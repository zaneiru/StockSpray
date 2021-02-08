package com.spray.stock.viewModels.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.spray.stock.BuildConfig
import com.spray.stock.api.RecommendedItemApi
import com.spray.stock.config.RetrofitClient
import com.spray.stock.viewModels.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class RecommendedItemViewModel @Inject constructor(): ViewModel() {

    fun loadRecommendedItems(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val api = RetrofitClient.get(BuildConfig.ITEM_BASE_URL)!!.create(RecommendedItemApi::class.java)
        val response = api.getRecommendedItems(page, 20)

        emit(Resource.success(response))
    }

    fun loadRecommendedItem(id: Long) = liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val api = RetrofitClient.get(BuildConfig.ITEM_BASE_URL)!!.create(RecommendedItemApi::class.java)
        val response = api.getRecommendedItem(id)

        emit(Resource.success(response))
    }
}