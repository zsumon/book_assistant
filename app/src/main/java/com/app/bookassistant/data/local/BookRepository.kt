package com.app.bookassistant.data.local

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
        var b = BookModel(
            "cse101",
            "Computer Fundamentals",
            "Introduction to computers",
            mutableListOf()
        )
        enrolledBooks.add(b)
        b = BookModel(
            "cse102",
            "Programming & Problem Solving",
            "Programming in C",
            mutableListOf()
        )
        //  enrolledBooks.add(b)
        b = BookModel("cse103", "Math", "Basic Math", mutableListOf())
        enrolledBooks.add(b)
        b = BookModel("cse104", "Data Structures", "Data Structures", mutableListOf())
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