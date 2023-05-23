package com.example.sportsquiz.core.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class QuestionsViewModel(
    private val questionsRepository: QuestionsRepository
): ViewModel() {

    private val _allQuestions = MutableStateFlow(Quiz())
    var allQuestions = _allQuestions.asStateFlow()

    init {
        viewModelScope.launch {
            _allQuestions.emit(
                questionsRepository.getQuestions()
            )
        }
    }

}