package com.spray.stock.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.spray.stock.R
import com.spray.stock.config.GlideApp
import com.spray.stock.models.item.RecommendedItemComment
import com.spray.stock.utils.diffTime

class RecommendedItemCommentAdapter(val context: Context) : RecyclerView.Adapter<RecommendedItemCommentAdapter.ViewHolder>() {

    private lateinit var mView: View
    private var mRecommendedItemComments = mutableListOf<RecommendedItemComment>()

    private val diffCallback = object : DiffUtil.ItemCallback<RecommendedItemComment>() {
        override fun areItemsTheSame(oldItem: RecommendedItemComment, newItem: RecommendedItemComment) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RecommendedItemComment, newItem: RecommendedItemComment) =
            oldItem == newItem
    }

    private val mAsyncDiff = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedItemCommentAdapter.ViewHolder {
        mView = LayoutInflater.from(context).inflate(R.layout.items_recommended_detail_comment, parent, false)
        return ViewHolder(mView)
    }

    override fun getItemCount(): Int = mAsyncDiff.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mAsyncDiff.currentList[position])
    }

    fun submitList(recommendedItemComments: MutableList<RecommendedItemComment>) {
        mAsyncDiff.submitList(recommendedItemComments)
        mRecommendedItemComments.addAll(recommendedItemComments)
    }

    fun clear() {
        mRecommendedItemComments.clear()
        mAsyncDiff.submitList(mRecommendedItemComments)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val commentMemberProfile: ImageView = itemView.findViewById(R.id.civ_recommended_item_comment_member_profile)
        private val commentMemberNickName: TextView = itemView.findViewById(R.id.tv_recommended_item_comment_member_nick_name)
        private val commentDate: TextView = itemView.findViewById(R.id.tv_recommended_item_comment_date)
        private val comment: TextView = itemView.findViewById(R.id.tv_recommended_item_comment)

        fun bind(recommendedItemComment: RecommendedItemComment) {

            commentMemberNickName.text = recommendedItemComment.member.nickName
            commentDate.text = recommendedItemComment.lastModifiedDate.diffTime()
            comment.text = recommendedItemComment.comment.replace("\\n", System.getProperty("line.separator")!!)
            GlideApp.with(context)
                .load(recommendedItemComment.member.profileImageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transform(CircleCrop())
                .into(commentMemberProfile)
        }
    }
}