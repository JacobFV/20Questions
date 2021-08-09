package com.example.a20questions.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.a20questions.Answer
import com.example.a20questions.R

class QuestionnaireActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionarre)
    }

    private fun answer(answer: Answer) {
        // this is where all the game logic happens
    }

    fun addQuestion(question: String) {
        // TODO: Manish can you please add the question to the recycler view
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

    fun answerYesPressed(view: View) {
        answer(Answer.YES)
    }

    fun answerNotSurePressed(view: View) {
        answer(Answer.NOTSURE)
    }

    fun answerNoPressed(view: View) {
        answer(Answer.NO)
    }
}