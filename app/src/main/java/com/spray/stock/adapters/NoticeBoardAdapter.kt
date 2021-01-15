package com.spray.stock.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spray.stock.R
import com.spray.stock.models.noticeBoard.NoticeBoardResponse

class NoticeBoardAdapter(private var response: NoticeBoardResponse) : RecyclerView.Adapter<NoticeBoardAdapter.Companion.Holder>() {
    private lateinit var context: Context
    private lateinit var rv: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        rv = LayoutInflater.from(parent.context).inflate(R.layout.fragment_notice, parent, false)
        return Holder(rv)
    }

    override fun getItemCount(): Int = response.totalElements

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvDate.text = response.content[position].lastModifiedDate.toString()
        holder.tvTitle.text = response.content[position].title
    }

    companion object {
        class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var tvDate: TextView = itemView!!.findViewById(R.id.tv_notice_board_date)
            var tvTitle: TextView = itemView!!.findViewById(R.id.tv_notice_board_title)
        }
    }
}