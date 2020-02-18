package com.app.bookassistant.data.local

import com.app.bookassistant.ui.chapters.ChapterModel
import com.app.bookassistant.ui.dashboard.BookModel

class BookRepository private constructor() {
    companion object {
        private var instance: BookRepository? = null
        fun getInstance(): BookRepository {
            if (instance == null) {
                instance = BookRepository()
                return instance!!
            }
            return instance!!
        }
    }

    fun getEnrolledBooks(): MutableList<BookModel> {
        val enrolledBooks = mutableListOf<BookModel>()

        val chapters = mutableListOf(ChapterModel().apply {
            chapterTitle = "Chapter 1"
            chapterDescripton = "Description of chapter 1"
            chapterQuestions = mutableListOf(ChapterModel.QuestionModel().apply {
                questionTitle = "Question 1"
            })
        })

        var b = BookModel(
            "cse101",
            "Computer Fundamentals",
            "Introduction to computers",
            chapters
        )
        enrolledBooks.add(b)
        /*b = BookModel(
            "cse102",
            "Programming & Problem Solving",
            "Programming in C",
            mutableListOf()
        )
        enrolledBooks.add(b)*/
        b = BookModel(
            "cse103",
            "Math",
            "Basic Math",
            chapters
        )
        enrolledBooks.add(b)
        b = BookModel("cse104", "Data Structures", "Data Structures", chapters)
        enrolledBooks.add(b)

        return enrolledBooks
    }

    fun getAvailableBooks(): MutableList<BookModel> {
        val availableBooks = mutableListOf<BookModel>()
        var b = BookModel(
            "cse401",
            "Data Mining",
            "Data Mining & Big Data",
            mutableListOf()
        )
        availableBooks.add(b)
        b = BookModel(
            "cse402",
            "Computer Graphics",
            "GUIs & shapes, OpenGL",
            mutableListOf()
        )
        availableBooks.add(b)

        return availableBooks
    }
}