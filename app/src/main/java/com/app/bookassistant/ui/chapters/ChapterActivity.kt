package com.app.bookassistant.ui.chapters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.bookassistant.R

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

    }
}
