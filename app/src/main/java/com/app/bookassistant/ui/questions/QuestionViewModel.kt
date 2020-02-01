package com.app.bookassistant.ui.questions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bookassistant.ui.chapters.ChapterModel

class QuestionViewModel : ViewModel() {

    lateinit var questionList: MutableLiveData<List<ChapterModel.QuestionModel>>

}