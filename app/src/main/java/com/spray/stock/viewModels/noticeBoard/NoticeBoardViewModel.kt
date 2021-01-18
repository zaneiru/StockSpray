package com.spray.stock.viewModels.noticeBoard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.spray.stock.BuildConfig
import com.spray.stock.api.NoticeBoardApi
import com.spray.stock.config.RetrofitClient
import com.spray.stock.viewModels.Resource
import kotlinx.coroutines.Dispatchers

class NoticeBoardViewModel: ViewModel() {

    fun loadData(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val api = RetrofitClient.get(BuildConfig.BASE_URL)!!.create(NoticeBoardApi::class.java)
        val response = api.getNoticeBoards(page, 20)

        emit(Resource.success(response))
    }
}