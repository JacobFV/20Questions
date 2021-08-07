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
            button.setOnClickListener{
                val intent = Intent(this,QuestionareActivity::class.java)
                startActivity(intent)
    }

    /*fun play_button_clicked(view: View) {
        val intent = Intent(this, QuestionareActivity::class.java)
        startActivity(intent)
    }*/


        }

    //fun share_button_clicked(view: View) {

  //  }


}