package com.spray.stock.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.*
import com.spray.stock.R
import com.spray.stock.databinding.ActivityMainBinding
import com.spray.stock.views.fragments.*

class MainActivity : AppCompatActivity() {
    private val mainFragment by lazy { MainFragment() }
    private val newsFragment by lazy { NewsFragment() }
    private val themeFragment by lazy { ThemeFragment() }
    private val reportFragment by lazy { ReportFragment() }
    private val noticeFragment by lazy { NoticeFragment() }

    private var mFloatingOpen = false
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        // 구글 배너 광고 초기화
        MobileAds.initialize(this) {}

        // 네비게이션 초기화
        initNavigationBar (mBinding)

        val floatingOpen = AnimationUtils.loadAnimation(this, R.anim.floating_open)
        val floatingClose = AnimationUtils.loadAnimation(this, R.anim.floating_close)
//        val floatingWise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
//        val floatingAntiWise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise)

        mBinding.btnMainAdd.setOnClickListener {
            if (mFloatingOpen) {
                mBinding.btnMainMy.startAnimation(floatingClose)
                mBinding.btnMainMembership.startAnimation(floatingClose)
                //mBinding.btnMainAdd.startAnimation(floatingWise)

                mFloatingOpen = false
            } else {
                mBinding.btnMainMy.startAnimation(floatingOpen)
                mBinding.btnMainMembership.startAnimation(floatingOpen)
                //mBinding.btnMainAdd.startAnimation(floatingAntiWise)

                mBinding.btnMainMy.isClickable
                mBinding.btnMainMembership.isClickable

                mFloatingOpen = true
            }

        }
    }

    private fun initNavigationBar(binding: ActivityMainBinding) {
        binding.bottomNaviMain.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menuMain -> {
                        loadBannerAd(binding)
                        changeFragment(mainFragment)
                    }
                    R.id.menuNews -> {
                        loadBannerAd(binding)
                        changeFragment(newsFragment)
                    }
                    R.id.menuTheme -> {
                        loadBannerAd(binding)
                        changeFragment(themeFragment)
                    }
                    R.id.menuReport -> {
                        loadBannerAd(binding)
                        changeFragment(reportFragment)
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