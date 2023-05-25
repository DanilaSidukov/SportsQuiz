package com.example.sportsquiz.core.questions

class QuestionsRepository(){

    fun getQuestions(): Quiz{
        return Quiz()
    }

    fun getAnswers(): RightAnswers{
        return  RightAnswers()
    }

}