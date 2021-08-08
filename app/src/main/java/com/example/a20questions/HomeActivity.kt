package com.example.a20questions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val button = findViewById<Button>(R.id.play_button)
            button.setOnClickListener {
                val intent = Intent(this, QuestionareActivity::class.java)
                startActivity(intent)
            }
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

    fun play_button_clicked(view: View) {

    }

}