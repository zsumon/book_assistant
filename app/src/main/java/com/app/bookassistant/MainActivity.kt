package com.app.bookassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bookassistant.ui.dashboard.BookModel
import com.app.bookassistant.ui.chapters.ChapterActivity
import com.app.bookassistant.ui.dashboard.BookListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookListAdapter.OnBookListener {

    private lateinit var bookListAdapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDashboardBookList()
    }

    private fun initDashboardBookList() {
        dashboardBookListRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            bookListAdapter = BookListAdapter(this@MainActivity)
            adapter = bookListAdapter
        }
        val items = mutableListOf<BookModel>()
        for (i in 0..5) {
            val b = BookModel()
            b.apply {
                title = "Book $i"
                description = "Description of Book $i"
            }
            items.add(b)
        }
        bookListAdapter.supplyBookList(items)
    }

    override fun onBookClick(position: Int) {
        startActivity(Intent(this, ChapterActivity::class.java))
    }
}
