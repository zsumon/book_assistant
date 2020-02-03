package com.app.bookassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        var b = BookModel("cse101", "Computer Fundamentals", "Introduction to computers", null)
        items.add(b)
        b = BookModel("cse102", "Programming & Problem Solving", "Programming in C", null)
        items.add(b)
        b = BookModel("cse103", "Math", "Basic Math", null)
        items.add(b)
        bookListAdapter.supplyBookList(items)
    }

    override fun onBookClick(position: Int) {
        startActivity(Intent(this, ChapterActivity::class.java))
    }

    override fun onMoreButtonClick(bookPosition: Int, menuId: Int) {
        Toast.makeText(this, "$bookPosition -- $menuId", Toast.LENGTH_SHORT).show()
    }
}
