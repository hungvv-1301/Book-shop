package com.atom.android.bookshop.ui.bill.pending

import androidx.core.view.isVisible
import com.atom.android.bookshop.R
import com.atom.android.bookshop.base.BaseFragment
import com.atom.android.bookshop.data.model.Bill
import com.atom.android.bookshop.data.repository.BillRepository
import com.atom.android.bookshop.data.source.remote.bill.BillRemoteDataSource
import com.atom.android.bookshop.databinding.FragmentBillPendingBinding
import com.atom.android.bookshop.utils.toast

class BillPendingFragment :
    BaseFragment<FragmentBillPendingBinding>(FragmentBillPendingBinding::inflate),
    BillPendingContract.View {
    private val bills = mutableListOf<Bill>()
    private val billPendingPresenter by lazy {
        BillPendingPresenter.getInstance(
            BillRepository.getInstance(
                BillRemoteDataSource.getInstance()
            ),
            this
        )
    }

    private val listAdapter = ListAdapterBillPending(
        { bill: Bill -> {} },
        { bill: Bill -> billPendingPresenter.destroyBill(context, bill) },
        { bill: Bill -> billPendingPresenter.confirmBill(context, bill) }
    )

    override fun initData() {
        billPendingPresenter.getBillPending(context)
    }


    override fun initView() {
        binding?.recyclerviewBillPending?.adapter = listAdapter
    }

    override fun initEvent() {
        // late impl
    }

    override fun navigate(action: Int) {
        // late impl
    }

    override fun getBillPendingSuccess(bill: List<Bill>) {
        this.bills.clear()
        this.bills.addAll(bill)
        listAdapter.submitList(bill)
    }

    override fun getBillPendingFailed(message: String?) {
        binding?.textViewGetBillFailed?.isVisible = true
        binding?.recyclerviewBillPending?.isVisible = false
    }

    override fun confirmBillSuccess(bill: Bill) {
        this.bills.remove(bill)
        listAdapter.submitList(bills)
    }

    override fun confirmBillFailed(message: String?) {
        context?.toast(R.string.text_confirm_failed)
    }

    override fun destroyBillSuccess(bill: Bill) {
        this.bills.remove(bill)
        listAdapter.submitList(bills)
    }

    override fun destroyBillFailed(message: String?) {
        context?.toast(R.string.text_destroy_failed)
    }

    companion object {
        const val NAME = "Đơn chờ"
    }
}
