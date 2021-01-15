package com.spray.stock.models.product

data class Product(
    var id: Long,
    var code: String,
    var name: String,
    var description: String,
    var image: Int
)
