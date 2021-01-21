package com.spray.stock.views

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.spray.stock.BuildConfig
import com.spray.stock.api.ItemInfoApi
import com.spray.stock.config.RetrofitClient
import com.spray.stock.databinding.ActivityItemBinding

class ItemActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityItemBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mBinding = ActivityItemBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        mBinding.ivItemChart.clipToOutline = true

        val name = intent.getStringExtra("name")

        //binding.tvItemName.text = name
        mBinding.tvItemContentName.text = name
        mBinding.tvItemContentNumber.text = intent.getStringExtra("number").toString()

        mBinding.btnBack.setOnClickListener {
            onBackPressed();
        }
    }

    private fun updateView(id: Long) {
        // 레트로핏으로 정보 가져온다.
        val api: ItemInfoApi = RetrofitClient.get(BuildConfig.ITEM_BASE_URL)!!.create(ItemInfoApi::class.java)
        //val call: Call<NoticeBoardResponse> = api.getNoticeBoards(page, mSize)

    }
}