package com.spray.stock.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.spray.stock.R
import com.spray.stock.databinding.ActivityMainBinding
import com.spray.stock.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1550847067-93887e03cfbe?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max")
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .transform(CenterInside(), RoundedCorners(6))
            .into(mBinding.ivTestBanner)


    }
}