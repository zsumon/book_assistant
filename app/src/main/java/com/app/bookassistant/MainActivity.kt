package com.app.bookassistant

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bookassistant.ui.addbook.AddBookFragment
import com.app.bookassistant.ui.chapters.ChapterActivity
import com.app.bookassistant.ui.dashboard.AvailableBookAdapter
import com.app.bookassistant.ui.dashboard.BookListAdapter
import com.app.bookassistant.ui.dashboard.BookModel
import com.app.bookassistant.utils.CSVUtil
import com.app.bookassistant.utils.Constants
import com.app.bookassistant.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity(), BookListAdapter.OnBookListener,
    AvailableBookAdapter.OnAvailableBookListener, AddBookFragment.OnFinalizeUploadListener {

    private lateinit var bookListAdapter: BookListAdapter
    private lateinit var availableBookAdapter: AvailableBookAdapter
    private lateinit var availableBooks: MutableList<String>
    private lateinit var enrolledBooks: MutableList<BookModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEnrolledBookList()
        initDrawers()

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
            /*layoutManager = GridLayoutManager(this@MainActivity, 2)*/
            layoutManager = LinearLayoutManager(this@MainActivity)
            bookListAdapter = BookListAdapter(this@MainActivity)
            adapter = bookListAdapter
        }
        enrolledBooks = mutableListOf()

        var b = BookModel(
            "cse101",
            "Computer Fundamentals",
            "Introduction to computers",
            mutableListOf()
        )
        enrolledBooks.add(b)
        b = BookModel(
            "cse102",
            "Programming & Problem Solving",
            "Programming in C",
            mutableListOf()
        )
        enrolledBooks.add(b)
        b = BookModel("cse103", "Math", "Basic Math", mutableListOf())
        enrolledBooks.add(b)
        b = BookModel("cse104", "Data Structures", "Data Structures", mutableListOf())
        enrolledBooks.add(b)
        bookListAdapter.supplyBookList(enrolledBooks)
    }

    override fun onBookClick(position: Int) {

        val intent = Intent(this, ChapterActivity::class.java)
        val clickedBook = enrolledBooks[position].title
        intent.putExtra("book_name", clickedBook)
        startActivity(intent)
    }

    override fun onUpdateButtonClick(bookPosition: Int, menuId: Int) {
        Toast.makeText(this, "Updating", Toast.LENGTH_LONG).show()
        // notify viewModel about click and update from server/repository
    }

    override fun onAvailableBookClick(position: Int) {
        // now add this to enrolled class..
        val str = availableBooks[position]
        availableBookAdapter.removeItem(position)
        val b = BookModel("id_123", str, str, mutableListOf())

        enrolledBooks.add(b)
        bookListAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constants.SELECT_CSV_FILE && resultCode == RESULT_OK && data != null) {
            val selectedUri = data.data //The uri with the location of the file
            if (selectedUri != null) {
                var _path = selectedUri.path
                _path = _path?.substring(_path.indexOf(":") + 1)

                val book = CSVUtil.parseBookFromCSV(_path!!)
                val addBookFragment = AddBookFragment(this)
                addBookFragment.setUploadedBookInfo(book!!)
                addBookFragment.show(supportFragmentManager, "")
            } else {
                toast("No file selected")
            }
        }
    }

    fun uploadBook(view: View) {
        requestPermissionForFile()
        // upload new book
        CSVUtil.selectCSVFile(this)
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun requestPermissionForFile() {
        requestPermissions(
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            Constants.REQUEST_FOR_SDCARD_READ
        )
    }

    override fun onFinalUploadClick(book: BookModel) {
        toast("New book added under available books.")
        availableBooks.add(book.title)
        availableBookAdapter.notifyDataSetChanged()
    }
}
