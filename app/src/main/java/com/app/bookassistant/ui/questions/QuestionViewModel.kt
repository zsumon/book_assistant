package com.app.bookassistant.ui.questions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bookassistant.ui.chapters.ChapterModel

class QuestionViewModel : ViewModel() {

    var questionList: MutableLiveData<List<ChapterModel.QuestionModel>> = MutableLiveData()
    var selectedAnswers: MutableLiveData<List<Pair<Int, Int>>> = MutableLiveData()
    var correctAnswerVisibility: MutableLiveData<List<Pair<Int, Int>>> = MutableLiveData()


}