package com.app.bookassistant.ui.exam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bookassistant.R

class ExamMakerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedChapters: List<Int> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ExamViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_exam, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return selectedChapters.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ExamViewHolder -> {
                holder.bind(selectedChapters[position])
            }
        }
    }

    fun supplyChapters(items: MutableList<Int>) {
        selectedChapters = items
    }

    class ExamViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(chapterNumber: Int) {
            //
        }
    }

}