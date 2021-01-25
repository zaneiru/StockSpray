package com.spray.stock.models.item

import com.fasterxml.jackson.annotation.JsonFormat
import com.spray.stock.models.enums.UserConfig
import com.spray.stock.models.member.Member

data class RecommendedItemComment (
    val id: Long,
    val comment: String,
    val userConfig: UserConfig,
    val member: Member,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    val lastModifiedDate: String,
)

/*
* data class BlogPost(

    @Expose
    @SerializedName("pk")
    val pk: Int? = null,

    @Expose
    @SerializedName("title")
    val title: String? = null,

    @Expose
    @SerializedName("body")
    val body: String? = null,

    @Expose
    @SerializedName("image")
    val image: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as BlogPost

        if (pk != other.pk) return false

        return true
    }

    override fun toString(): String {
        return "BlogPost(title=$title, body=$body, image=$image)"
    }
}
* */