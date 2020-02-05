package com.app.bookassistant

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bookassistant.ui.chapters.ChapterActivity
import com.app.bookassistant.ui.dashboard.BookListAdapter
import com.app.bookassistant.ui.dashboard.BookModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookListAdapter.OnBookListener {

    private lateinit var bookListAdapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDashboardBookList()
        initDrawers()
    }

    private fun initDrawers() {
        setSupportActionBar(toolbar_bookList)
        val toggle = ActionBarDrawerToggle(
            this,
            dl_bookList,
            toolbar_bookList,
            R.string.app_name,
            R.string.app_name
        )
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.White)

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

        // notify viewModel about click and update from server/repository
    }
}
