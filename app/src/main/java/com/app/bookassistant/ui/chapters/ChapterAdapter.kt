package com.app.bookassistant.ui.chapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bookassistant.R
import kotlinx.android.synthetic.main.layout_chapter.view.*

class ChapterAdapter(private val onChapterListener: OnChapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var availableChapters: List<ChapterModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_chapter, parent, false
            ), onChapterListener
        )
    }

    override fun getItemCount(): Int {
        return availableChapters.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ChapterViewHolder -> {
                holder.bind(availableChapters[position])
            }
        }
    }

    fun supplyChapters(chapters: List<ChapterModel>) {
        availableChapters = chapters
    }


    class ChapterViewHolder constructor(
        itemView: View, private val onChapterListener: OnChapterListener
    ) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val chapterTitle = itemView.chapter_title
        private val chapterDescription = itemView.chapter_description
        private val moreBtn = itemView.chapter_more_btn
        private val chapterLayout = itemView.chapter_layout

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(chapterModel: ChapterModel) {
            chapterTitle.text = chapterModel.chapterTitle
            chapterDescription.text = chapterModel.chapterDescripton

            /*myCardView.setCardBackgroundColor(Color.TRANSPARENT);
            myCardView.setCardElevation(0);*/

        }


        override fun onClick(v: View?) {
            onChapterListener.onChapterClick(adapterPosition)
        }
    }

    interface OnChapterListener {
        fun onChapterClick(position: Int)
    }
}