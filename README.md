# Oblig 2: Quiz App revision

APK used during testing:

app-debug-androidTest.apk
app-debug.apk

abd commands used in Android Studio:

adb install - installs the APK on the device or emulator.
adb uninstall - uninstalls the APK from the device or emulator.
adb shell am instrument - runs the test cases on the device or emulator.

Description of the espresso test with results

1) The testPlayQuizzButton method in MainActivityTest is testing the functionality
   of the "Start Quizz" button, which is supposed to launch QuizActivity. After Launching
   QuizActivity, the display is checked if it matches the id of QuizActivity.

2 The testExitingQuizReturnsToMainActivity method in MainActivityTest is testing 
   the redirect button back from QuizActivity to MainActivity. After returning to
   MainActivity the display is checked if it matches the id of MainActivity.

4) The insertEntryAndGetEntry test method in QuestionDatabaseTest is testing
   the insertion of an entry to the database.

5) The insertEntryAndDeleteEntry method in QuestionDatabaseTest is testing
   inserting and deleting an entry in the database.

6) testScoreIncreasesOnCorrectAnswer test method in QuizActivityTest is testing
   if the score is incremented by 10 when you select the right answer.

7) testScoreDecreaseOnWrongAnswer test method in QuizActivityTest is testing if
   the score is decremented by 5 when the wrong answer is selected.