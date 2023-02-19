package com.example.thewizardquiz

import kotlin.random.Random

// store question objects
public var questions: ArrayList<Question> = ArrayList();

/**
 * This class contains question, answer and correctAnswer
 */
class Question(val q:String, val a:Array<String>, val cA:Int) {
    val question: String = q;
    val answers:Array<String> = a;
    val correctAnswer:Int = cA;
}

// static function which will initialize the question objects
fun initQuestions(){

    // if questions array is empty
    if(questions.size == 0){

        // set up questions
        questions.add(Question("Who wrote the Harry Potter novels?",
        arrayOf("Eliot G","Lewis Caroll","J. K. Rowling"),
            3
        ))

        questions.add(Question("Who is the main enemy of Harry Potter?",
            arrayOf("Phoenix","Lord Voldemort","Dumbledore"),
            2
        ))

        questions.add(Question(
                "What is Harry Potter's age at the beginning of the series?",
            arrayOf("17","14","11"),
            3
        ))

        questions.add(Question(
                "What is the name of the first novel about Harry Potter?",
            arrayOf("Harry Potter and the Philosopher's Stone","Harry Potter and the Chamber of Secrets",
                "Harry Potter and the Prisoner of Azkaban"),
            1
        ))

        questions.add(Question(
            "What was the name suggested by Cho Chang in the 5th book for the gang of Harry Potter and his friends?",
            arrayOf("Defense Association","Kids Association",
                "School Kids"),
            1
        ))

        questions.add(Question(
            "What was the title of the first Harry Potter book published in the USA?",
            arrayOf("Harry Potter and the Philosopher's stone","Harry Potter and the Sorcerer's Stone",
                "Harry Potter and the Dursley's stone"),
            2
        ))

        questions.add(Question(
            "What is the wizard bank in the 1st book known as?",
            arrayOf("Butselg","Gringotts",
                "Bunchew"),
            2
        ))

        questions.add(Question(
            "What is the name for people with no magical blood?",
            arrayOf("Squad","Squib",
                "Muggle"),
            3
        ))

        questions.add(Question(
            "How many Harry Potter books have been published till date?",
            arrayOf("7","2",
                "4"),
            1
        ))

        questions.add(Question(
            "When was the first Harry Potter movie released?",
            arrayOf("2006","2001",
                "2002"),
            2
        ))
    }
}


// it will return 4 random questions
fun getRandomQuestions(questions:ArrayList<Question>):ArrayList<Question>{

    // will store random questions
    var result:ArrayList<Question> = ArrayList();

    // number of random questions
    var count:Int = 4;

    // this is to track which question is already randomly selected
    val addedQuestions = mutableSetOf<Int>();

    while(count != 0){

        // get random index
        var index = Random.nextInt(0, questions.size);

        // if question is already selected, then do not add it once again
        if(addedQuestions.contains(index)){
            continue;
        }else{
            result.add(questions[index]);
            addedQuestions.add(index);
            count--;
        }
    }

    return result;
}