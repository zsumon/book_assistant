package com.app.bookassistant.ui.chapters


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bookassistant.R
import kotlinx.android.synthetic.main.fragment_chapter.*


class ChapterFragment : Fragment(), ChapterAdapter.OnChapterListener {

    private lateinit var chapterAdapter: ChapterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
//        val chapterRecyclerView: RecyclerView = rootView.findViewById(R.id.chapter_recyclerView)
        chapter_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            chapterAdapter = ChapterAdapter(this@ChapterFragment)
            adapter = chapterAdapter
        }
        chapterAdapter.supplyChapters(items)
    }

    override fun onChapterClick(position: Int) {
        Toast.makeText(context, "Clicked: $position", Toast.LENGTH_SHORT).show()
        /// make transition to question fragment...
    }


}
