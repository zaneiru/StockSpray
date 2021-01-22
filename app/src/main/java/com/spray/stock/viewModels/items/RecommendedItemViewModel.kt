package com.spray.stock.viewModels.items

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.spray.stock.BuildConfig
import com.spray.stock.api.RecommendedItemApi
import com.spray.stock.config.RetrofitClient
import com.spray.stock.viewModels.Resource
import kotlinx.coroutines.Dispatchers

class RecommendedItemViewModel @ViewModelInject constructor(): ViewModel() {

    fun loadData(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val api = RetrofitClient.get(BuildConfig.ITEM_BASE_URL)!!.create(RecommendedItemApi::class.java)
        val response = api.getRecommendedItems(page, 20)

        emit(Resource.success(response))
    }
}