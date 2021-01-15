package com.spray.stock.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.spray.stock.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}