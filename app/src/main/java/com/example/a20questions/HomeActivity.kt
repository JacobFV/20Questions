package com.example.a20questions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    private fun play_button_clicked(view: View) {
        val intent = Intent(this, QuestionarreActivity::class.java)
        startActivity(intent)
    }

    private fun share_button_clicked(view: View) {

    }


}