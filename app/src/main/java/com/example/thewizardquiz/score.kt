package com.example.thewizardquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

/**
 * This is for score view to show score
 */
class score : AppCompatActivity() {
    private lateinit var scoreView: TextView;
    private lateinit var backButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        scoreView = findViewById(R.id.score);
        backButton = findViewById(R.id.scoreBackButton);

        backButton.setOnClickListener{
            handleBackButton();
        }

        var currentScore = intent.getIntExtra("score",-1);
        scoreView.text = currentScore.toString() + "/4";
    }

    private fun handleBackButton(){
        // on back button click, go to the menu activity
        startActivity(Intent(this, menu::class.java).apply {  })
    }
}