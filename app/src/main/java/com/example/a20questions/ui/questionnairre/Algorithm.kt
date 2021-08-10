package com.example.a20questions

import com.opencsv.CSVReader
import android.widget.Toast

import android.os.Environment
import android.util.Log
import com.example.a20questions.ui.questionnairre.QuestionnaireActivity
import java.lang.Exception
import android.R
import java.io.*


class Algorithm(val domain: Domain, questionarreActivity: QuestionnaireActivity) {

    var currentQuestion: String = ""
    var mysteryItemName: String? = null
    var potentialAnswers: AnswerState = AnswerState.ManyAnswers

    private lateinit var currentProperty: String
    private lateinit var dataset: ItemDataset
    private lateinit var previous_allitems: List<SingleItem>

    init {
        // converted to kotlin from origonal java and slightly modified:
        // https://stackoverflow.com/questions/43055661/reading-csv-file-in-android-app
        try {
            Log.d("csv", "step1")
            val filepath = "android.resource://" + questionarreActivity.packageName + "/raw/superheroes.txt"
            Log.d("csv", "step2a: fp=$filepath")
            val inputStream = questionarreActivity.assets.open(when (this.domain) {
                Domain.Superheroes -> "superheroes.csv"
                // add more Domain.other cases here
                // ex: Domain.A -> "A.csv"
                else -> "unknown"
            })
            Log.d("csv", "step2b: inputStream=$inputStream")
            val bufferedReader = inputStream.bufferedReader()
            Log.d("csv", "step2c: bufferedReader=$bufferedReader")
            val reader = CSVReader(bufferedReader)
            Log.d("csv", "step3")
            dataset = ItemDataset(reader.readAll())
            Log.d("csv", "step4 dataset.size = ${dataset.allItems.size}")
            updateCurrentProperty()
            Log.d("csv", "step5")
            updateCurrentQuestionFromProperty()
            Log.d("csv", "step6")
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(questionarreActivity,
                "`Algorithm.kt` could not find the specified csvfile or did not recognize the domain",
                Toast.LENGTH_SHORT).show()
        }
        // end copy with modification from stackoverflow
    }

    fun answerCurrentQuestion(answer: Answer) {
        // Adjusts algorithm state based on answer supplied.
        // This step should only be used for yes/maybe/no questions

        // early exit for the do almost nothing case
        if (answer == Answer.NOTSURE) {
            // update question
            //Log.d("20q", "updating vars")
            updateCurrentProperty()
            updateCurrentQuestionFromProperty()
            //Log.d("20q", "updated vars")
            return
        }

        // Filters all items in dataset and Assign potentialAnswers
        // Assign next currentQuestion, property, and filter pool of properties

        // remove elements that have a different value in currentProperty than the answer supplied
        val pcurrindex = dataset.propertyNames.indexOf(currentProperty)
        Log.d("alg", "filtering ${dataset.allItems.size} dataset elements")
        previous_allitems = dataset.allItems
        dataset.allItems = dataset.allItems.filter {
            it.propertyValues[pcurrindex] == (answer == Answer.YES)
        }
        Log.d("alg", "${dataset.allItems.size} dataset elements remaining")

        potentialAnswers = when(dataset.allItems.size) {
            0 -> AnswerState.NoAnswer
            1 -> AnswerState.OneAnswer
            else -> AnswerState.ManyAnswers
        }

        when(potentialAnswers) {
            AnswerState.NoAnswer -> {
                Log.d("alg", "how did this happen?")
                mysteryItemName = previous_allitems.random().name
                Log.d("alg", "mystery guess = $mysteryItemName")
                potentialAnswers = AnswerState.OneAnswer
            }
            AnswerState.OneAnswer -> {
                mysteryItemName = dataset.allItems.first().name
                Log.d("alg", "mystery solved = $mysteryItemName")
            }
            AnswerState.ManyAnswers -> {
                Log.d("alg", "pre usablePropertyNames.size ${dataset.usablePropertyNames.size}")
                dataset.usablePropertyNames.remove(currentProperty)
                Log.d("alg", "post usablePropertyNames.size ${dataset.usablePropertyNames.size}")

                // filter dataset property names to only include ones that are distinct
                Log.d("alg", "pre usablePropertyNames.size ${dataset.usablePropertyNames.size}")
                dataset.usablePropertyNames = dataset.usablePropertyNames.filter { pname ->
                    val pIndex = dataset.propertyNames.indexOf(pname)
                    when {
                        dataset.allItems.all {
                            it.propertyValues[pIndex]
                        } -> false
                        dataset.allItems.all {
                            !it.propertyValues[pIndex]
                        } -> false
                        else -> true
                    }
                }.toMutableList()
                Log.d("alg", "post usablePropertyNames.size ${dataset.usablePropertyNames.size}")

                // update question
                Log.d("20q", "updating vars")
                updateCurrentProperty()
                updateCurrentQuestionFromProperty()
                Log.d("20q", "updated vars")
            }
        }
    }

    private fun updateCurrentProperty() {
        currentProperty = dataset.usablePropertyNames.random()
    }

    private fun updateCurrentQuestionFromProperty() {
        currentQuestion = "Does it have $currentProperty?"
    }
}

enum class Answer { YES, NOTSURE, NO }

enum class AnswerState {
    NoAnswer,
    OneAnswer,
    ManyAnswers
}

enum class Domain {
    Superheroes,
    // this is the only supported domain right now
}