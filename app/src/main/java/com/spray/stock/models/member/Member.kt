package com.spray.stock.models.member

import com.spray.stock.models.product.Product

data class Member(
    val id: Long,
    val product: Product
)
