package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnStart : Button = findViewById(R.id.startButton)
        val txtName : EditText = findViewById(R.id.txtName)
        btnStart.setOnClickListener {
            if(txtName.text.isNotEmpty()) {
                val quizQuestionsIntent = Intent(this, QuizQuestionsActivity::class.java)
                quizQuestionsIntent.putExtra(Constants.USER_NAME, txtName.text.toString())
                startActivity(quizQuestionsIntent)
                finish()
            }else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
            }
        }



    }
}