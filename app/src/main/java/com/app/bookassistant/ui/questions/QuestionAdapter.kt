package com.app.bookassistant.ui.questions

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bookassistant.R
import com.app.bookassistant.ui.chapters.ChapterModel.QuestionModel
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionAdapter(private val onQuestionClickListener: OnQuestionClickListener) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    private var allQuestions: List<QuestionModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_question,
                parent,
                false
            ), onQuestionClickListener
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
        itemView: View,
        private val onQuestionClickListener: OnQuestionClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.button_show_correct_answer.setOnClickListener(this)
        }

        fun bind(questionModel: QuestionModel) {
            // TODO
        }

        override fun onClick(v: View?) {
            if (v?.id == R.id.button_show_correct_answer) {
                /// show/hide correct answer & explanations
                onQuestionClickListener.onShowCorrectAnswerClick(adapterPosition)
                toggleCorrectAnswer()
            }
        }

        private fun toggleCorrectAnswer() {
            if (itemView.button_show_correct_answer.text == "Show Correct Answer") {
                itemView.button_show_correct_answer.text = "Hide Correct Answer"

                itemView.text_correct_answer.visibility = View.VISIBLE
                itemView.text_answer_explanation.visibility = View.VISIBLE
                itemView.text_answer_explanation.text = "Explanation: None"
                itemView.text_correct_answer.text = "Correct answer is: Option 1"
            } else {
                itemView.button_show_correct_answer.text = "Show Correct Answer"
                itemView.text_correct_answer.visibility = View.GONE
                itemView.text_answer_explanation.visibility = View.GONE
            }
        }
    }

    interface OnQuestionClickListener {
        fun onShowCorrectAnswerClick(questionPosition: Int)
        fun onQuestionOptionClick(questionPosition: Int, selectedOption: Int)
    }

}