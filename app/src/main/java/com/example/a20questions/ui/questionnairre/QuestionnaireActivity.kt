package com.example.a20questions.ui.questionnairre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.a20questions.*
import com.example.a20questions.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import kotlin.random.Random


class QuestionnaireActivity : AppCompatActivity() {

    private var questionState = QuestionState.Algorithm
    private var algorithm: Algorithm? = null
    private var numGameQuestions = 0
    private lateinit var username: String

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

        // get username
        username = intent.getStringExtra("USERNAME")!!

        initView()

        // either calls `loadSavedGame` or `loadFirstQuestion`
        // maybe unload qaPairs from bundle for savedgame
        // but for now assume game is new
        startNewGame()
    }

    private fun initView() {
        questionnairreRecyclerview = findViewById(R.id.questionnairre_recyclerview)
        questionnairreRecyclerview.adapter = qaRecyclerAdapter
        questionnairreRecyclerview.addItemDecoration(DividerItemDecoration(this,
            DividerItemDecoration.VERTICAL))
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

    private fun gameQuestionResponse(answer: Answer) {
        // this is where alot of the game logic happens
        qaPairs.add(QAPair(algorithm!!.currentQuestion, answer.toString()))
        qaRecyclerAdapter.notifyItemInserted(qaPairs.size-1)

        algorithm!!.answerCurrentQuestion(answer)
        numGameQuestions = numGameQuestions + 1

        when(algorithm!!.potentialAnswers) {
            AnswerState.NoAnswer -> {
                Log.d("questionnairre", "no answer. guessing based on last round's potential answers")
                guessItem()
            }
            AnswerState.OneAnswer -> {
                // guess item
                guessItem()
            }
            AnswerState.ManyAnswers -> {
                when(numGameQuestions) {
                    20 -> {
                        maybePlayMore()
                    }
                    25 -> {
                        maybePlayMore()
                    }
                    30 -> {
                        guessItem()
                    }
                    else -> {
                        // normal game round
                        displayNextQuestion()
                    }
                }
            }
        }
    }

    private fun guessItemResponse(answer: Answer) {
        when(answer) {
            Answer.YES -> {
                // you lost
                qaPairs.add(QAPair((
                        getString(R.string.thinking_of)
                                + algorithm!!.mysteryItemName!!
                                + getString(R.string.question_mark)
                        ), "Yes"))
                qaRecyclerAdapter.notifyItemInserted(qaPairs.size-1)

                this.saveAndQuitGame(false, algorithm!!.mysteryItemName!!)
            }
            Answer.NO -> {
                // you won
                qaPairs.add(QAPair((
                        getString(R.string.thinking_of)
                                + algorithm!!.mysteryItemName!!
                                + getString(R.string.question_mark)
                        ), "No"))
                qaRecyclerAdapter.notifyItemInserted(qaPairs.size-1)

                // get name of item
                val actualName = "Unknown"

                this.saveAndQuitGame(false, actualName)
            }
            else -> {
                Log.e("Questionnairre","Maybe pressed when disabled")
            }
        }
    }

    private fun maybeContinuePlayingResponse(answer: Answer) {
        // maybe end game
        // if yes, continue playing
        // if no, guess item
        when(answer) {
            Answer.YES -> {
                displayNextQuestion()
            }
            Answer.NO -> {
                guessItem()
            }
            else -> {
                Log.e("Questionnairre","Maybe pressed when disabled")
            }
        }
    }

    private fun displayNextQuestion() {
        yesButton.isEnabled = true
        notSureButton.isEnabled = true
        noButton.isEnabled = true

        questionTextView.text = algorithm!!.currentQuestion

        questionState = QuestionState.Algorithm
    }

    private fun guessItem() {
        yesButton.isEnabled = true
        notSureButton.isEnabled = false
        noButton.isEnabled = true

        questionTextView.text = (
            getString(R.string.thinking_of)
            + algorithm!!.mysteryItemName!!
            + getString(R.string.question_mark)
        )

        questionState = QuestionState.GuessItem
    }

    private fun maybePlayMore() {
        yesButton.isEnabled = true
        notSureButton.isEnabled = false
        noButton.isEnabled = true

        questionTextView.text = getString(R.string.play_more)

        questionState = QuestionState.PlayMore
    }

    private fun route(answer: Answer) {
        when (questionState) {
            QuestionState.Algorithm -> this.gameQuestionResponse(answer)
            QuestionState.GuessItem -> guessItemResponse(answer)
            QuestionState.PlayMore -> maybeContinuePlayingResponse(answer)
        }
    }

    fun answerYesPressed(view: View) {
        route(Answer.YES)
    }

    fun answerNotSurePressed(view: View) {
        route(Answer.NOTSURE)
    }

    fun answerNoPressed(view: View) {
        route(Answer.NO)
    }

    private fun saveAndQuitGame(didwin: Boolean, mysteryItemName: String) {
        // save into database
        runBlocking { launch { _internalSaveGame(didwin, mysteryItemName) } }

        // quit
        finish()
    }

    private suspend fun _internalSaveGame(didwin: Boolean, mysteryItemName: String) {
        var combinedQAPairs = ""
        qaPairs.forEach {
            combinedQAPairs = combinedQAPairs + it.Q + " " + it.A
        }
        withContext(Dispatchers.IO) {
            AppDatabase
                .getDatabase(application)
                .savedGameDao()
                .insertSavedGames(SavedGame(
                    Random.nextInt(),
                    (System.currentTimeMillis() / 1000).toInt(),
                    SimpleDateFormat("dd-MM-yyy HH:mm:ss z").format(Calendar.getInstance().time),
                    combinedQAPairs,
                    didwin,
                    numGameQuestions,
                    username
                )
            )
        }
    }
}

enum class QuestionState {
    Algorithm, GuessItem, PlayMore
}