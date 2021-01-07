package com.spray.stock.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spray.stock.BuildConfig
import com.spray.stock.R
import com.spray.stock.R.layout.fragment_notice
import com.spray.stock.adapters.NoticeBoardAdapter
import com.spray.stock.api.NoticeBoardApi
import com.spray.stock.client.RetrofitClient
import com.spray.stock.databinding.FragmentNoticeBinding
import com.spray.stock.model.noticeBoard.NoticeBoard
import com.spray.stock.model.noticeBoard.NoticeBoardResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class NoticeFragment : Fragment() {

    private lateinit var mBinding: FragmentNoticeBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: NoticeBoardAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mProgressBar: ProgressBar
    private var mList: MutableList<NoticeBoard> = mutableListOf()

    private var mVisibleItemCount: Int = 0
    private var mTotalItemCount: Int = 0
    private var mPastVisibleItemCount: Int = 0
    private var mLoading: Boolean = false

    private var mPage = 1
    private var mSize = 20

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_notice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mProgressBar = mBinding.pbNoticeBoard
        mRecyclerView = mBinding.rvNoticeBoard
        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.setHasFixedSize(true)

        // 목록에서 각 Content 영역 하단에 구분되는 gray bottom bar 그리는 부분
        val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(this.context?.let {
            AppCompatResources.getDrawable(
                it,
                R.drawable.recyclerview_divider_line
            )
        }!!)

        // 각 Row 부분의 하단에 회색 선 (bar) 넣는 부분
        mRecyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun getNoticeBoards(page: Int) {
        mProgressBar.visibility = View.VISIBLE // 공지사항 목록을 가져오기 시작하면 프로그레스바를 Visible (보여줌) 으로 설정
        val api: NoticeBoardApi = RetrofitClient.get(BuildConfig.BASE_URL)!!.create(NoticeBoardApi::class.java)
        val call: Call<NoticeBoardResponse> = api.getNoticeBoards(page, mSize)

        // API 를 call 하며 2개의 오버라이드 메서드를 작성한다.
        call.enqueue(object: Callback<NoticeBoardResponse> {
            override fun onResponse(call: Call<NoticeBoardResponse>, response: Response<NoticeBoardResponse>) {
                response.takeIf { it.code() == 200 }?.apply {
                    mLoading = true
                    response.body()?.let { setUpAdapter(it) }
                }

                mProgressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<NoticeBoardResponse>, t: Throwable) {
                try {
                    mProgressBar.visibility = View.GONE
                    t.message?.let { Log.d("tag", it) }
                } catch (e: Exception) {

                }
            }
        })
    }

    private fun setUpAdapter(body: NoticeBoardResponse) {
        if (body.totalElements == 0) {
            mRecyclerView.adapter = NoticeBoardAdapter(body)
        } else {
            mList.addAll(body.content)
            mAdapter.notifyDataSetChanged()

            val currentPosition = (mRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            mRecyclerView.scrollToPosition(currentPosition)
        }

        mRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    mVisibleItemCount = mLayoutManager.childCount
                    mTotalItemCount = mLayoutManager.itemCount
                    mPastVisibleItemCount = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    mLoading.takeIf { (mVisibleItemCount + mPastVisibleItemCount) >= mTotalItemCount }.apply {
                        mLoading = false             // 로딩을 비활성화
                        mPage++                      // page 번호를 증가 시킨다. (즉, 다음 페이지로 넘길수 있도록 page 번호 미리 증가 시켜 놓는다.)
                        getNoticeBoards(mPage)       // 위에서 증가된 page 번호를 매게변수로 넘겨 공지사항 목록을 가져온다.
                    }
                }
            }
        })
    }
}