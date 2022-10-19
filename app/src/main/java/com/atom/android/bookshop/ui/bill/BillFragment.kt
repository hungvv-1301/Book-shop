package com.atom.android.bookshop.ui.bill

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.atom.android.bookshop.base.BaseFragment
import com.atom.android.bookshop.databinding.FragmentBillBinding
import com.atom.android.bookshop.ui.bill.confirm.BillConfirmFragment
import com.atom.android.bookshop.ui.bill.delivery.BillDeliveryFragment
import com.atom.android.bookshop.ui.bill.pending.BillPendingFragment
import com.atom.android.bookshop.ui.bill.sucess.BillSuccessFragment

class BillFragment : BaseFragment<FragmentBillBinding>(FragmentBillBinding::inflate) {

    override fun initData() {
        // TODO implement later
    }


    override fun initView() {
        binding?.viewPagerBill?.adapter = ViewPagerTabLayout(
            childFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            listOf(
                BillPendingFragment(),
                BillConfirmFragment(),
                BillDeliveryFragment(),
                BillSuccessFragment()
            )
        )
        binding?.tabLayoutBill?.setupWithViewPager(binding?.viewPagerBill)
    }


    override fun initEvent() {
        // TODO implement later
    }

    override fun navigate(action: Int) {
        // TODO implement later
    }


}
