package com.app.bookassistant.ui.chapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bookassistant.R
import com.app.bookassistant.ui.questions.QuestionFragment
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_chapter.*


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

        val title = "Chapters"
        (activity as AppCompatActivity?)?.supportActionBar?.title = title

        initChapterRecyclerView()
        initPieChart()
    }

    private fun initChapterRecyclerView() {
        val items = mutableListOf<ChapterModel>()
        for (i in 0..10) {
            val ch = ChapterModel()
            ch.apply {
                chapterTitle = "Chapter ${i + 1}"
                chapterDescripton = "Description of Chapter ${i + 1}"
            }
            items.add(ch)
        }
        chapter_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            chapterAdapter = ChapterAdapter(this@ChapterFragment)
            adapter = chapterAdapter
            isNestedScrollingEnabled = false
        }
        chapterAdapter.supplyChapters(items)
    }

    private fun initPieChart(): Unit {

        // https://www.studytutorial.in/android-pie-chart-using-mpandroid-library-tutorial
        val yvalues = ArrayList<Entry>()
        yvalues += Entry(8f, 0)
        yvalues += Entry(15f, 1)
        yvalues += Entry(12f, 2)
        yvalues += Entry(25f, 3)
        yvalues += Entry(17f, 4)

        val dataSet = PieDataSet(yvalues, "Recent Scores")

        val xVals = ArrayList<String>()

        xVals.add("Exam 1")
        xVals.add("Exam 2")
        xVals.add("Exam 3")
        xVals.add("Exam 4")
        xVals.add("Exam 5")

        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS)

        val data = PieData(xVals, dataSet)
        chapter_performance_piechart.data = data
        chapter_performance_piechart.setDescription("Recent Exam Scores")

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
