package com.app.bookassistant.ui.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bookassistant.ui.chapters.ChapterModel.*

class TestViewModel : ViewModel() {
    var testQuestionList: MutableLiveData<List<QuestionModel>> = MutableLiveData()

}