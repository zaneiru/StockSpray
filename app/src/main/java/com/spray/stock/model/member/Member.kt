package com.spray.stock.model.member

import com.spray.stock.model.product.Product

data class Member(
    val id: Long,
    val itemPurchase: ItemPurchase,
    val product: Product
)
