package com.spray.stock.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.spray.stock.R
import com.spray.stock.adapters.RecommendedItemAdapter
import com.spray.stock.databinding.FragmentMainBinding
import com.spray.stock.viewModels.Status
import com.spray.stock.viewModels.items.RecommendedItemViewModel

class MainFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mBinding: FragmentMainBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: RecommendedItemAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mProgressBar: ProgressBar
    private val mViewModel: RecommendedItemViewModel by viewModels()

    private var mLoading: Boolean = false

    private var mPage = 0
    private var mTotalPage = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = FragmentMainBinding.inflate(inflater, container, false)
        mRecyclerView = mBinding.rvRecommendedItem
        mSwipeRefreshLayout = mBinding.spRecommendedItem
        return mBinding.root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mProgressBar = mBinding.pbRecommendedItem
        mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mSwipeRefreshLayout.setOnRefreshListener(this)
        mAdapter = context?.let { RecommendedItemAdapter(it) }!!
        with(mRecyclerView) {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

        // 목록에서 각 Content 영역 하단에 구분되는 gray bottom bar 그리는 부분
        val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(this.context?.let {
            AppCompatResources.getDrawable(
                it,
                R.drawable.recyclerview_divider_line
            )
        }!!)
        mRecyclerView.addItemDecoration(dividerItemDecoration)

        getRecommendedItems(false)

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = mLayoutManager.childCount
                val pastVisibleItem = mLayoutManager.findFirstVisibleItemPosition()
                val total  = mAdapter.itemCount
                if (!mLoading && mPage < mTotalPage){
                    if (visibleItemCount + pastVisibleItem >= total){
                        mPage++
                        getRecommendedItems(false)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

//
//        // 목록에서 각 Content 영역 하단에 구분되는 gray bottom bar 그리는 부분
//        val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
//        dividerItemDecoration.setDrawable(this.context?.let { getDrawable(it, R.drawable.recyclerview_divider_line) }!!)
//        mRecyclerView.addItemDecoration(dividerItemDecoration)
//        mRecyclerView.adapter = ItemAdapter(this.context, items) {
//            startActivity(Intent(this.context, ItemActivity::class.java))
//        }
    }

    private fun getRecommendedItems(isRefresh: Boolean) {
        mLoading = true
        if (!isRefresh) mProgressBar.visibility = View.VISIBLE

        mViewModel.loadData(mPage).observe(viewLifecycleOwner, { networkResource ->
            when (networkResource.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    mTotalPage = networkResource.data?.body()?.totalElements!!

                    val listResponse = networkResource.data.body()?.content
                    if (listResponse != null){
                        mAdapter.addList(listResponse)
                    }

                    mProgressBar.visibility = View.GONE
                    mLoading = false
                    mBinding.spRecommendedItem.isRefreshing = false
                }
                Status.ERROR -> {
                    mProgressBar.visibility = View.GONE
                    mLoading = false
                    mBinding.spRecommendedItem.isRefreshing = false
                }
            }
        })
    }

    override fun onRefresh() {
        mAdapter.clear()
        mPage = 0
        getRecommendedItems(true)
    }
}