package com.spray.stock.models.item

import com.spray.stock.models.member.Member

data class RecommendedItemComment (
    val id: Long,
    val comment: String,
    val recommendedItem: RecommendedItem,
    val member: Member,
    val lastModifiedDate: String,
)