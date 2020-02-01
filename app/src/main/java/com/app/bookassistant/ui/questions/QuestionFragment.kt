package com.app.bookassistant.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bookassistant.R
import com.app.bookassistant.ui.chapters.ChapterModel.QuestionModel
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment(), QuestionAdapter.OnQuestionClickListener {

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var viewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(activity!!).get(QuestionViewModel::class.java)
        viewModel.selectedAnswers.observe(this) {
            //
        }

        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initQuestionRecyclerView()
    }

    private fun initQuestionRecyclerView() {
        val items = mutableListOf<QuestionModel>()
        for (i in 0..5) {
            val qm = QuestionModel()
            qm.apply {

            }
            items.add(qm)
        }
        recyclerView_question.apply {
            layoutManager = LinearLayoutManager(context)
            questionAdapter = QuestionAdapter(this@QuestionFragment)
            adapter = questionAdapter
        }
        questionAdapter.supplyQuestions(items)
    }

    override fun onShowCorrectAnswerClick(questionPosition: Int) {


    }

    override fun onQuestionOptionClick(questionPosition: Int, selectedOption: Int) {

    }
}
