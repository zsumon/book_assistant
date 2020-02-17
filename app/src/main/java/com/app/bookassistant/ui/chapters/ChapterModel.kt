package com.app.bookassistant.ui.chapters

class ChapterModel {
    var chapterTitle: String = ""
    var chapterDescripton: String = ""
    var chapterQuestions: List<QuestionModel> = mutableListOf()

    class QuestionModel {
        var questionTitle: String = ""
        var options: MutableList<String> = mutableListOf()
        var correctAnswer: Int = -1
        var explanation: String = ""
    }
}