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
import com.example.a20questions.data.AppDatabase
import com.example.a20questions.data.SavedGame
import com.example.a20questions.data.exampleQuestionsAndAnswers1
import com.example.a20questions.data.exampleQuestionsAndAnswers2
import com.example.a20questions.ui.questionnairre.QuestionnaireActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var savedGamesListAdaptor: SavedGameRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initData()
        initView()
    }

    private fun initData() {
        //savedGamesListAdaptor = SavedGameRecyclerAdapter(mutableListOf())
        // thanks:
        // https://medium.com/@tonia.tkachuk/android-app-example-using-room-database-63f7091e69af
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.savedGamesList.observe(this,
            Observer { savedGamesList: List<SavedGame> ->
                savedGamesListAdaptor.setSavedGamesList(savedGamesList)

                Log.d("num savedgames", savedGamesList.size.toString())
                Log.d("savedgame", savedGamesList.toString())
                savedGamesList.forEach {
                    Log.d("savedgames[i]", it.username)
                    Log.d("savedgames[i]", it.time_completed_formatted)
                //    Log.d("savedgames[i]", it.questions_and_answers)
                }

            }
        )
        // end thanks
    }

    private fun initView() {
        val savedGamesRecyclerView: RecyclerView =
            findViewById(R.id.home_savedgames_recyclerview)
        savedGamesListAdaptor = SavedGameRecyclerAdapter(this)
        savedGamesRecyclerView.adapter = savedGamesListAdaptor
        savedGamesRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        savedGamesRecyclerView.layoutManager = LinearLayoutManager(this)
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