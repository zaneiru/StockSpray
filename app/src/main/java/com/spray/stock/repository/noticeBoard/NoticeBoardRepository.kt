package com.spray.stock.repository.noticeBoard

import com.spray.stock.BuildConfig
import com.spray.stock.api.NoticeBoardApi
import com.spray.stock.config.RetrofitClient
import com.spray.stock.models.noticeBoard.NoticeBoardResponse
import retrofit2.Response

class NoticeBoardRepository {
    suspend fun getNoticeBoards(page: Int, size: Int): Response<NoticeBoardResponse> {
        return RetrofitClient.get(BuildConfig.BASE_URL)!!
            .create(NoticeBoardApi::class.java)
            .getNoticeBoards(page, size)
    }
}