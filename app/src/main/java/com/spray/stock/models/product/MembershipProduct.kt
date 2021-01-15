package com.spray.stock.models.product

class MembershipProduct(
    var id: Long,
    var code: String,
    var name: String,
    var description: String,
    var image: Int
) {
    var amount: Int? = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MembershipProduct

        if (id != other.id) return false
        if (code != other.code) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (amount != other.amount) return false
        if (image != other.image) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + code.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + (amount ?: 0)
        result = 31 * result + image
        return result
    }
}