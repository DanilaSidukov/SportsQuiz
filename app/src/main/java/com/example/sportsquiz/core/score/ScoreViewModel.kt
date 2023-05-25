package com.example.sportsquiz.core.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsquiz.core.db.EntityScore
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ScoreViewModel(
    private val scoreRepository: ScoreRepository
): ViewModel() {

    val score = scoreRepository.getTotalScore().shareIn(viewModelScope, SharingStarted.Lazily)

    init {
        viewModelScope.launch {
            if (scoreRepository.checkIsEmpty()) {
                scoreRepository.changeTotalScore(EntityScore(scoreValue =  0))
            }
        }
    }

}