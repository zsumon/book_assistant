package com.app.bookassistant.ui.exam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.app.bookassistant.R
import kotlinx.android.synthetic.main.fragment_exam.*

class ExamMakerFragment : Fragment() {

    //TODO("Set up Bottom Nav For test & chapter navigation.. in chapter activity")

    private lateinit var examMakerAdapter: ExamMakerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exam, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initChapterListRecView()

    }

    fun initChapterListRecView(): Unit {
        val items = mutableListOf<Int>()
        for (i in 0..3) {
            val ch = i + 1
            items.add(ch)
        }
        exam_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            examMakerAdapter = ExamMakerAdapter()
            adapter = examMakerAdapter
        }
        examMakerAdapter.supplyChapters(items)
    }
}
