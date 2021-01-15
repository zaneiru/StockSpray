package com.spray.stock.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spray.stock.views.ItemActivity
import com.spray.stock.adapters.ItemAdapter
import com.spray.stock.models.Items
import com.spray.stock.R

class MainFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO : 이 부분에서 API로 추천종목 가져오는 부분을 넣어야 함.

        val items = arrayListOf(
            Items(
                "SBI핀테크솔루션즈",
                "거래소",
                "9,980억",
                "▲ 130,000원 (12.93%)",
                "1,290억",
                "자동차부품",
                "(950110)"
            ),
            Items("새론오토모티브", "거래소", "1,057억", "▲ 430원 (12.93%)", "994억", "자동차부품", "(075180)"),
            Items("씨젠", "코스닥", "5조 3100억", "▲ 1,4000원 6.91%)", "2,860억", "백신/방역, 진단키드", "(096530)"),
            Items("태림포장", "거래소", "4,616억", "▲ 150원 (2.30%)", "502억", "골판지, 포장", "(011280)")
        )

        mRecyclerView = view.findViewById(R.id.rv_main)
        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.setHasFixedSize(true)

        // 목록에서 각 Content 영역 하단에 구분되는 gray bottom bar 그리는 부분
        val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(this.context?.let { getDrawable(it, R.drawable.recyclerview_divider_line) }!!)
        mRecyclerView.addItemDecoration(dividerItemDecoration)
        mRecyclerView.adapter = ItemAdapter(this.context, items) {
            startActivity(Intent(this.context, ItemActivity::class.java))
        }
    }
}