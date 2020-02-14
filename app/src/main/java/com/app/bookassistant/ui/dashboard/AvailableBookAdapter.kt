package com.app.bookassistant.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bookassistant.R
import kotlinx.android.synthetic.main.item_available_book.view.*

class AvailableBookAdapter(private val onAvailableBookListener: OnAvailableBookListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var availableBook: MutableList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AvailableViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_available_book, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return availableBook.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is AvailableViewHolder -> {
                holder.bind(availableBook[position])
                holder.itemView.setOnClickListener {
                    onAvailableBookListener.onAvailableBookClick(position)
                }
            }
        }

    }

    fun supplyList(items: MutableList<String>) {
        availableBook = items
    }

    fun removeItem(position: Int) {
        if (position >= 0 && position < availableBook.size) {
            availableBook.removeAt(position)
            notifyDataSetChanged()
        }
    }

    class AvailableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(bookName: String) {
            itemView.available_book_item_title.text = bookName
        }
    }

    interface OnAvailableBookListener {
        fun onAvailableBookClick(position: Int)
    }

}