package com.app.bookassistant.ui.dashboard

import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.app.bookassistant.MainActivity
import com.app.bookassistant.R
import kotlinx.android.synthetic.main.item_books.view.*
import java.lang.Float
import java.time.LocalDate


class BookListAdapter(private val onBookListener: OnBookListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var bookList: List<BookModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_books, parent, false
            ), onBookListener
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookListViewHolder -> {
                holder.bind(bookList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun supplyBookList(bookList: List<BookModel>) {
        this.bookList = bookList
    }

    fun addBook(book: BookModel) {
        this.bookList += book
        notifyDataSetChanged()
    }


    class BookListViewHolder constructor(
        itemView: View,
        private val onBookListener: OnBookListener
    ) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val IMGS = mutableListOf(
            R.drawable.img_badminton,
            R.drawable.img_baseball,
            R.drawable.img_bowling,
            R.drawable.img_cycling,
            R.drawable.img_golf,
            R.drawable.img_running,
            R.drawable.img_soccer,
            R.drawable.img_basketball,
            R.drawable.img_swimming,
            R.drawable.img_tabletennis,
            R.drawable.img_tennis
        )

        private val bookTitle = itemView.book_title
        private val bookDescriptor = itemView.book_description
        private val moreBtn = itemView.more_button
        private val bookItemLayout = itemView.bookItemLayout

        init {
            itemView.setOnClickListener(this)
            itemView.more_button.setOnClickListener(this)
        }

        fun bind(book: BookModel) {
            bookTitle.text = book.title
            bookDescriptor.text = book.description

            setBackground(adapterPosition % IMGS.size)
        }

        private fun setBackground(pos: Int) {
            // val ran = (0 until IMGS.size).random()
            bookItemLayout.setBackgroundResource(IMGS[pos])
            bookItemLayout.alpha = Float.parseFloat("50.0")
        }

        override fun onClick(v: View?) {
            if (v?.id == R.id.more_button) {
                val popup = PopupMenu(onBookListener as MainActivity, itemView.more_button)
                popup.inflate(R.menu.book_more_menu)
                popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(item: MenuItem): Boolean {
                        return when (item.getItemId()) {
                            R.id.book_update -> {
                                onBookListener.onMoreButtonClick(adapterPosition, R.id.more_button)
                                return true
                            }
                            else -> false
                        }
                    }
                })
                popup.show()
            } else {
                Log.d("TAG", " view id is: " + v?.id.toString())
                onBookListener.onBookClick(adapterPosition)
            }
        }
    }

    interface OnBookListener {
        fun onBookClick(position: Int)
        fun onMoreButtonClick(bookPosition: Int, menuId: Int)
    }

}