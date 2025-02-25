package com.app.bookassistant.ui.chapters

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bookassistant.R
import com.app.bookassistant.ui.dashboard.BookModel
import com.app.bookassistant.ui.questions.QuestionFragment
import com.app.bookassistant.utils.Constants
import com.app.bookassistant.utils.toast
import im.dacer.androidcharts.LineView
import kotlinx.android.synthetic.main.fragment_chapter.*
import java.util.ArrayList


class ChapterFragment : Fragment(), ChapterAdapter.OnChapterListener {

    private lateinit var chapterAdapter: ChapterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chapter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookFromBundle = arguments!![Constants.BOOK_ARG_KEY] as BookModel
        initChapterRecyclerView(bookFromBundle)
        initLineChart(view)
    }

    private fun initChapterRecyclerView(selectedBook: BookModel) {

        //  toast("selected book from bundle: ${selectedBook.title}!!")
        val items = selectedBook.chapters

        toast("total chapters: ${items.size}")

        chapter_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            chapterAdapter = ChapterAdapter(this@ChapterFragment)
            adapter = chapterAdapter
            isNestedScrollingEnabled = false
        }
        chapterAdapter.supplyChapters(items)
    }

    private fun initLineChart(view: View) {
        // https://github.com/HackPlan/AndroidCharts#usage
        val bottomList =
            mutableListOf(
                "Exam 1",
                "Exam 2",
                "Exam 3",
                "Exam 4",
                "Exam 5",
                "Exam 6",
                "Exam 7",
                "Exam 8",
                "Exam 9",
                "Exam 10"
            )
        val dataLists: ArrayList<ArrayList<Int>> = ArrayList(ArrayList())

        dataLists.add(ArrayList(mutableListOf(95, 86, 26, 55, 88, 65, 85, 76, 48, 82)))


        val lineView = view.findViewById<LineView>(R.id.line_view)
        lineView.setDrawDotLine(false) //optional
        lineView.setShowPopup(LineView.SHOW_POPUPS_MAXMIN_ONLY) //optional

        lineView.setBottomTextList(bottomList as ArrayList<String>?)
        lineView.setColorArray(intArrayOf(Color.GREEN, Color.CYAN, Color.BLACK, Color.GRAY))
        lineView.setDataList(dataLists) //or lineView.setFloatDataList(floatDataLists)

        lineView.bottom = 0
        lineView.top = 0


    }

    override fun onChapterClick(position: Int) {
        val bundle: Bundle = bundleOf(Pair("book_and_chapter", "Book1"))

        /// transaction to Question fragment
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.chapter_fragment_container_frameLayout, QuestionFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
