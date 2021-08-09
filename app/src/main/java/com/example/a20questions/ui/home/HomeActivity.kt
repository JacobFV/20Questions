package com.example.a20questions.ui.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var savedGamesListAdaptor: SavedGameRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        savedGamesListAdaptor = SavedGameRecyclerAdapter(mutableListOf(
            SavedGame(1, 1628524385, "Aug 9, 2021 10:00am",
                exampleQuestionsAndAnswers1, true, "test"),
            SavedGame(1, 1628524399, "Aug 9, 2021 10:05am",
                exampleQuestionsAndAnswers1, true, "test"),
            SavedGame(1, 1628524801, "Aug 9, 2021 11:00am",
                exampleQuestionsAndAnswers2, false, "test"),
            SavedGame(1, 1628525002, "Aug 9, 2021 11:35am",
                exampleQuestionsAndAnswers2, false, "test"),
        ))
        initView()
        //initData()
    }

    private fun initView() {
        val savedGamesRecyclerView: RecyclerView =
            findViewById(R.id.home_savedgames_recyclerview)
        savedGamesRecyclerView.adapter = savedGamesListAdaptor
        //savedGamesRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        savedGamesRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initData() {
        // thanks:
        // https://medium.com/@tonia.tkachuk/android-app-example-using-room-database-63f7091e69af
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.savedGamesList.observe(this,
            Observer { savedGamesList: List<SavedGame> ->
                savedGamesListAdaptor.setSavedGamesList(savedGamesList)
            }
        )
        // end thanks
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