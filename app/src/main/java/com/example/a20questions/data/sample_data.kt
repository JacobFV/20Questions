package com.example.a20questions.data

val userMohammed = User(1, "Mohammed", "1234")
val userJacob = User(2, "Jacob", "1234")
val userManish = User(3, "Manish", "1234")
val userArwa = User(4, "Arwa", "1234")

const val exampleQuestionsAndAnswers1 = (
        "Does it have Spicy? No," +
                "Does it have Sweet? Yes," +
                "Does it have Salty? No," +
                "Does it have Bitter? Yes," +
                "Does it have Savory? No," +
                "Does it have Rosemary? Yes," +
                "Does it have Cayan? Yes," +
                "Does it have Chili? No," +
                "Does it have Sugar? Yes," +
                "Does it have Lime? Yes," +
                "Does it have Lemon? No," +
                "Does it have French Fries? Yes," +
                "Does it have Cheese? Yes," +
                "Does it have Chicken? No," +
                "Does it have Beef? No," +
                "Does it have Bacon? No," +
                "Does it have Lettuce? Yes," +
                "Does it have Pickle? Yes," +
                "Does it have Tomato? No," +
                "Does it have Bread? Yes"
        )


const val exampleQuestionsAndAnswers2 = (
        "Does it have Brown? No," +
                "Does it have Red? Yes," +
                "Does it have Orange? No," +
                "Does it have Yellow? Yes," +
                "Does it have Lime? No," +
                "Does it have Green? Yes," +
                "Does it have Cyan? Yes," +
                "Does it have Blue? No," +
                "Does it have Indigo? Yes," +
                "Does it have Violet? Yes," +
                "Does it have Magenta? No," +
                "Does it have Pink? Yes," +
                "Does it have Cheese? Yes," +
                "Does it have Chicken? No," +
                "Does it have Beef? No," +
                "Does it have Bacon? No," +
                "Does it have Lettuce? Yes," +
                "Does it have Pickles? Yes," +
                "Does it have Tomatoes? No," +
                "Does it have Bread? Yes"
        )


const val exampleQuestionsAndAnswers3 = (
        "Does it have Brown? No," +
                "Does it have Red? Yes," +
                "Does it have Orange? No," +
                "Does it have Yellow? Yes," +
                "Does it have Lime? No," +
                "Does it have Green? Yes," +
                "Does it have Cyan? Yes," +
                "Does it have Blue? No," +
                "Does it have Indigo? Yes," +
                "Does it have Violet? Yes," +
                "Does it have Magenta? No," +
                "Does it have Pink? Yes," +
                "Does it have Squares? Yes," +
                "Does it have Circles? No," +
                "Does it have Diamonds? No," +
                "Does it have Stripes? No," +
                "Does it have Polka Dots? Yes," +
                "Does it have Crescents? Yes," +
                "Does it have Diagonal Lines? No," +
                "Does it have Straight Lines? Yes"
        )

val savedGame1 = SavedGame(1, 1628524385, "Aug 9, 2021 10:00am",
    exampleQuestionsAndAnswers1, true, 20, userManish.username)
val savedGame2 = SavedGame(2, 1628524399, "Aug 9, 2021 10:05am",
    exampleQuestionsAndAnswers1, true, 20, userJacob.username)
val savedGame3 = SavedGame(3, 1628524801, "Aug 9, 2021 11:00am",
    exampleQuestionsAndAnswers2, false, 20, userJacob.username)
val savedGame4 = SavedGame(4, 1628525002, "Aug 9, 2021 11:35am",
    exampleQuestionsAndAnswers2, false, 20, userJacob.username)
val savedGame5 = SavedGame(5, 1628524387, "Aug 19, 2021 10:00am",
    exampleQuestionsAndAnswers1, true, 20, userManish.username)
val savedGame6 = SavedGame(6, 1628524394, "Aug 19, 2021 10:05am",
    exampleQuestionsAndAnswers1, true, 20, userJacob.username)
val savedGame7 = SavedGame(7, 1628524803, "Aug 19, 2021 11:00am",
    exampleQuestionsAndAnswers2, false,20,  userJacob.username)
val savedGame8 = SavedGame(8, 1628525005, "Aug 19, 2021 11:35am",
    exampleQuestionsAndAnswers2, false, 20, userJacob.username)
