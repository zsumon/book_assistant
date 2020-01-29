package com.app.bookassistant.ui.chapters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.bookassistant.R
import kotlinx.android.synthetic.main.activity_chapter.*
import kotlinx.android.synthetic.main.layout_chapter.*
import java.util.concurrent.CompletableFuture

class ChapterActivity : AppCompatActivity(), ChapterAdapter.OnChapterListener {

    private lateinit var chapterAdapter: ChapterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

        initChapterRecyclerView()
    }


    fun initChapterRecyclerView() {
        val items = mutableListOf<ChapterModel>()
        for (i in 0..5) {
            val ch = ChapterModel()
            ch.apply {
                chapterTitle = "Chapter $i"
                chapterDescripton = "Description of Chapter $i"
            }
            items.add(ch)
        }
//        println("size is: ${items.size}")
        Log.d("TAG", "Size_is: " + items.size.toString())

        chapter_recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ChapterActivity)
            chapterAdapter = ChapterAdapter(this@ChapterActivity)
            adapter = chapterAdapter
        }
        chapterAdapter.supplyChapters(items)
    }

    override fun onChapterClick(position: Int) {
        // clicked on chapter at `position`
        Toast.makeText(this, "Clicked: $position", Toast.LENGTH_SHORT).show()
    }
}
