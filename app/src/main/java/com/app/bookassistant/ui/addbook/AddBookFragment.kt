package com.app.bookassistant.ui.addbook


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.app.bookassistant.R
import kotlinx.android.synthetic.main.fragment_add_book.*

class AddBookFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookName = savedInstanceState?.get("book_title") as String?
        if (bookName != null)
            book_title_editText.setText(bookName)
    }


}
