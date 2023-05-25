package com.example.sportsquiz.features

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.sportsquiz.R
import com.example.sportsquiz.core.score.Settings
import com.example.sportsquiz.core.score.Settings.Companion.DIFFICULT_KEY
import com.example.sportsquiz.core.di.QuestionViewModelFactory
import com.example.sportsquiz.core.questions.QuestionsViewModel
import kotlinx.coroutines.launch


class GameActivity : AppCompatActivity() {

    private lateinit var questionNumber: TextView
    private lateinit var question: TextView
    private lateinit var optionA: TextView
    private lateinit var optionB: TextView
    private lateinit var optionC: TextView
    private lateinit var optionD: TextView
    private lateinit var timeLeft: TextView

    private lateinit var questionsViewModel: QuestionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val difficulty = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.extras!!.getSerializable(DIFFICULT_KEY, Settings.DIFFICULTY::class.java)
        } else intent.extras!!.getSerializable(DIFFICULT_KEY) as Settings.DIFFICULTY

        questionsViewModel = ViewModelProvider(this, QuestionViewModelFactory(
            this,
            difficulty!!
        ))[QuestionsViewModel::class.java]

        questionNumber = findViewById(R.id.text_question_number)
        question = findViewById(R.id.text_question)
        optionA = findViewById(R.id.text_option_a)
        optionB = findViewById(R.id.text_option_b)
        optionC = findViewById(R.id.text_option_c)
        optionD = findViewById(R.id.text_option_d)
        timeLeft = findViewById(R.id.text_timer)

        lifecycleScope.launch {
            questionsViewModel.timeLeft.collect {
                timeLeft.text = it.toString()
            }
        }

        lifecycleScope.launch {
            questionsViewModel.isGameOver.collect {
                if (it) finish()
            }
        }

        lifecycleScope.launch {
            questionsViewModel.currentQuestion.collect { currentQuestion ->
                question.text = currentQuestion.first
                optionA.text = "A: ${currentQuestion.second.a}"
                optionB.text = "B: ${currentQuestion.second.b}"
                optionC.text = "C: ${currentQuestion.second.c}"
                optionD.text = "D: ${currentQuestion.second.d}"
            }
        }

        lifecycleScope.launch {
            questionsViewModel.currentIndex.collect { index ->
                questionNumber.text = "Question: ${index + 1}"
            }
        }

        listOf(optionA, optionB, optionC, optionD).forEach {
            it.setOnClickListener {
                val text = when(it) {
                    optionA -> optionA.text.toString()
                    optionB -> optionB.text.toString()
                    optionC -> optionC.text.toString()
                    optionD -> optionD.text.toString()
                    else -> {}
                }
                questionsViewModel.onOptionSelected(text.toString())
            }
        }

    }
}