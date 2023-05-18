package com.chiamaka.quizapp

object Constants {
    const val USER_NAME: String = "user_name"

    const val RESPONSE_ONE: String = "response_one"
    const val RESPONSE_TWO: String = "response_two"
    const val RESPONSE_THREE: String = "response_three"
    const val RESPONSE_FOUR: String = "response_four"
    const val RESPONSE_FIVE: String = "response_five"

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWER: String = "correct_answers"


    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val que1 = Question(
            1, "What body type are you?",
            R.drawable.body_sizes,
            "Ectomorph",
            "MesoMorph",
            "Entomorph",
            "Skip",
            1,

        )
        questionsList.add(que1)


        val que2 = Question(
            2,
            "What is your body goals? ",
            R.drawable.fitness_goals,
            "To gain muscles",
            "To lose fat",
            "To maintain body composition",
            "I have no body goals",
            1
        )
        questionsList.add(que2)

        val que3 = Question(
            3,
            "What duration of Project 50 do you think you can commit to? ",
            R.drawable.clock,
            "Up to 30 days",
            "Up to 50 days",
            "Up to 70 days",
            "Up to 100 days",
            1
        )
        questionsList.add(que3)

        val que4 = Question(
            4,
            "What is your dietary preference? ",
            R.drawable.foods,
            "Vegan",
            "Vegetarian",
            "Prescaterian",
            "No dietary preference",
            1
        )
        questionsList.add(que4)

        val que5 = Question(
            5,
            "What is your daily activity level like? ",
            R.drawable.reg_day,
            "High activity (eg. high strength and agility)",
            "Medium actvity (eg. walking around often)",
            "Low activity (eg mostly sitting at desk",
            "My activity level fluctuates",
            1
        )
        questionsList.add(que5)
        return questionsList
    }
}