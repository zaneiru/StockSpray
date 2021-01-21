package com.spray.stock.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.spray.stock.R
import com.spray.stock.models.item.RecommendedItem

class RecommendedItemAdapter(val context: Context) : RecyclerView.Adapter<RecommendedItemAdapter.CustomViewHolder>() {

    private lateinit var mView: View
    private var mRecommendedItems = ArrayList<RecommendedItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        mView = LayoutInflater.from(parent.context).inflate(R.layout.items_main, parent, false)
        return CustomViewHolder(mView)
    }

    override fun getItemCount(): Int = mRecommendedItems.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(mRecommendedItems[position])
    }

    fun addList(recommendedItems: ArrayList<RecommendedItem>){
        mRecommendedItems.addAll(recommendedItems)
        notifyDataSetChanged()
    }

    fun clear(){
        mRecommendedItems.clear()
        notifyDataSetChanged()
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lastModifiedDate: TextView = itemView.findViewById(R.id.tv_recommended_last_modified_date)
        private val bannerMainTitle: TextView = itemView.findViewById(R.id.tv_recommended_banner_main_title)
        private val bannerSubTitle: TextView = itemView.findViewById(R.id.tv_recommended_banner_sub_title)
        private val title: TextView = itemView.findViewById(R.id.tv_recommended_title)
        private val likeCount: TextView = itemView.findViewById(R.id.tv_recommended_like_count)
        private val viewCount: TextView = itemView.findViewById(R.id.tv_recommended_view_count)
        private val commentCount: TextView = itemView.findViewById(R.id.tv_recommended_comment_count)
        private val bannerUrl: ImageView = itemView.findViewById(R.id.iv_recommended_banner_url)

        fun bind(recommendedItem: RecommendedItem){
            lastModifiedDate.text = recommendedItem.lastModifiedDate
            bannerMainTitle.text = recommendedItem.bannerMainTitle
            bannerSubTitle.text = recommendedItem.bannerSubTitle
            title.text = recommendedItem.title
            likeCount.text = recommendedItem.likeCount.toString()
            viewCount.text = recommendedItem.viewCount.toString()
            commentCount.text = recommendedItem.viewCount.toString()
            Glide.with(context)
                .load(recommendedItem.bannerUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transform(CenterInside(), RoundedCorners(6))
                .into(bannerUrl)
        }
    }
}