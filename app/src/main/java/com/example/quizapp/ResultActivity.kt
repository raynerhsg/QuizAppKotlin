package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    private var mUserName : String? = null
    private var mCorrectAnswers : Int? = null
    private var mTotalQuestions : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mCorrectAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        mTotalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        setContentView(R.layout.activity_result)
        txtName.text = mUserName.toString()
        txtResult.text = "Your score is ${mCorrectAnswers.toString()} out of ${mTotalQuestions.toString()}"


        finishButton.setOnClickListener {
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }

    }
}