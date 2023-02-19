package com.example.thewizardquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

/**
 * First activity which user will see, when open the application
 */
class WelcomeActivity : AppCompatActivity() {
    private lateinit var enterButton: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // start background music
        playAudio(applicationContext);

        // set up enter button handler
        enterButton = findViewById<Button>(R.id.enterButton);
        enterButton.setOnClickListener {
            handleEnterButton();
        }
    }

    private fun handleEnterButton(){
        // go to the menu activity
        startActivity(Intent(this,  menu::class.java).apply {  })
    }
}