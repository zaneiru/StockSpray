package com.spray.stock.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spray.stock.R
import com.spray.stock.databinding.FragmentThemeBinding

class ThemeFragment : Fragment() {

    private var mBinding: FragmentThemeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentThemeBinding.inflate(inflater, container, false)
        return mBinding?.root!!
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}