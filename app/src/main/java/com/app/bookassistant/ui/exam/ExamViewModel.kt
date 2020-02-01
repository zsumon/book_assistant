package com.app.bookassistant.ui.exam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bookassistant.ui.chapters.ChapterModel.*

class TestViewModel : ViewModel() {
    var testQuestionList: MutableLiveData<List<QuestionModel>> = MutableLiveData()

}