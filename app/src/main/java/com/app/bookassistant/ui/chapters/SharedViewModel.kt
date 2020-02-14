package com.app.bookassistant.ui.chapters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bookassistant.ui.dashboard.BookModel


// shared view model for chapter & questions.. and may be more like: tests.. any new features etc
class SharedViewModel : ViewModel() {
    var selectedBook: MutableLiveData<BookModel> = MutableLiveData()
    var selectedChapter: MutableLiveData<ChapterModel> = MutableLiveData()

}