package com.app.bookassistant.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bookassistant.R

class AvailableCoursesAdapter(private val onAvailableBookListener: OnAvailableBookListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var availableCourse: MutableList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_books, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return availableCourse.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            onAvailableBookListener.onAvailableBookClick(position)
        }

    }

    fun supplyList(items: MutableList<String>) {
        availableCourse = items
    }

    fun removeItem(position: Int) {
        if (position >= 0 && position < availableCourse.size) {
            availableCourse.removeAt(position)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }

    }

    interface OnAvailableBookListener {
        fun onAvailableBookClick(position: Int)
    }

}