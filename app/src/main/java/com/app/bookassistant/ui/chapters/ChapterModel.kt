package com.app.bookassistant.ui.chapters

import java.io.Serializable

class ChapterModel : Serializable {
    var chapterTitle: String = ""
    var chapterDescripton: String = ""
    var chapterQuestions: MutableList<QuestionModel> = mutableListOf()

    class QuestionModel : Serializable {
        var questionTitle: String = ""
        var options: MutableList<String> = mutableListOf()
        var correctAnswer: Int = -1
        var explanation: String = ""
    }
}