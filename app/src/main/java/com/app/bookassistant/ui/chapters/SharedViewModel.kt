package com.app.bookassistant.ui.chapters

import androidx.lifecycle.ViewModel
import com.app.bookassistant.ui.dashboard.BookModel


// shared view model for chapter & questions.. and may be more like: tests.. any new features etc
class SharedViewModel : ViewModel() {
    private lateinit var selectedBook: BookModel
    private lateinit var selectedChapter: ChapterModel

}