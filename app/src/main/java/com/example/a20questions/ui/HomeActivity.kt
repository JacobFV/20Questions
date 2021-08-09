package com.example.a20questions.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.a20questions.R
import com.example.a20questions.ui.questionnairre.QuestionnaireActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
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