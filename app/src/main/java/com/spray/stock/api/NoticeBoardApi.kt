package com.spray.stock.api

import com.spray.stock.models.ApiPageResponse
import com.spray.stock.models.noticeBoard.NoticeBoard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeBoardApi {
    @GET("api/v1/noticeBoards")
    suspend fun getNoticeBoards(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ApiPageResponse<NoticeBoard>>
}