package com.app.bookassistant.ui.chapters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.bookassistant.R
import com.app.bookassistant.ui.exam.ExamMakerFragment
import kotlinx.android.synthetic.main.activity_chapter.*

class ChapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

        /// transaction to chapter fragment
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = ChapterFragment()
        fragmentTransaction.replace(R.id.chapter_fragment_container_frameLayout, fragment)
        fragmentTransaction.commit()

        setSupportActionBar(toolbar_chapter)

        initBottomNav()
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
                    fragmentTransaction.commit()
                    true
                }
                R.id.chapter_bottom_menu_exam -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(
                        R.id.chapter_fragment_container_frameLayout, ExamMakerFragment()
                    )
                    fragmentTransaction.commit()
                    true
                }
                else -> false

            }
        }
    }
}
