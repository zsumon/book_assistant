package com.app.bookassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bookassistant.data.models.Book
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
        val items = mutableListOf<Book>()
        for (i in 0..5) {
            val b = Book()
            b.apply {
                title = "Book $i"
                description = "Description of Book $i"
            }
            items.add(b)
        }
        bookListAdapter.supplyBookList(items)
    }

    override fun onBookClick(position: Int) {
        Toast.makeText(this, "Clicked on: $position", Toast.LENGTH_SHORT).show()
    }
}
