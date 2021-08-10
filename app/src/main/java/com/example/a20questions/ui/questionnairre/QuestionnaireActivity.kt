package com.example.a20questions.ui.questionnairre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a20questions.Algorithm
import com.example.a20questions.Answer
import com.example.a20questions.Domain
import com.example.a20questions.R

class QuestionnaireActivity : AppCompatActivity() {

    private var algorithm: Algorithm? = null

    private val qaPairs = mutableListOf<QAPair>()
    private val qaRecyclerAdapter = QARecyclerAdapter(qaPairs)

    private lateinit var questionnairreRecyclerview: RecyclerView
    private lateinit var questionTextView: TextView
    private lateinit var yesButton: Button
    private lateinit var notSureButton: Button
    private lateinit var noButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionarre)

        initView()

        // either calls `loadSavedGame` or `loadFirstQuestion`
        // maybe unload qaPairs from bundle for savedgame
        // but for now assume game is new
        startNewGame()
    }

    private fun initView() {
        questionnairreRecyclerview = findViewById(R.id.questionnairre_recyclerview)
        questionnairreRecyclerview.adapter = qaRecyclerAdapter
        questionTextView = findViewById(R.id.question_textview)
        yesButton = findViewById(R.id.yes_button)
        notSureButton = findViewById(R.id.not_sure_button)
        noButton = findViewById(R.id.no_button)
    }

    private fun loadSavedGame(gameId: Int) {
        // currently not used
        yesButton.isEnabled = false
        notSureButton.isEnabled = false
        noButton.isEnabled = false
    }

    private fun startNewGame() {
        algorithm = Algorithm(Domain.Superheroes, this)
        questionTextView.text = algorithm!!.currentQuestion
    }

    private fun answer(answer: Answer) {
        // this is where all the game logic happens
        qaPairs.add(QAPair(algorithm!!.currentQuestion, answer.toString()))
        qaRecyclerAdapter.notifyItemInserted(qaPairs.size-1)
        algorithm!!.answerCurrentQuestion(answer)
        questionTextView.text = algorithm!!.currentQuestion
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