package com.atom.android.bookshop.ui.bill.pending

import android.content.Context
import com.atom.android.bookshop.data.model.Bill
import com.atom.android.bookshop.data.repository.BillRepository
import com.atom.android.bookshop.data.source.remote.IRequestCallback
import com.atom.android.bookshop.data.source.remote.ResponseObject
import com.atom.android.bookshop.data.source.remote.api.ApiConstants
import com.atom.android.bookshop.utils.Constants
import com.atom.android.bookshop.utils.SharedPreferenceUtils
import com.atom.android.bookshop.utils.getTokenLogin

class BillPendingPresenter(
    private val repository: BillRepository,
    private val view: BillPendingContract.View
) : BillPendingContract.Presenter {
    override fun getBillPending(context: Context?) {
        val token = SharedPreferenceUtils.getInstance(context)?.getTokenLogin()
        repository.getBill(
            token,
            Constants.DEFAULT_PAGE,
            ApiConstants.TYPEOFBILL.PENDING,
            object : IRequestCallback<ResponseObject<List<Bill>>> {
                override fun onSuccess(responseObject: ResponseObject<List<Bill>>) {
                    view.getBillPendingSuccess(responseObject.data as List<Bill>)
                }

                override fun onFailed(message: String?) {
                    view.getBillPendingFailed(message)
                }

            })
    }

    override fun confirmBill(context: Context?, bill: Bill) {
        val token = SharedPreferenceUtils.getInstance(context)?.getTokenLogin()
        repository.updateStatusBill(
            token,
            bill.id,
            ApiConstants.TYPEOFBILL.ACCEPT,
            object : IRequestCallback<ResponseObject<Bill>> {
                override fun onSuccess(responseObject: ResponseObject<Bill>) {
                    view.confirmBillSuccess(bill)
                }

                override fun onFailed(message: String?) {
                    view.confirmBillFailed(message)
                }
            })
    }

    override fun destroyBill(context: Context?, bill: Bill) {
        val token = SharedPreferenceUtils.getInstance(context)?.getTokenLogin()
        repository.updateStatusBill(
            token,
            bill.id,
            ApiConstants.TYPEOFBILL.DESTROY,
            object : IRequestCallback<ResponseObject<Bill>> {
                override fun onSuccess(responseObject: ResponseObject<Bill>) {
                    view.destroyBillSuccess(bill)
                }

                override fun onFailed(message: String?) {
                    view.destroyBillFailed(message)
                }
            })
    }

    companion object {
        private var instance: BillPendingPresenter? = null
        fun getInstance(
            repository: BillRepository,
            view: BillPendingContract.View
        ) = synchronized(this) {
            instance ?: BillPendingPresenter(repository, view).also {
                instance = it
            }
        }
    }
}
