package com.example.sportsquiz.features

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.sportsquiz.R
import com.example.sportsquiz.core.di.viewModelModule
import com.example.sportsquiz.core.questions.Answers
import com.example.sportsquiz.core.questions.QuestionsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class GameActivity: AppCompatActivity()  {

    private lateinit var questionNumber: TextView
    private lateinit var question: TextView
    private lateinit var optionA: TextView
    private lateinit var optionB: TextView
    private lateinit var optionC: TextView
    private lateinit var optionD: TextView

    private var questionEntity: Pair<String?, Answers?> = Pair(null, null)
    private val questionsViewModel: QuestionsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        questionNumber = findViewById(R.id.text_question_number)
        question = findViewById(R.id.text_question)
        optionA = findViewById(R.id.text_option_a)
        optionB = findViewById(R.id.text_option_b)
        optionC = findViewById(R.id.text_option_c)
        optionD = findViewById(R.id.text_option_d)


        lifecycleScope.launch {
            questionsViewModel.allQuestions.collect{quizItem ->
                questionEntity = Pair(quizItem.easy!!.question[0].first, quizItem.easy.question[0].second)
            }
        }
        questionNumber.text = "0"
        question.text = questionEntity.first.toString()
        optionA.text = questionEntity.second!!.a
        optionB.text = questionEntity.second!!.b
        optionC.text = questionEntity.second!!.c
        optionD.text = questionEntity.second!!.d

    }

}