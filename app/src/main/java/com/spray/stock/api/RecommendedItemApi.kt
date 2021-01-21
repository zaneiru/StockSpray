package com.spray.stock.api

import com.spray.stock.models.ApiPageResponse
import com.spray.stock.models.item.RecommendedItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendedItemApi {
    @GET("api/v1/recommendedItems")
    suspend fun getRecommendedItems(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ApiPageResponse<RecommendedItem>>
}