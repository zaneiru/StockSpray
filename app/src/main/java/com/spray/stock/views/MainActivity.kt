package com.spray.stock.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.spray.stock.R
import com.spray.stock.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var mFloatingOpen = false
    private var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)

        // 네비게이션 초기화
        // 네비게이션들을 담는 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

        // 네비게이션 컨트롤러
        val navController = navHostFragment.navController

        // 바텀 네비게이션 뷰 와 네비게이션을 묶어준다
        NavigationUI.setupWithNavController(mBinding?.myBottomNav!!, navController)

        val floatingOpen = AnimationUtils.loadAnimation(this, R.anim.floating_open)
        val floatingClose = AnimationUtils.loadAnimation(this, R.anim.floating_close)

        mBinding?.btnMainAdd?.setOnClickListener {
            mFloatingOpen = if (mFloatingOpen) {
                mBinding?.btnMainMy?.startAnimation(floatingClose)
                mBinding?.btnMainMembership?.startAnimation(floatingClose)
                false
            } else {
                mBinding?.btnMainMy?.startAnimation(floatingOpen)
                mBinding?.btnMainMembership?.startAnimation(floatingOpen)
                mBinding?.btnMainMy?.isClickable
                mBinding?.btnMainMembership?.isClickable
                true
            }
        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}