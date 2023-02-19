package com.example.thewizardquiz

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

/**
 * This is the kotlin class for Menu Activity (3 buttons)
 */
class menu : AppCompatActivity() {

    private lateinit var playButton: Button;
    private lateinit var scoreButton: Button;
    private lateinit var backButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // init play button
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener{
            handlePlayButton();
        }

        // init back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener{
            handleBackButton();
        }

        // init score button
        scoreButton = findViewById(R.id.scoreButton)
        scoreButton.setOnClickListener{
            handleScoreButton();
        }
    }

    private fun handleBackButton(){
        // go to welcome activity
        startActivity(Intent(this, WelcomeActivity::class.java).apply {  })
    }

    private fun handlePlayButton(){
        // go to  main quiz activity
        startActivity(Intent(this, quiz::class.java).apply {  })
    }

    private fun handleScoreButton(){
        // go to score activity
        startActivity(Intent(this, score::class.java).also {
            it.putExtra("score",getScore());
        })

    }
    private fun getScore(): Int{
        // we stores the score in shared preference, when any user completes 4 questions
        // here, we are fetching last stores score
        val sharedPreference =  getSharedPreferences("QUIZ_SCORE", Context.MODE_PRIVATE);
        return sharedPreference.getInt("score",0);
    }
}