package com.spray.stock.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spray.stock.R
import com.spray.stock.models.noticeBoard.NoticeBoard

class NoticeBoardAdapter : RecyclerView.Adapter<NoticeBoardAdapter.CustomViewHolder>() {

    private lateinit var mView: View
    private var mNoticeBoards = ArrayList<NoticeBoard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        mView = LayoutInflater.from(parent.context).inflate(R.layout.notice_boards, parent, false)
        return CustomViewHolder(mView)
    }

    override fun getItemCount(): Int = mNoticeBoards.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(mNoticeBoards[position])
    }

    fun addList(noticeBoards: ArrayList<NoticeBoard>){
        mNoticeBoards.addAll(noticeBoards)
        notifyDataSetChanged()
    }

    fun clear(){
        mNoticeBoards.clear()
        notifyDataSetChanged()
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDate: TextView = itemView.findViewById(R.id.tv_notice_board_date)
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_notice_board_title)

        fun bind(noticeBoard: NoticeBoard){
            tvDate.text = noticeBoard.lastModifiedDate
            tvTitle.text = noticeBoard.title
        }
    }
}