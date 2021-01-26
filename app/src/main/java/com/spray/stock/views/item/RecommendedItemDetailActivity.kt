package com.spray.stock.views.item

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jakewharton.threetenabp.AndroidThreeTen
import com.spray.stock.adapters.RecommendedItemCommentAdapter
import com.spray.stock.databinding.ActivityRecommendedItemDetailBinding
import com.spray.stock.dialogs.CommentDialog
import com.spray.stock.dialogs.CommentDialogImpl
import com.spray.stock.viewModels.Status
import com.spray.stock.viewModels.items.RecommendedItemCommentViewModel
import com.spray.stock.viewModels.items.RecommendedItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecommendedItemDetailActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener, CommentDialog {

    private var mBinding: ActivityRecommendedItemDetailBinding? = null
    private lateinit var mRecyclerView: RecyclerView

    private var mId: Long = 0

    private lateinit var mAdapter: RecommendedItemCommentAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mProgressBar: ProgressBar
    private val mViewModel: RecommendedItemViewModel by viewModels()
    private val mCommentViewModel: RecommendedItemCommentViewModel by viewModels()

    private var mLoading: Boolean = false

    private var mPage = 0
    private var mTotalPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRecommendedItemDetailBinding.inflate(layoutInflater)
        setContentView(mBinding?.root!!)
        AndroidThreeTen.init(this)

        mId = intent.getLongExtra("id", 1)

        mSwipeRefreshLayout = mBinding?.spRecommendedItemDetail!!
        mProgressBar = mBinding?.pbRecommendedItemDetail!!
        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mSwipeRefreshLayout.setOnRefreshListener(this)
        mAdapter = RecommendedItemCommentAdapter(this)
        mRecyclerView = mBinding?.rvRecommendedItemDetail!!
        with(mRecyclerView) {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

        getRecommendedItemComments(false)

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = mLayoutManager.childCount
                val pastVisibleItem = mLayoutManager.findFirstVisibleItemPosition()
                val total = mAdapter.itemCount

                if (!mLoading && mPage < mTotalPage) {
                    if (visibleItemCount + pastVisibleItem >= total && total >= 20) {
                        mPage++
                        getRecommendedItemComments(false)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        mBinding!!.btnRecommendedDetailBack.setOnClickListener {
            Toast.makeText(this, "TESTING BUTTON CLICK 1", Toast.LENGTH_SHORT).show()
        }

        // 코멘트 작성 클릭시 코멘트 다이얼로그 show
        val commentDialogImpl = CommentDialogImpl(this, this)
        mBinding!!.btnWrite.setOnClickListener {
            commentDialogImpl.window?.apply {
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                setGravity(Gravity.BOTTOM)
            }
            commentDialogImpl.show()
        }

        // 테마 추천 상세 화면 (레이아웃) 클릭시 키보드 비활성화
//        val layout: LinearLayout = findViewById(R.id.activity_recommended_item_detail)
//        commentDialogImpl.setOnCancelListener {
//            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
//            imm.hideSoftInputFromWindow(layout.windowToken, 0);
//        }
    }

    private fun getRecommendedItemComments(isRefresh: Boolean) {
        mLoading = true
        if (!isRefresh) mProgressBar.visibility = View.VISIBLE

        mCommentViewModel.loadRecommendedItemComments(mId, mPage).observe(this, { networkResource ->
            when (networkResource.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    mTotalPage = networkResource.data?.body()?.totalElements!!
                    val listResponse = networkResource.data.body()?.content

                    mAdapter.submitList(listResponse!!.toMutableList())
                    mProgressBar.visibility = View.GONE
                    mLoading = false
                    mBinding?.spRecommendedItemDetail?.isRefreshing = false
                }
                Status.ERROR -> {
                    mProgressBar.visibility = View.GONE
                    mLoading = false
                    mBinding?.spRecommendedItemDetail?.isRefreshing = false
                }
            }
        })
    }

    override fun onRefresh() {
        //mAdapter.clear()
        mPage = 0
        getRecommendedItemComments(true)
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    // 코멘트 저장 버튼 클릭
    override fun onCommentWriteClicked() {
        Log.d("메인 액티비티 로그", "메인 액티비에서 넘어왔어용!")
    }
}
