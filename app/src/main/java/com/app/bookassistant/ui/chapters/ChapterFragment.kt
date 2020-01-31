package com.app.bookassistant.ui.chapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bookassistant.R
import kotlinx.android.synthetic.main.fragment_chapter.*


class ChapterFragment : Fragment(), ChapterAdapter.OnChapterListener {

    private lateinit var chapterAdapter: ChapterAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chapter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initChapterRecyclerView()
    }

    private fun initChapterRecyclerView() {
        val items = mutableListOf<ChapterModel>()
        for (i in 0..5) {
            val ch = ChapterModel()
            ch.apply {
                chapterTitle = "Chapter $i"
                chapterDescripton = "Description of Chapter $i"
            }
            items.add(ch)
        }
        chapter_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            chapterAdapter = ChapterAdapter(this@ChapterFragment)
            adapter = chapterAdapter
        }
        chapterAdapter.supplyChapters(items)
    }

    override fun onChapterClick(position: Int) {
        val bundle: Bundle = bundleOf(Pair("book_and_chapter", "Book1"))
        navController.navigate(R.id.action_chapterFragment_to_questionFragment, bundle)
    }


}
