package com.example.sportsquiz.core.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsquiz.core.score.ScoreRepository
import com.example.sportsquiz.core.score.Settings
import com.example.sportsquiz.core.db.EntityScore
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class QuestionsViewModel(
    private val questionsRepository: QuestionsRepository,
    private val scoreRepository: ScoreRepository,
    private val difficulty: Settings.DIFFICULTY,
) : ViewModel() {

    private val questions = questionsRepository.getQuestions().let {
        when (difficulty) {
            Settings.DIFFICULTY.EASY -> it.easy
            Settings.DIFFICULTY.MEDIUM -> it.medium
            Settings.DIFFICULTY.HARD -> it.hard
        }
    }
    private val answers = questionsRepository.getAnswers().let {
        when (difficulty) {
            Settings.DIFFICULTY.EASY -> it.easy
            Settings.DIFFICULTY.MEDIUM -> it.medium
            Settings.DIFFICULTY.HARD -> it.hard
        }
    }
    private val score = when (difficulty) {
        Settings.DIFFICULTY.EASY -> 1
        Settings.DIFFICULTY.MEDIUM -> 2
        Settings.DIFFICULTY.HARD -> 3
    }

    private val timerValue = 10

    private var _timeLeft = MutableStateFlow(timerValue)
    var timeLeft: StateFlow<Int> = _timeLeft

    private var _currentIndex = MutableStateFlow(0)
    var currentIndex = _currentIndex.asStateFlow()

    private var _currentQuestion = MutableStateFlow(
        Pair(
            questions.question[currentIndex.value].first,
            questions.question[currentIndex.value].second
        )
    )
    var currentQuestion = _currentQuestion.asStateFlow()

    private var _isGameOver = MutableStateFlow(false)
    var isGameOver: StateFlow<Boolean> = _isGameOver

    private lateinit var countDownJob: Job
    private var questionIndex = 0
    private var totalScore = 0

    init {
        onNewQuestionStarted()
    }

    fun onOptionSelected(answer: String?) {

        val currentAnswer = answer?.substring(3, answer.lastIndex + 1) ?: answer

        if (answers[questionIndex] == currentAnswer) {
            totalScore += score
        }
        if (questionIndex < questions.question.size - 1) {
            questionIndex += 1
            onNewQuestionStarted()
            viewModelScope.launch {
                _currentIndex.emit(
                    questionIndex
                )
                _currentQuestion.emit(
                    Pair(
                        questions.question[currentIndex.value].first,
                        questions.question[currentIndex.value].second
                    )
                )
            }
        } else {
            viewModelScope.launch {
                scoreRepository.changeTotalScore(
                    EntityScore(
                        scoreValue = totalScore + scoreRepository.getTotalScore().first().scoreValue
                    )
                )
                _isGameOver.emit(true)
            }
        }
    }

    private fun onNewQuestionStarted() {
        viewModelScope.launch {
            if (this@QuestionsViewModel::countDownJob.isInitialized) countDownJob.cancelAndJoin()
            _timeLeft.emit(timerValue)
            countDownJob = viewModelScope.launch {
                var newTimestamp = _timeLeft.value - 1
                while (newTimestamp > 0) {
                    delay(1000)
                    newTimestamp = _timeLeft.value - 1
                    _timeLeft.emit(newTimestamp)
                }
                onOptionSelected(null)
            }
        }
    }


}