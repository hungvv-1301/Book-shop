package com.atom.android.bookshop.ui.bill.pending

import android.content.Context
import com.atom.android.bookshop.data.model.Bill

class BillPendingContract {
    interface View {
        fun getBillPendingSuccess(bill: List<Bill>)
        fun getBillPendingFailed(message: String?)
        fun confirmBillSuccess(bill: Bill)
        fun confirmBillFailed(message: String?)
        fun destroyBillSuccess(bill: Bill)
        fun destroyBillFailed(message: String?)
    }

    interface Presenter {
        fun getBillPending(context: Context?)
        fun confirmBill(context: Context?, bill: Bill)
        fun destroyBill(context: Context?, bill: Bill)
    }
}
