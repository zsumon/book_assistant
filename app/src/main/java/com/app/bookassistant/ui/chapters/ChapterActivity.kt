package com.app.bookassistant.ui.chapters

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bookassistant.R
import kotlinx.android.synthetic.main.activity_chapter.*

class ChapterActivity : AppCompatActivity(), ChapterAdapter.OnChapterListener {

    private lateinit var chapterAdapter: ChapterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

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
            layoutManager = LinearLayoutManager(this@ChapterActivity)
            chapterAdapter = ChapterAdapter(this@ChapterActivity)
            adapter = chapterAdapter
        }
        chapterAdapter.supplyChapters(items)
    }

    override fun onChapterClick(position: Int) {
        Toast.makeText(this, "Clicked: $position", Toast.LENGTH_SHORT).show()
    }
}
