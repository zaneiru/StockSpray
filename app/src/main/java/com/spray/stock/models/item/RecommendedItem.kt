package com.spray.stock.models.item

data class RecommendedItem (
    val id: Long,
    val title: String,
    val bannerUrl: String,
    val bannerMainTitle: String,
    val bannerSubTitle: String,
    val likeCount: Int,
    val viewCount: Int,
    val commentCount: Int,
    val lastModifiedDate: String,
    val comments: ArrayList<RecommendedItemComment>?
)