package com.example.a20questions

import com.opencsv.CSVReader
import java.io.File
import java.io.FileReader
import android.widget.Toast

import android.os.Environment
import java.lang.Exception


class Algorithm(val domain: Domain, questionarreActivity: QuestionarreActivity) {

    var currentQuestion: String = ""
    var mysteryItemName: String? = null
    var potentialAnswers: AnswerState = AnswerState.ManyAnswers

    private lateinit var currentProperty: String
    private lateinit var dataset: ItemDataset

    init {
        // converted to kotlin from origonal java and slightly modified:
        // https://stackoverflow.com/questions/43055661/reading-csv-file-in-android-app
        try {
            val csvfile =
                File(Environment.getExternalStorageDirectory().toString() + "/" +
                        when (this.domain) {
                            Domain.Superheroes -> "superheroes.csv"
                            // add more Domain.other cases here
                            // ex: Domain.A -> "A.csv"
                            else -> Error("The mystery item domain is not supported by `Algorithm.kt`")
                        })
            val reader = CSVReader(FileReader(csvfile.absolutePath))
            dataset = ItemDataset(reader.readAll())
            updateCurrentProperty()
            updateCurrentQuestionFromProperty()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(questionarreActivity,
                "`Algorithm.kt` could not find the specified csvfile or did not recognize the domain",
                Toast.LENGTH_SHORT).show()
        }
        // end copy with modification from stackoverflow
    }

    fun answerCurrentQuestion(answer: Answer) {
        /*
        Adjusts algorithm state based on answer supplied.
        This step should only be used for yes/maybe/no questions

        Filters all items in dataset
        Assign potentialAnswers
        If many potentialAnswers is many:
            Assign next currentQuestion, property, and filter pool of properties
         */

        // early exit for the do almost nothing case
        if (answer == Answer.NOTSURE) {
            // update question
            updateCurrentProperty()
            updateCurrentQuestionFromProperty()
            return
        }

        // remove elements that have a different value in currentProperty than the answer supplied
        val pcurrindex = dataset.propertyNames.indexOf(currentProperty)
        dataset.allItems.forEach {
            if(it.propertyValues[pcurrindex] != (answer == Answer.YES)) {
                dataset.allItems.remove(it)
            }
        }

        potentialAnswers = when(dataset.allItems.size) {
            0 -> AnswerState.NoAnswer
            1 -> AnswerState.OneAnswer
            else -> AnswerState.ManyAnswers
        }

        if (potentialAnswers == AnswerState.OneAnswer) {
            mysteryItemName = dataset.allItems.first().name
        }

        if (potentialAnswers == AnswerState.ManyAnswers) {
            // filter dataset property names to only include ones that are distinct
            dataset.usablePropertyNames.remove(currentProperty)
            dataset.usablePropertyNames = dataset.usablePropertyNames.filter { pname: String ->
                dataset.allItems.all {
                    it.propertyValues[dataset.propertyNames.indexOf(pname)] == dataset.allItems.first().propertyValues[dataset.propertyNames.indexOf(pname)]
                }
            }.toMutableList()

            // update question
            updateCurrentProperty()
            updateCurrentQuestionFromProperty()
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