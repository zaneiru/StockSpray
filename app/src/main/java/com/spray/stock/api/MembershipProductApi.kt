package com.spray.stock.api

import com.spray.stock.models.product.MembershipProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface MembershipProductApi {

    @GET("/v1/products/memberships")
    fun getMembershipProducts(): Call<List<MembershipProductResponse>>
}