package com.chiamaka.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class SurveyActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mUserName : String? = null
    private var mCorrectAnswers: Int = 0

    private var mResponseOne: String? = null
    private var mResponseTwo: String? = null
    private var mResponseThree: String? = null
    private var mResponseFour: String? = null
    private var mResponseFive: String? = null

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView?= null
    private var ivImage : ImageView? = null

    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mResponseOne = intent.getStringExtra(Constants.RESPONSE_ONE)
        mResponseTwo = intent.getStringExtra(Constants.RESPONSE_ONE)
        mResponseThree = intent.getStringExtra(Constants.RESPONSE_ONE)
        mResponseFour = intent.getStringExtra(Constants.RESPONSE_ONE)
        mResponseFive = intent.getStringExtra(Constants.RESPONSE_ONE)

        progressBar = findViewById(R.id.progress_bar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        mQuestionsList = Constants.getQuestions()


        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
        setQuestion()
    }


    private fun setQuestion() {
        defaultOptionsView()
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if (mCurrentPosition == mQuestionsList!!.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }


    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvOptionOne?.let{
            options.add(0, it)
        }
        tvOptionTwo?.let{
            options.add(1, it)
        }
        tvOptionThree?.let{
            options.add(2, it)
        }
        tvOptionFour?.let{
            options.add(3, it)
        }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }


    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.default_option_border_bg
        )
    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_option_one -> {
                tvOptionOne?.let{
                    selectedOptionView(it, 1)
                }
            }
            R.id.tv_option_two -> {
                tvOptionTwo?.let{
                    selectedOptionView(it, 2)
                }
            }
            R.id.tv_option_three -> {
                tvOptionThree?.let{
                    selectedOptionView(it, 3)
                }
            }
            R.id.tv_option_four -> {
                tvOptionFour?.let{
                    selectedOptionView(it, 4)
                }
            }
            R.id.btn_submit -> {
                answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                            //TODO: Save the selected position
                        } else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWER, mUserName)

                        intent.putExtra(Constants.RESPONSE_ONE, mResponseOne)
                        intent.putExtra(Constants.RESPONSE_TWO, mResponseTwo)
                        intent.putExtra(Constants.RESPONSE_THREE, mResponseThree)
                        intent.putExtra(Constants.RESPONSE_FOUR, mResponseFour)
                        intent.putExtra(Constants.RESPONSE_FIVE, mResponseFive)

                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                        startActivity(intent)
                        finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition-1)
                    generateResponses(question!!.id, mSelectedOptionPosition)

                    if (mCurrentPosition == mQuestionsList!!.size){
                        btnSubmit?.text == "FINISH"
                    }else{
                        btnSubmit?.text = "NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0


                }
            }
        }
    }
    private fun generateResponses(questionId: Int, optionPos:Int){

        if(questionId == 1){
            when(optionPos){
                1 -> mResponseOne = "In fitness, you need to think about the WHY because it gives a sense of direction. As an ectomorph, is your body type influenced genetic or diet or activity levels or illness or nothing at all. "
                2 -> mResponseOne = "In fitness, you need to think about the WHY because it gives a sense of direction. As a mesomorph, is your body type influenced genetic or diet or activity levels or illness or nothing at all. "
                3 -> mResponseOne = "In fitness, you need to think about the WHY because it gives a sense of direction. As an endomorph, is your body type influenced genetic or diet or activity levels or illness or nothing at all. "
                4 -> mResponseOne = "In fitness, you need to think about the WHY because it gives a sense of direction. For instance, is your body type influenced genetic or diet or activity levels or illness or nothing at all. "
            }
        } else if(questionId == 2){
            when(optionPos) {
                1 -> mResponseTwo = "Since you are interested in gaining muscles, you need to prioritze protein intake and strategic exercises that engage your body region of interest. "
                2 -> mResponseTwo = "Fat loss is easily misunderstood in body building as it gives unique results across people. Nonetheless, its an interesting process where internal fat deposits are reduced in a top to bottom form. "
                3 -> mResponseTwo = "Body maintenance is very valuable, and it expands through people of varying body states with each needing a maintenance regimen. For instance, an althete needs a more intense maintenance routine than a lean ectomorph. "
                4 -> mResponseTwo = "Having no body goals is the pinnacle of fun at the gym. It means engaging your mind, being at peace with your current and future state and just exploring your curiosity. "
            }
        } else if(questionId == 3){
            when(optionPos) {
                1 -> mResponseThree = "You selected a 30 day duration. With such short time, you might not observe significant results since your body is still adjusting to the new lifestyle. "
                2 -> mResponseThree = "For a 50 day duration, you would need a high intensity workout routine before results can be observed, but you would also need to set time for rest and sleep. "
                3 -> mResponseThree = "A 70 day duration would allow to set up pace with your body as you gradually increase in rep count and weight. This way you become more accustomed to your routine. "
                4 -> mResponseThree = "Partaking in a 100 day challenge will put you on a right track to your goals, especially when done with the rightroutine, intensity and diet. "
            }
        } else if(questionId == 4){
            when(optionPos) {
                1 -> mResponseFour = "Being on a restrictive vegan diet means that you would need to explore healthy and efficient alternatives for valuable nutrients that help to maintain a sustainable fitness lifestyle "
                2, 3 -> mResponseFour = "As you explore healthy and efficient alternatives for valuable nutrients that help to maintain a sustainable fitness lifestyle, it is important that you avoid processed food with unhealthy caloric to mineral ratio "
                4 -> "Having no restrictive dietary preferences gives wider option for food and nutrients Take advantage of that and fuel your strength!"
            }
        } else if(questionId == 5){
            when(optionPos) {
                1 -> "high"
                2 -> "mid"
                3 -> "low"
                4 -> "fluctuates"
            }
        }
    }

    private fun answerView(answer:Int, drawableView: Int){
        when(answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this@SurveyActivity, drawableView
                )
            }
            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this@SurveyActivity, drawableView
                )
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this@SurveyActivity, drawableView
                )
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this@SurveyActivity, drawableView
                )
            }
        }
    }
}