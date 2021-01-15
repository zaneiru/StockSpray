package com.spray.stock.api

import com.spray.stock.models.noticeBoard.NoticeBoardResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemInfoApi {

    @GET("/v1/items/{code}")
    fun getNoticeBoards(
        @Query("code") code: String
    ): Call<NoticeBoardResponse>
}