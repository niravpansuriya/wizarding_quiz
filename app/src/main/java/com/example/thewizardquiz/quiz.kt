package com.example.thewizardquiz

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class quiz : AppCompatActivity() {

    // question text
    private lateinit var questionText:TextView;

    // this is the array, which contains 3 answers buttons
    private var ansButtons:ArrayList<Button> = ArrayList();

    // background of the activity, to change the color
    private lateinit var background: View;

    private lateinit var nextButton: Button;

    // score view
    private lateinit var scoreText: TextView;

    // randomly selected questions
    private lateinit var selectedQuestions:ArrayList<Question>;

    // keep count of current question number
    private var questionCount:Int = -1;

    // keep counts of current score
    private var currentScore:Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // init question text view and ans buttons
        questionText = findViewById(R.id.questionText);
        ansButtons.add(findViewById(R.id.ansAButton));
        ansButtons.add(findViewById(R.id.ansBButton));
        ansButtons.add(findViewById(R.id.ansCButton));

        // set up answer button handler with button id
        for(i in 0..2){
            ansButtons[i].setOnClickListener{
                handleAnswerButton(i)
            }
        }

        // init score text, background and next button
        scoreText = findViewById(R.id.scoreText);
        background = findViewById(R.id.quizBackground);
        nextButton = findViewById(R.id.nextButton);

        // set up next button handler
        nextButton.setOnClickListener{
            handleNextButton();
        }

        // store 20 questions
        initQuestions();

        // get random questions from 20 questions
        selectedQuestions = getRandomQuestions(questions);

        handleNextButton();

    }

    // set up the random background
    private fun setRandomBackground(){
        var colors:Array<String> = arrayOf("#dec78a","#fadaaa","#c29d70","#ecc57d");
//        var color:String = colors[Random.nextInt(0,4)];
        background.setBackgroundColor(Color.parseColor(colors[questionCount]));
    }

    // handle next button click
    private fun handleNextButton(){

        // enable all answers buttons, which will be disable once user will click on ansewr
        // button
        enableAnswerButtons();

        // make all buttons transparent, which will be red or green when user click it, based on
        // the answer (green for correct answer, red for wrong)
        makeAnsButtonsTransparant();

        // increase question counter
        questionCount++;

        // if user has already ansewrd all 4 questions, then...
        if(questionCount == 4){

            // store the score in shared preference
            storeScore(currentScore);

            // go to the score activity
            startActivity(Intent(this, score::class.java).also{
                it.putExtra("score",currentScore)
            })
            return;
        }

        // set random background
        setRandomBackground();

        // set question and ansewrs to question text view and answer buttons
        setQuestion(selectedQuestions[questionCount]);
    }

    // it will make all answer buttons transparent
    private fun makeAnsButtonsTransparant(){
        for(i in 0..2){
            ansButtons[i].setBackgroundColor(Color.parseColor("#0000ffff"));
        }
    }

    // it will put question in question text view
    private fun setQuestion(question:Question){
        // disable next button, it will be enabled when user will click on the ansewr button
        nextButton.isEnabled = false;

        // set up the question
        questionText.text = question.question;

        // set up ansewrs
        ansButtons[0].text = "A. " + question.answers[0];
        ansButtons[1].text = "B. " + question.answers[1];
        ansButtons[2].text = "C. " + question.answers[2];
    }

    // it will handle answer button click
    private fun handleAnswerButton(buttonId:Int){

        // disable answer buttons, so user does not select multiple answers
        disableAnswerButtons();

        // enable next button
        nextButton.isEnabled = true

        // get correct answer of the question
        var correctAnswer = selectedQuestions[questionCount].correctAnswer;

        // if answer is correct, then increase the score
        if(correctAnswer -1 == buttonId){
            currentScore++;
        }
        // else change the clicked button background to red
        else{
            ansButtons[buttonId].setBackgroundColor(Color.parseColor("#7DFA1402"));
        }

        // update the score view with updated score
        scoreText.text  = currentScore.toString() + "/4";

        // make correct answer button to green
        ansButtons[correctAnswer-1].setBackgroundColor(Color.parseColor("#FF99CC00"));
    }

    private fun disableAnswerButtons(){
        for(i in 0..2){
            ansButtons[i].isEnabled = false;
        }
    }

    private fun enableAnswerButtons(){
        for(i in 0..2){
            ansButtons[i].isEnabled = true;
        }
    }

    // it will store score in shared preference
    private fun storeScore(score:Int){
        var sharedPreference =  getSharedPreferences("QUIZ_SCORE",Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putInt("score",score)
        editor.commit()
    }
}