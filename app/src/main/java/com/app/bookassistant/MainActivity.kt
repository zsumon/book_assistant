package com.app.bookassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hello_id?.text = "This is demo text view item"

    }

    fun onButtonClick(view: View) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
    }
}
