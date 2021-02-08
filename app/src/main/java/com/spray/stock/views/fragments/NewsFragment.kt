package com.spray.stock.views.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.spray.stock.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {

    private var mBinding: FragmentNewsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentNewsBinding.inflate(inflater, container, false)

        return mBinding?.root!!

    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}