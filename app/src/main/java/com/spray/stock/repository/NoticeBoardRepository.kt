package com.spray.stock.repository

import com.spray.stock.BuildConfig
import com.spray.stock.api.NoticeBoardApi
import com.spray.stock.client.RetrofitClient
import com.spray.stock.models.noticeBoard.NoticeBoard
import com.spray.stock.models.noticeBoard.NoticeBoardResponse

class NoticeBoardRepository {

    suspend fun getNoticeBoards(): List<NoticeBoard> {
        val api: NoticeBoardApi = RetrofitClient.get(BuildConfig.BASE_URL)!!.create(NoticeBoardApi::class.java)
        return api.getNoticeBoards(0, 10)
    }
}