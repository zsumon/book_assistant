package com.app.bookassistant.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bookassistant.R
import com.app.bookassistant.data.models.Book
import kotlinx.android.synthetic.main.dashboard_books.view.*


class BookListAdapter(private val onBookListener: OnBookListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var bookList: List<Book> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dashboard_books, parent, false
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

    fun supplyBookList(bookList: List<Book>) {
        this.bookList = bookList
    }


    class BookListViewHolder constructor(
        itemView: View,
        private var onBookListener: OnBookListener
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
        }

        fun bind(book: Book) {
            bookTitle.text = book.title
            bookDescriptor.text = book.description

            setBackground()
        }

        private fun setBackground() {
            val ran = (0 until IMGS.size).random()
            bookItemLayout.setBackgroundResource(IMGS[ran])
        }

        override fun onClick(v: View?) {
            onBookListener.onBookClick(adapterPosition)
        }
    }

    interface OnBookListener {
        fun onBookClick(position: Int)
    }

}