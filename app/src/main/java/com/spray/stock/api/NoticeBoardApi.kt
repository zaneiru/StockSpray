package com.spray.stock.api

import com.spray.stock.models.ServiceResponse
import com.spray.stock.models.noticeBoard.NoticeBoard
import com.spray.stock.models.noticeBoard.NoticeBoardResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeBoardApi {

    @GET("/v1/noticeBoards")
    suspend fun getNoticeBoards(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ServiceResponse<NoticeBoardResponse>>

//    @GET("/v1/noticeBoards")
//    suspend fun getNoticeBoards(
//        @Query("page") page: Int,
//        @Query("size") size: Int
//    ): Call<NoticeBoardResponse>
}