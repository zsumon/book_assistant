package com.app.bookassistant.utils

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import com.app.bookassistant.ui.chapters.ChapterModel
import com.app.bookassistant.ui.dashboard.BookModel
import com.opencsv.CSVReader
import java.io.*
import java.lang.Exception
import kotlin.math.exp


class CSVUtil {
    companion object {
        private fun readFile(file: File): StringBuffer {
            val ret = StringBuffer()
            try {
                val reader = CSVReader(file.reader())
                var nextLine = reader.readNext()
                while (nextLine != null) {
                    nextLine.forEach {
                        ret.append("$it ")
                    }
                    nextLine = reader.readNext()
                }
            } catch (e: IOException) {
                logd("Exception reading csv file: $e")
            }
            return ret
        }

        fun selectCSVFile(activity: AppCompatActivity) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "text/*"
            startActivityForResult(
                activity,
                Intent.createChooser(intent, "Open CSV file for Book"),
                Constants.SELECT_CSV_FILE,
                null
            )
        }

        fun parseBookFromCSV(path: String): BookModel? {
            val file = File(path)
            var book: BookModel? = null

            try {
                val csvReader = CSVReader(file.reader())
                var nextLine = csvReader.readNext()

                while (nextLine != null) {
                    if (nextLine[0] == "book_name") {
                        book = BookModel("id123", nextLine[1], "Description", mutableListOf())
                    } else if (nextLine[0].contains("chapter_")) {
                        val chTitle = nextLine[1]
                        val chDesc = nextLine[2]
                        book?.chapters?.add(ChapterModel().apply {
                            chapterQuestions = readQuestions(csvReader).apply {
                                chapterTitle = chTitle
                                chapterDescripton = chDesc
                            }
                        })
                    }
                    nextLine = csvReader.readNext()
                }
            } catch (e: Exception) {
                println(e.toString())
            }
            return book
        }

        private fun readQuestions(reader: CSVReader): MutableList<ChapterModel.QuestionModel> {

            var line = reader.readNext()
            val questions = mutableListOf<ChapterModel.QuestionModel>()

            while (line != null) {
                val currentQuestion = ChapterModel.QuestionModel().apply {
                    questionTitle = line[1]
                    for (i in 2..line.size - 2) options.add(line[i])
                    correctAnswer = line[line.size - 2].toInt()
                    explanation = line[line.size - 1]
                }
                questions += currentQuestion
                line = reader.readNext()
                if (line == null) break
                if (emptyLine(line)) break
            }

            return questions
        }

        private fun emptyLine(line: Array<String>): Boolean {
            line.forEach {
                if (it.isNotEmpty()) return false
            }
            return true
        }
    }
}