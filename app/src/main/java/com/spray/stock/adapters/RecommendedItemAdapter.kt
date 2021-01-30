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
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.spray.stock.R
import com.spray.stock.config.GlideApp
import com.spray.stock.models.item.RecommendedItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecommendedItemAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<RecommendedItemAdapter
    .ViewHolder>() {

    private lateinit var mView: View
    private var mRecommendedItems = mutableListOf<RecommendedItem>()

    private val diffCallback = object : DiffUtil.ItemCallback<RecommendedItem>() {
        override fun areItemsTheSame(oldItem: RecommendedItem, newItem: RecommendedItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RecommendedItem, newItem: RecommendedItem) =
            oldItem == newItem
    }

    private val mAsyncDiff = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mView = LayoutInflater.from(parent.context).inflate(R.layout.items_main, parent, false)
        return ViewHolder(mView)
    }

    override fun getItemCount(): Int = mAsyncDiff.currentList.size

    //클릭 인터페이스 정의
    interface ItemClickListener {
        fun onClick(view: View, position: Int, data: RecommendedItem)
    }

    //클릭리스너 선언
    private lateinit var itemClickListener: ItemClickListener

    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mAsyncDiff.currentList[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position, mRecommendedItems[position])
        }
    }

    fun submitList(recommendedItems: MutableList<RecommendedItem>?) {
        mAsyncDiff.submitList(recommendedItems)
        mRecommendedItems.addAll(recommendedItems!!)
    }

    fun clear() {
        mRecommendedItems.clear()
        mAsyncDiff.submitList(mRecommendedItems)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val lastModifiedDate: TextView = itemView.findViewById(R.id.tv_recommended_last_modified_date)
        private val bannerMainTitle: TextView = itemView.findViewById(R.id.tv_recommended_banner_main_title)
        private val bannerSubTitle: TextView = itemView.findViewById(R.id.tv_recommended_banner_sub_title)
        private val title: TextView = itemView.findViewById(R.id.tv_recommended_title)
        private val likeCount: TextView = itemView.findViewById(R.id.tv_recommended_like_count)
        private val viewCount: TextView = itemView.findViewById(R.id.tv_recommended_view_count)
        private val commentCount: TextView = itemView.findViewById(R.id.tv_recommended_comment_count)
        private val bannerUrl: ImageView = itemView.findViewById(R.id.iv_recommended_banner_url)

        fun bind(recommendedItem: RecommendedItem) {
            lastModifiedDate.text = recommendedItem.lastModifiedDate
            bannerMainTitle.text = recommendedItem.bannerMainTitle
            bannerSubTitle.text = recommendedItem.bannerSubTitle
            title.text = recommendedItem.title
            likeCount.text = recommendedItem.likeCount.toString()
            viewCount.text = recommendedItem.viewCount.toString()
            commentCount.text = recommendedItem.viewCount.toString()
            GlideApp.with(context)
                .load(recommendedItem.bannerUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transform(CenterInside(), RoundedCorners(12))
                .into(bannerUrl)
        }

    }

}