package com.chiamaka.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName: TextView = findViewById(R.id.tv_name)
        val btnDone: Button = findViewById(R.id.btn_done)
        val tvResult: TextView = findViewById(R.id.tv_res_text)

        tvName.text = intent.getStringExtra(Constants.USER_NAME)
        tvResult.text = intent.getStringExtra(Constants.RESPONSE_ONE) + intent.getStringExtra(Constants.RESPONSE_TWO) + intent.getStringExtra(Constants.RESPONSE_THREE) + intent.getStringExtra(Constants.RESPONSE_FOUR)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)

        btnDone.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java ))
        }
    }
}