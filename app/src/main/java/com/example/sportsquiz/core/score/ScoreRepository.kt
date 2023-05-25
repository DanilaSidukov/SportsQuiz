package com.example.sportsquiz.core.score

import com.example.sportsquiz.core.db.EntityScore
import com.example.sportsquiz.core.db.ScoreDao


class ScoreRepository (
    private val scoreDao: ScoreDao,
){

    fun getTotalScore() = scoreDao.getScore()

    suspend fun changeTotalScore(scoreItem: EntityScore) =
        scoreDao.changeScore(scoreItem)

    suspend fun checkIsEmpty() = scoreDao.isEmpty()

}