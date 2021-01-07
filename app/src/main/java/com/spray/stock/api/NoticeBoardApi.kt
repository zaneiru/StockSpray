package com.spray.stock.api

import com.spray.stock.model.noticeBoard.NoticeBoardResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeBoardApi {

    @GET("/v1/noticeBoards")
    fun getNoticeBoards(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<NoticeBoardResponse>
}