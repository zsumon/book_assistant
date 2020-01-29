package com.app.bookassistant.ui.dashboard

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bookassistant.data.models.Book

class BookListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var bookList: List<Book> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //        TODO
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class BooklistViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(book: Any) {}
    }

}