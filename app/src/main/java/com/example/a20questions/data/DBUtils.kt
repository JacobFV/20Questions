package com.example.a20questions.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// thanks: https://medium.com/@tonia.tkachuk/android-app-example-using-room-database-63f7091e69af
suspend fun rePopulateDB(database: AppDatabase?) {
    database?.let { db: AppDatabase ->
        withContext(Dispatchers.IO) {
            val userDao: UserDao = db.userDao()
            val savedGameDao: SavedGameDao = db.savedGameDao()

            userDao.deleteAll()
            savedGameDao.deleteAll()

            val userMohammed = User(1, "Mohammed", "1234")
            val userJacob = User(2, "Jacob", "1234")
            val userManish = User(3, "Manish", "1234")
            val userArwa = User(4, "Arwa", "1234")
            userDao.insertUsers(userMohammed, userJacob, userManish, userArwa)


            val savedGame1 = SavedGame(1, 1628524385, "Aug 9, 2021 10:00am",
                exampleQuestionsAndAnswers1, true, userManish.username)
            val savedGame2 = SavedGame(2, 1628524399, "Aug 9, 2021 10:05am",
                exampleQuestionsAndAnswers1, true, userJacob.username)
            val savedGame3 = SavedGame(3, 1628524801, "Aug 9, 2021 11:00am",
                exampleQuestionsAndAnswers2, false, userJacob.username)
            val savedGame4 = SavedGame(4, 1628525002, "Aug 9, 2021 11:35am",
                exampleQuestionsAndAnswers2, false, userJacob.username)
            savedGameDao.insertSavedGames(savedGame1, savedGame2, savedGame3, savedGame4)
        }
    }
}
// end thanks


val exampleQuestionsAndAnswers1 = (
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


val exampleQuestionsAndAnswers2 = (
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


val exampleQuestionsAndAnswers3 = (
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