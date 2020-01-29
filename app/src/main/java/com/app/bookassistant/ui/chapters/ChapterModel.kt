package com.app.bookassistant.ui.chapters

class ChapterModel {
    var chapterTitle: String = ""
    var questions: List<Question> = mutableListOf()

    class Question {
        var questionTitle: String = ""
        var numberOfOptions: Int = 0
        var questionOptions: List<Option> = mutableListOf()
        var correctOption: Option =
            Option()
    }

    class Option {
        var value: String = ""
    }
}