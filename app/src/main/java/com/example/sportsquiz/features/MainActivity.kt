package com.example.sportsquiz.features

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sportsquiz.R
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var buttonBuyWallpapers: Button
    private lateinit var buttonPlay: Button

    private lateinit var chooseDifficultyDialogView: View
    private lateinit var chooseDifficultyDialog: AlertDialog
    private lateinit var easyButton: TextView
    private lateinit var mediumButton: TextView
    private lateinit var hardButton: TextView


    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonBuyWallpapers = findViewById(R.id.button_buy_wallpapers)
        buttonBuyWallpapers.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    WallpaperActivity::class.java
                )
            )
        }

        chooseDifficultyDialogView =
            LayoutInflater.from(this).inflate(R.layout.dialog_choose_difficult, null, false)
        val dialog = AlertDialog.Builder(this)
            .setView(chooseDifficultyDialogView)

        easyButton = chooseDifficultyDialogView.findViewById(R.id.text_easy)
        mediumButton = chooseDifficultyDialogView.findViewById(R.id.text_medium)
        hardButton = chooseDifficultyDialogView.findViewById(R.id.text_hard)

        buttonPlay = findViewById(R.id.button_play)
        buttonPlay.setOnClickListener {

            if (chooseDifficultyDialogView.parent != null){
                (chooseDifficultyDialogView.parent as ViewGroup).removeView(chooseDifficultyDialogView)
            }

            chooseDifficultyDialog = dialog.show()
            easyButton.setOnClickListener {
                startActivity(
                    Intent(
                        this,
                        GameActivity::class.java
                    )
                )
            }
            mediumButton.setOnClickListener {
                startActivity(
                    Intent(
                        this,
                        GameActivity::class.java
                    )
                )
            }
            hardButton.setOnClickListener {
                startActivity(
                    Intent(
                        this,
                        GameActivity::class.java
                    )
                )
            }

        }

    }

}