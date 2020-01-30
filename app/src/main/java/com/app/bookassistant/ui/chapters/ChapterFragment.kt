package com.app.bookassistant.ui.chapters


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.bookassistant.R


class ChapterFragment : Fragment(), ChapterAdapter.OnChapterListener {

    private lateinit var chapterAdapter: ChapterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_chapter, container, false)
        initChapterRecyclerView(rootView)

        return rootView
    }

    private fun initChapterRecyclerView(rootView: View) {
        val items = mutableListOf<ChapterModel>()
        for (i in 0..5) {
            val ch = ChapterModel()
            ch.apply {
                chapterTitle = "Chapter $i"
                chapterDescripton = "Description of Chapter $i"
            }
            items.add(ch)
        }

        val chapterRecyclerView: RecyclerView = rootView.findViewById(R.id.chapter_recyclerView)
        chapterRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            chapterAdapter = ChapterAdapter(this@ChapterFragment)
            adapter = chapterAdapter
        }
        chapterAdapter.supplyChapters(items)
    }

    override fun onChapterClick(position: Int) {
        Toast.makeText(context, "Clicked: $position", Toast.LENGTH_SHORT).show()
    }


}
