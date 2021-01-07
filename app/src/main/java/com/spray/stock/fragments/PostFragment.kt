package com.spray.stock.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spray.stock.ItemActivity
import com.spray.stock.R
import com.spray.stock.adapters.ItemAdapter
import com.spray.stock.model.Items

class PostFragment : Fragment() {

    lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_post, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = arrayListOf(
            Items(
                "MP한",
                "거래소",
                "9,980억",
                "▲ 130,000원 (12.93%)",
                "1,290억",
                "자동차부품",
                "(950110)"
            ),
            Items("삼성전", "거래소", "1,057억", "▲ 430원 (12.93%)", "994억", "자동차부품", "(075180)"),
            Items("SK바이오", "코스닥", "5조 3100억", "▲ 1,4000원 6.91%)", "2,860억", "백신/방역, 진단키드", "(096530)"),
            Items("파미셀", "거래소", "4,616억", "▲ 150원 (2.30%)", "502억", "골판지, 포장", "(011280)")
        )

        recyclerView = view.findViewById(R.id.rv_main)
        //val adapter = ItemAdapter(items)

        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)


        val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(this.context?.let {
            AppCompatResources.getDrawable(
                it,
                R.drawable.recyclerview_divider_line
            )
        }!!)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = ItemAdapter(this.context, items) {
            val intent = Intent(this.context, ItemActivity::class.java)
            startActivity(intent)
        }


    }
}