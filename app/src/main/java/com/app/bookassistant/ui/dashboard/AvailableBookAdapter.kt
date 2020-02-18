package com.app.bookassistant.ui.dashboard

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.app.bookassistant.R
import kotlinx.android.synthetic.main.dialog_confirm_enroll_book.*
import kotlinx.android.synthetic.main.item_available_book.view.*

class AvailableBookAdapter(private val onAvailableBookListener: OnAvailableBookListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), ConfirmEnrollBookDialog.OnDialogListener {

    lateinit var availableBook: MutableList<BookModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AvailableViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_available_book, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return availableBook.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AvailableViewHolder -> {
                holder.bind(availableBook[position])
                holder.itemView.setOnClickListener {
                    ConfirmEnrollBookDialog(this, position).show(
                        (onAvailableBookListener as AppCompatActivity).supportFragmentManager,
                        "show_available_book_to_enroll_confirm_dialog"
                    )
                }
            }
        }
    }

    fun supplyList(items: MutableList<BookModel>) {
        availableBook = items
    }

    fun removeItem(position: Int) {
        if (position >= 0 && position < availableBook.size) {
            availableBook.removeAt(position)
            notifyDataSetChanged()
        }
    }

    class AvailableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(book: BookModel) {
            itemView.available_book_item_title.text = book.title
        }
    }

    interface OnAvailableBookListener {
        fun onAvailableBookClick(position: Int)
    }

    override fun onConfirmClick(position: Int) {
        onAvailableBookListener.onAvailableBookClick(position)
    }

    override fun onCancelClick(position: Int) {

    }

}

public class ConfirmEnrollBookDialog(
    private val onDialogListener: OnDialogListener,
    private val position: Int
) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_confirm_enroll_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        conf_dialog_confirm.setOnClickListener {
            onDialogListener.onConfirmClick(position)
            dialog?.dismiss()
        }

        conf_dialog_cancel.setOnClickListener {
            // onDialogListener.onCancelClick(position)
            dialog?.dismiss()
        }
    }

    interface OnDialogListener {
        fun onConfirmClick(position: Int)
        fun onCancelClick(position: Int)
    }
}