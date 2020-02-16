package com.app.bookassistant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bookassistant.ui.chapters.ChapterActivity
import com.app.bookassistant.ui.dashboard.AvailableBookAdapter
import com.app.bookassistant.ui.dashboard.BookListAdapter
import com.app.bookassistant.ui.dashboard.BookModel
import com.app.bookassistant.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookListAdapter.OnBookListener,
    AvailableBookAdapter.OnAvailableBookListener {

    private lateinit var bookListAdapter: BookListAdapter
    private lateinit var availableBookAdapter: AvailableBookAdapter
    private lateinit var availableBooks: MutableList<String>
    private lateinit var enrolleBooks: MutableList<BookModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEnrolledBookList()
        initDrawers()

        initFab()

        initAvailableCourseList()
    }

    private fun initAvailableCourseList() {
        available_courses_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            availableBookAdapter = AvailableBookAdapter(this@MainActivity)
            adapter = availableBookAdapter
        }

        val items = mutableListOf("Data Mining", "Computer Graphics", "Introduction to Algorithms")
        availableBooks = items
        availableBookAdapter.supplyList(items)

    }

    private fun initFab() {
        /*fab_add_book.setOnClickListener {
            Toast.makeText(this, "Add new Book", Toast.LENGTH_SHORT).show()
        }*/
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

    private fun initEnrolledBookList() {
        dashboardBookListRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            bookListAdapter = BookListAdapter(this@MainActivity)
            adapter = bookListAdapter
        }
        enrolleBooks = mutableListOf()

        var b = BookModel("cse101", "Computer Fundamentals", "Introduction to computers", null)
        enrolleBooks.add(b)
        b = BookModel("cse102", "Programming & Problem Solving", "Programming in C", null)
        enrolleBooks.add(b)
        b = BookModel("cse103", "Math", "Basic Math", null)
        enrolleBooks.add(b)
        b = BookModel("cse104", "Data Structures", "Data Structures", null)
        enrolleBooks.add(b)
        bookListAdapter.supplyBookList(enrolleBooks)
    }

    override fun onBookClick(position: Int) {

        val intent = Intent(this, ChapterActivity::class.java)
        var clickedBook = "DEFAULT"
        try {
            clickedBook = enrolleBooks[position].title
        } catch (e: Exception) {
            Log.d("TAG", e.toString())
        }
        intent.putExtra("book_name", clickedBook)
        startActivity(intent)
    }

    override fun onMoreButtonClick(bookPosition: Int, menuId: Int) {
        Toast.makeText(this, "$bookPosition", Toast.LENGTH_SHORT).show()

        // notify viewModel about click and update from server/repository
    }

    override fun onAvailableBookClick(position: Int) {
        // now add this to enrolled class..
        var str = ""
        try {
            str = availableBooks[position]
        } catch (e: Exception) {
            Log.d("TAG", e.toString())
        }
        availableBookAdapter.removeItem(position)
        val b = BookModel(
            "id_123",
            str,
            str, null
        )

        enrolleBooks.add(b)
        bookListAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file
            toast("Selected: $selectedFile");
        }
    }

    fun uploadBook(view: View) {
        // upload new book

        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
    }
}
