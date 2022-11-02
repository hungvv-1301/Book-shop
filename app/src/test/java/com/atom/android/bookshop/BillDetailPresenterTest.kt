package com.atom.android.bookshop

import android.content.Context
import android.os.Bundle
import com.atom.android.bookshop.data.model.Bill
import com.atom.android.bookshop.ui.bill.detail.BillDetailContract
import com.atom.android.bookshop.ui.bill.detail.BillDetailPresenter
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BillDetailPresenterTest {

    private val view = mockk<BillDetailContract.View>(relaxed = true)
    private val presenter = BillDetailPresenter()
    private val bundle = mockk<Bundle>(relaxed = true)
    private val bill = mockk<Bill>(relaxed = true)

    @Test
    fun `get instance object is unique`() {
        val presenter = BillDetailPresenter.getInstance()
        val mPresenter = BillDetailPresenter.getInstance()
        Assert.assertEquals(presenter, mPresenter)
    }

    @Before
    fun setView() {
        presenter.setView(view)
    }

    @Test
    fun `get bill Success`() {
        every {
            presenter.getBill(bundle)
        } answers {
            view.getBillSuccess(bill)
        }
        presenter.getBill(bundle)
        verify(exactly = 1) {
            view.getBillSuccess(bill)
        }
    }

    @Test
    fun `get bill failed`() {
        every {
            presenter.getBill(bundle)
        } answers {
            view.getBillFailed()
        }
        presenter.getBill(bundle)
        verify(exactly = 1) {
            view.getBillFailed()
        }
    }
}
