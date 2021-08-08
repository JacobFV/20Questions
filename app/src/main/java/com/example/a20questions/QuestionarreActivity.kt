package com.example.a20questions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class QuestionarreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionarre)
    }


    fun addQuestion(question: String) {
        // TODO: Manish can you please add the question to the recycler view
    private fun answer(answer: Answer) {
        // this is where all the game logic happens
    }

    private fun addTextElement(question: String) {
        // This function is called by `answer`
        // TODO: Manish can you please add a cardview to the recycler view
        //       that displays a piece of text?
    }

    private fun addYesMaybeNoInput(question: String) {
        // This function is called by `answer`
        // TODO: Manish can you please add a cardview to the recycler view
        //       that displays three buttons: "yes", "not sure", and "no" ?
    }

    private fun addYesNoInput(question: String) {
        // This function is called by `answer`
        // TODO: Manish can you please add a cardview to the recycler view
        //       that displays three buttons: "yes" and "no" ?
    }

    fun answerYesPressed() {

    }

    fun answerNotSurePressed() {

    }

    fun answerNoPressed() {

    }
}

enum class Answer { YES, NOTSURE, NO }