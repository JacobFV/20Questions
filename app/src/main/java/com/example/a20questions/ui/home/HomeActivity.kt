package com.example.a20questions.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a20questions.R
import com.example.a20questions.data.*
import com.example.a20questions.ui.questionnairre.QuestionnaireActivity
import kotlinx.android.synthetic.main.activity_scores.*
import kotlinx.coroutines.*
import kotlin.math.max

class HomeActivity : AppCompatActivity() {

    private lateinit var savedGames: List<SavedGame>
    private lateinit var savedGamesListAdaptor: SavedGameRecyclerAdapter
    private lateinit var username: String
    private var maxQuestions: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        username = intent.getStringExtra("USERNAME")?: userJacob.username

        runBlocking { launch {
            initData()
            initView()
        } }
    }

    private suspend fun initData() {
        Log.d("timing", "start init data")
        withContext(Dispatchers.IO) {
            val savedGameDao: SavedGameDao = AppDatabase.getDatabase(application).savedGameDao()
            //savedGameDao.insertSavedGames(savedGame1, savedGame2, savedGame3, savedGame4)
            savedGames = savedGameDao.getAll()
            savedGames.forEach {
                maxQuestions = max(maxQuestions, it.num_questions)
            }
            Log.d("sgs initData", savedGames.size.toString())
        }
        Log.d("timing", "end init data")
    }

    private fun initView() {
        Log.d("timing", "start init view")
        val savedGamesRecyclerView: RecyclerView =
            findViewById(R.id.home_savedgames_recyclerview)
        savedGamesListAdaptor = SavedGameRecyclerAdapter(savedGames, "Manish")
        savedGamesRecyclerView.adapter = savedGamesListAdaptor
        savedGamesRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        savedGamesRecyclerView.layoutManager = LinearLayoutManager(this)
        findViewById<TextView>(R.id.longest_game_text).text = "${maxQuestions}Q"
        Log.d("timing", "end init view")
    }

    override fun onResume() {
        super.onResume()
        //Code to refresh listview
        runBlocking { launch {
            initData()
            initView()
        } }
    }

    fun play_button_clicked(view: View) {
        val intent = Intent(this, QuestionnaireActivity::class.java)
        intent.putExtra("USERNAME", username)
        startActivity(intent)
    }

    fun share_button_clicked(view: View) {
        /*This will be the actual content you wish you share.*/
        val shareBody = (
            "I just played 20 Questions." +
            "My longest running game was $maxQuestions questions. \n\n" +
            "You can also 20 Questions by downloading the app APK at " +
            "https://github.com/JacobFV/20Questions")
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareBody)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    fun info_button_clicked(view: View) {
        val openURL = Intent(android.content.Intent.ACTION_VIEW)
        openURL.data = Uri.parse("https://github.com/JacobFV/20Questions")
        startActivity(openURL)
    }

}