package com.spray.stock.viewModels.noticeBoard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.spray.stock.BuildConfig
import com.spray.stock.api.NoticeBoardApi
import com.spray.stock.client.RetrofitClient
import com.spray.stock.viewModels.Resource
import kotlinx.coroutines.Dispatchers

class NoticeBoardViewModel: ViewModel() {

    fun loadData() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val api = RetrofitClient.get(BuildConfig.BASE_URL)!!.create(NoticeBoardApi::class.java)
        val response = api.getNoticeBoards(0, 10)

        if (response.isSuccessful) {
            emit(Resource.success(response.body()?.data))
        }
    }
}