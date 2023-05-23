package com.example.sportsquiz.core.questions

class QuestionsRepository() {

    suspend fun getQuestions(): Quiz{
        return Quiz()
    }

}