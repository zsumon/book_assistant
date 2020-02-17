package com.app.bookassistant.ui.addbook


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.app.bookassistant.R
import com.app.bookassistant.ui.dashboard.BookModel
import kotlinx.android.synthetic.main.fragment_add_book.*

class AddBookFragment(private val onFinalizeUploadListener: OnFinalizeUploadListener) :
    DialogFragment() {

    lateinit var uploadedBook: BookModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*if (arguments != null) {
            val bookName = arguments!!.getString("book_title")
            Toast.makeText(activity, "bundle: text: ${bookName}", Toast.LENGTH_LONG).show()
            if (bookName != null)
                book_title_editText.setText(bookName)
        }*/

        book_title_editText.setText(uploadedBook.title)
        total_chapters_textView.text = "Total Chapters:  ${uploadedBook.chapters.size}"

        var totalQuestion = 0
        for (i in uploadedBook.chapters) {
            totalQuestion += i.chapterQuestions.size
        }
        total_questions_textView.text = "Total Questions:  $totalQuestion"

        finalize_book_upload.setOnClickListener {
            onFinalizeUploadListener.onFinalUploadClick(uploadedBook.apply {
                title = book_title_editText.text.toString()
            })
            dialog?.dismiss()
        }

    }

    fun setUploadedBookInfo(book: BookModel) {
        uploadedBook = book
    }

    interface OnFinalizeUploadListener {
        fun onFinalUploadClick(book: BookModel) // need to have some good listener/interface naming conventions..
    }
}
