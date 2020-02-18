package com.app.bookassistant.ui.chapters

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.app.bookassistant.R
import com.app.bookassistant.ui.dashboard.BookModel
import com.app.bookassistant.ui.exam.ExamMakerFragment
import kotlinx.android.synthetic.main.activity_chapter.*

class ChapterActivity : AppCompatActivity() {

    lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        fragmentTransaction()

        initToolbar()
        initBottomNav()
        _getIntent()
    }

    private fun _getIntent() {
        val temp = intent.getStringExtra("book_name")
        val bookName: String = if (temp.isNullOrEmpty()) " " else temp

        sharedViewModel.selectedBook.value =
            BookModel("id_123", bookName, "Description of $bookName", mutableListOf())
        supportActionBar?.title = bookName
    }

    private fun fragmentTransaction() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = ChapterFragment()

        fragment.arguments = intent.extras
        fragmentTransaction.replace(R.id.chapter_fragment_container_frameLayout, fragment)
        fragmentTransaction.commit()


    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_chapter)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val upArrow = resources.getDrawable(R.drawable.ic_arrow_back_white_24dp)
        upArrow.setColorFilter(resources.getColor(R.color.White), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun initBottomNav() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.chapter_bottom_menu_chapter -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(
                        R.id.chapter_fragment_container_frameLayout, ChapterFragment()
                    )
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                    true
                }
                R.id.chapter_bottom_menu_exam -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(
                        R.id.chapter_fragment_container_frameLayout, ExamMakerFragment()
                    )
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                    true
                }
                else -> false

            }
        }
    }


}
