package com.spray.stock.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.*
import com.spray.stock.R
import com.spray.stock.databinding.ActivityMainBinding
import com.spray.stock.fragments.LatestFragment
import com.spray.stock.fragments.MainFragment
import com.spray.stock.fragments.NoticeFragment
import com.spray.stock.fragments.PostFragment

class MainActivity : AppCompatActivity() {
    private val mainFragment by lazy { MainFragment() }
    private val postFragment by lazy { PostFragment() }
    private val latestFragment by lazy { LatestFragment() }
    private val noticeFragment by lazy { NoticeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 구글 배너 광고 초기화
        MobileAds.initialize(this) {}

        // 네비게이션 초기화
        initNavigationBar (binding)
    }

    private fun initNavigationBar(binding: ActivityMainBinding) {
        binding.bottomNaviMain.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menuMain -> {
                        loadBannerAd(binding)
                        changeFragment(mainFragment)
                    }
                    R.id.menuPost -> {
                        loadBannerAd(binding)
                        changeFragment(postFragment)
                    }
                    R.id.menuLatest -> {
                        loadBannerAd(binding)
                        changeFragment(latestFragment)
                    }
                    R.id.menuNotice -> {

                        changeFragment(noticeFragment)
                    }
                }
                true
            }
            selectedItemId = R.id.menuMain
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment).commit()
    }

    private fun loadBannerAd(binding: ActivityMainBinding) {
        binding.adViewBanner.loadAd(AdRequest.Builder().build())
    }
}