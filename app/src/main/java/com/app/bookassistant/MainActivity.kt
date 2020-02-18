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
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bookassistant.data.local.BookRepository
import com.app.bookassistant.ui.addbook.AddBookFragment
import com.app.bookassistant.ui.chapters.ChapterActivity
import com.app.bookassistant.ui.dashboard.AvailableBookAdapter
import com.app.bookassistant.ui.dashboard.BookListAdapter
import com.app.bookassistant.ui.dashboard.BookModel
import com.app.bookassistant.utils.CSVUtil
import com.app.bookassistant.utils.Constants
import com.app.bookassistant.utils.toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), BookListAdapter.OnBookListener,
    AvailableBookAdapter.OnAvailableBookListener, AddBookFragment.OnFinalizeUploadListener {

    private lateinit var enrolledBookAdapter: BookListAdapter
    private lateinit var availableBookAdapter: AvailableBookAdapter
    private lateinit var enrolledBooks: MutableList<BookModel>
    private lateinit var availableBooks: MutableList<BookModel>

    private lateinit var bookRepository: BookRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEnrolledBookList()
        initDrawers()

        initAvailableCourseList()
    }

    private fun initAvailableCourseList() {
        available_book_recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            availableBookAdapter = AvailableBookAdapter(this@MainActivity)
            adapter = availableBookAdapter
        }

        val items = bookRepository.getAvailableBooks()
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
        enrolled_book_recyclerView.apply {
            /*layoutManager = GridLayoutManager(this@MainActivity, 2)*/
            layoutManager = LinearLayoutManager(this@MainActivity)
            enrolledBookAdapter = BookListAdapter(this@MainActivity)
            adapter = enrolledBookAdapter
        }
        bookRepository = BookRepository.getInstance()
        enrolledBooks = bookRepository.getEnrolledBooks()
        enrolledBookAdapter.supplyBookList(enrolledBooks)

    }

    override fun onBookClick(position: Int) {

        val intent = Intent(this, ChapterActivity::class.java)
        val clickedBook = enrolledBooks[position]
        intent.putExtra("book_name", clickedBook.title)

        val bundle = bundleOf(Constants.BOOK_ARG_KEY to clickedBook)

        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onUpdateButtonClick(bookPosition: Int, menuId: Int) {
        Toast.makeText(this, "Updating", Toast.LENGTH_LONG).show()
        // notify viewModel about click and update from server/repository
    }

    override fun onDeleteClick(bookPosition: Int) {
        availableBooks.add(enrolledBooks[bookPosition])
        enrolledBooks.removeAt(bookPosition)
        availableBookAdapter.notifyDataSetChanged()
        enrolledBookAdapter.notifyDataSetChanged()
    }

    override fun onAvailableBookClick(position: Int) {
        // now add this to enrolled class..
        val clickedBook = availableBooks[position]

        availableBooks.removeAt(position)
        availableBookAdapter.notifyDataSetChanged()

        enrolledBooks.add(clickedBook)
        enrolledBookAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constants.SELECT_CSV_FILE && resultCode == RESULT_OK && data != null) {
            val selectedUri = data.data //The uri with the location of the file
            if (selectedUri != null) {
                var selectedPath = selectedUri.path
                selectedPath = selectedPath?.substring(selectedPath.indexOf(":") + 1)

                if (selectedPath.isNullOrEmpty()) {
                    toast("Error selecting file, please try later/another book")
                    return
                }
                val book = CSVUtil.parseBookFromCSV(selectedPath)

                if (book == null) {
                    toast("Error parsing book please try another one")
                    return
                }
                val addBookFragment = AddBookFragment(this)
                addBookFragment.setUploadedBookInfo(book)
                addBookFragment.show(supportFragmentManager, "add_book_fragment")
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
        availableBooks.add(book)
        availableBookAdapter.notifyDataSetChanged()
    }
}
