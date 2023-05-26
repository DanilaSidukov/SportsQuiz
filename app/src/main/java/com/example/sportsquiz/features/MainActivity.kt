package com.example.sportsquiz.features

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.sportsquiz.R
import com.example.sportsquiz.core.di.ScoreViewModelFactory
import com.example.sportsquiz.core.score.ScoreViewModel
import com.example.sportsquiz.core.score.Settings
import com.example.sportsquiz.core.score.Settings.Companion.DIFFICULT_KEY
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var buttonBuyWallpapers: Button
    private lateinit var buttonPlay: Button

    private lateinit var chooseDifficultyDialogView: View
    private lateinit var chooseDifficultyDialog: AlertDialog
    private lateinit var easyButton: TextView
    private lateinit var mediumButton: TextView
    private lateinit var hardButton: TextView
    private lateinit var scoreValue: TextView

    lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreViewModel = ViewModelProvider(this, ScoreViewModelFactory())[ScoreViewModel::class.java]

        scoreValue = findViewById(R.id.score)
        buttonBuyWallpapers = findViewById(R.id.button_buy_wallpapers)
        buttonBuyWallpapers.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    WallpaperActivity::class.java
                )
            )
        }

        lifecycleScope.launch {
            scoreViewModel.score.collect {
                try {
                    scoreValue.text = it.scoreValue.toString()
                } catch (e: NullPointerException) {

                }
            }
        }

        chooseDifficultyDialogView =
            LayoutInflater.from(this).inflate(R.layout.dialog_choose_difficult, null, false)
        chooseDifficultyDialog =
            AlertDialog.Builder(this).setView(chooseDifficultyDialogView).create()

        easyButton = chooseDifficultyDialogView.findViewById(R.id.text_easy)
        mediumButton = chooseDifficultyDialogView.findViewById(R.id.text_medium)
        hardButton = chooseDifficultyDialogView.findViewById(R.id.text_hard)

        buttonPlay = findViewById(R.id.button_play)
        buttonPlay.setOnClickListener {
            chooseDifficultyDialog.show()
            easyButton.setOnClickListener {
                startGameActivity(Settings.DIFFICULTY.EASY)
            }
            mediumButton.setOnClickListener {
                startGameActivity(Settings.DIFFICULTY.MEDIUM)
            }
            hardButton.setOnClickListener {
                startGameActivity(Settings.DIFFICULTY.HARD)
            }
        }
    }

    private fun startGameActivity(difficulty: Settings.DIFFICULTY) {
        startActivity(
            Intent(
                this,
                GameActivity::class.java
            ).apply {
                putExtra(DIFFICULT_KEY, difficulty)
            }
        )
        chooseDifficultyDialog.dismiss()
    }

}