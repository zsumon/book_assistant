package com.app.bookassistant.ui.chapters

class ChapterModel {
    var chapterTitle: String = ""
    var chapterDescripton: String = ""
    var questionModels: List<QuestionModel> = mutableListOf()

    class QuestionModel {
        var questionTitle: String = ""
        var numberOfOptions: Int = 0
        var questionOptionModels: List<OptionModel> = mutableListOf()
        var correctOptionModel: OptionModel =
            OptionModel()
    }

    class OptionModel {
        var value: String = ""
    }
}