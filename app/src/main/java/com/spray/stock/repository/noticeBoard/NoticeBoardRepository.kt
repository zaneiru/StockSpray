package com.spray.stock.repository.noticeBoard

import com.spray.stock.BuildConfig
import com.spray.stock.api.NoticeBoardApi
import com.spray.stock.config.RetrofitClient
import com.spray.stock.models.ApiPageResponse
import com.spray.stock.models.noticeBoard.NoticeBoard
import com.spray.stock.models.noticeBoard.NoticeBoardResponse
import retrofit2.Response

class NoticeBoardRepository {
    suspend fun getNoticeBoards(page: Int, size: Int): Response<ApiPageResponse<NoticeBoard>> {
        return RetrofitClient.get(BuildConfig.NOTICE_BASE_URL)!!
            .create(NoticeBoardApi::class.java)
            .getNoticeBoards(page, size)
    }
}