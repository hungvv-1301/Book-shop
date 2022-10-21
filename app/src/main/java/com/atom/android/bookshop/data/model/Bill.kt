package com.atom.android.bookshop.data.model

data class Bill(
    val id: Int,
    val shippingMethod: ShippingMethod,
    val address: String,
    val note: String,
    val phone: String,
    val receiver: String,
    val orderHistories: List<OrderHistory>,
    val orderLines: List<OrderLine>,
    val createdAt: String,
) {
    fun totalBill(): Double =  orderLines.sumOf { it.amount * it.price }

    fun totalItem(): Int = orderLines.sumOf {  it.amount }

    companion object {
        const val ID = "id"
        const val SHIPPING_METHOD = "shippingMethod"
        const val ADDRESS = "address"
        const val NOTE = "note"
        const val PHONE = "phone"
        const val RECEIVER = "receiver"
        const val ORDER_HISTORIES = "orderHistories"
        const val ORDER_LINES = "orderLines"
        const val CREATED_AT = "createdAt"
    }
}
