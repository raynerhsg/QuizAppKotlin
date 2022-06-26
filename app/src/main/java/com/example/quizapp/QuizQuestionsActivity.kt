package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        setContentView(R.layout.activity_quiz_questions)
        mQuestionsList = Constants.getQuestions()

        txtOpt1.setOnClickListener(this)
        txtOpt2.setOnClickListener(this)
        txtOpt3.setOnClickListener(this)
        txtOpt4.setOnClickListener(this)
        submitButton.setOnClickListener(this)
        setQuestion()

    }

    private fun setQuestion() {
        defaultOptionsView()
        val currentQuestion: Question = mQuestionsList!![mCurrentPosition - 1]
        imageFlag.setImageResource(currentQuestion.image)
        progressBar.progress = mCurrentPosition
        txtProgress.text = "$mCurrentPosition / ${progressBar.max}"
        txtQuestion.text = currentQuestion.question
        txtOpt1.text = currentQuestion.optionOne
        txtOpt2.text = currentQuestion.optionTwo
        txtOpt3.text = currentQuestion.optionThree
        txtOpt4.text = currentQuestion.optionFour

        if (mCurrentPosition == mQuestionsList!!.size) {
            submitButton.text == "finish"
        } else {
            submitButton.text == "submit"
        }
    }


    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        txtOpt1?.let {
            options.add(0, it)
        }
        txtOpt2?.let {
            options.add(1, it)
        }
        txtOpt3?.let {
            options.add(2, it)
        }
        txtOpt4?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8090"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg
            )
        }
    }


    private fun selectedOptionView(txt: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        txt.setTextColor(Color.parseColor("#363a43"))
        txt.setTypeface(txt.typeface, Typeface.BOLD)
        txt.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_bg
        )


    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.txtOpt1 -> {
                txtOpt1?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.txtOpt2 -> {
                txtOpt2?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.txtOpt3 -> {
                txtOpt3?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.txtOpt4 -> {
                txtOpt4?.let {
                    selectedOptionView(it, 4)
                }
            }

            R.id.submitButton -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val finishIntent = Intent(this, ResultActivity::class.java)
                            finishIntent.putExtra(Constants.USER_NAME, mUserName)
                            finishIntent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            finishIntent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(finishIntent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.corret_option_border_bg)


                    if (mCurrentPosition == mQuestionsList!!.size) {
                        submitButton.text = "Finish"
                    } else {
                        submitButton.text = "next question"
                    }

                    mSelectedOptionPosition = 0

                }
            }

        }
    }


    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                txtOpt1.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                txtOpt2.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                txtOpt3.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                txtOpt4.background = ContextCompat.getDrawable(this, drawableView)
            }

        }
    }

}