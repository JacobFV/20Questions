package com.example.a20questions.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a20questions.R
import com.example.a20questions.data.*
import com.example.a20questions.ui.questionnairre.QuestionnaireActivity
import kotlinx.coroutines.*

class HomeActivity : AppCompatActivity() {

    private lateinit var savedGames: List<SavedGame>
    private lateinit var savedGamesListAdaptor: SavedGameRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        runBlocking { launch {
            initData()
            initView()
        } }
    }

    private suspend fun initData() {
        Log.d("timing", "start init data")
        withContext(Dispatchers.IO) {
            val savedGameDao: SavedGameDao = AppDatabase.getDatabase(application).savedGameDao()
            savedGameDao.insertSavedGames(savedGame1, savedGame2, savedGame3, savedGame4)
            savedGames = savedGameDao.getAll()
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
        Log.d("timing", "end init view")
    }

    fun play_button_clicked(view: View) {
        val intent = Intent(this, QuestionnaireActivity::class.java)
        startActivity(intent)
    }

    fun share_button_clicked(view: View) {
        /*This will be the actual content you wish you share.*/
        val shareBody = (
            "I just <WON/LOST> against 20 Questions." +
            "My score was <SCORE>. \n\n" +
            "You can also 20 Questions by downloading the app at " +
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