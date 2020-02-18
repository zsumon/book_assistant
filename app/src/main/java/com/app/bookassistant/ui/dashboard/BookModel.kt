package com.app.bookassistant.ui.dashboard

import com.app.bookassistant.ui.chapters.ChapterModel
import java.io.Serializable

data class BookModel(
    var bookId: String,
    var title: String,
    var description: String,
    var chapters: MutableList<ChapterModel> = mutableListOf()
) : Serializable
