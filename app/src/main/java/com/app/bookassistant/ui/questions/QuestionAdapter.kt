package com.app.bookassistant.ui.questions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bookassistant.R
import com.app.bookassistant.ui.chapters.ChapterModel.QuestionModel

class QuestionAdapter(private val onQuestionOptionClickListener: OnQuestionOptionClickListener) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    private var allQuestions: List<QuestionModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_question,
                parent,
                false
            ), onQuestionOptionClickListener
        )
    }

    override fun getItemCount(): Int {
        return allQuestions.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {

    }

    fun supplyQuestions(items: List<QuestionModel>) {
        allQuestions = items
    }

    class QuestionViewHolder(
        private val itemView: View,
        private val onQuestionOptionClickListener: OnQuestionOptionClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(questionModel: QuestionModel) {
            // TODO
        }

    }

    interface OnQuestionOptionClickListener {
        fun onQuestionOptionClick(position: Int)
    }

}