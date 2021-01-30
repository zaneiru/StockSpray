package com.spray.stock.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.spray.stock.adapters.ReportViewPagerAdapter
import com.spray.stock.databinding.FragmentReportBinding

class ReportFragment : Fragment() {

    private var mBinding: FragmentReportBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentReportBinding.inflate(inflater, container, false)
        return mBinding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = mBinding!!.tlReport
        val viewPager2 = mBinding!!.vpReport
        val adapter = ReportViewPagerAdapter(childFragmentManager, lifecycle)

        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "종목분석"
                }
                1 -> {
                    tab.text = "투자분석"
                }
            }
        }.attach()
    }
}