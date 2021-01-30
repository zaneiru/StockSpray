package com.spray.stock.views

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.spray.stock.R
import com.spray.stock.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var mFloatingOpen = false
    private var mBinding: ActivityMainBinding? = null
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)

        // 네비게이션 초기화
        // 네비게이션들을 담는 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

        // 네비게이션 컨트롤러
        mNavController = navHostFragment.navController

        // 바텀 네비게이션 뷰 와 네비게이션을 묶어준다
        NavigationUI.setupWithNavController(mBinding?.myBottomNav!!, mNavController)

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

        // 이미 선택된 Bottom 메뉴를 또 다시 눌렀을땐 리프레시 않하도록 ....
        mBinding!!.myBottomNav.setOnNavigationItemSelectedListener {
            if (it.itemId != mBinding!!.myBottomNav.selectedItemId)
                NavigationUI.onNavDestinationSelected(it, mNavController)
            true
        }
    }



    /*
    * bottomNavigation.setOnNavigationItemSelectedListener {
                if (it.itemId != bottomNavigation.selectedItemId)
                    NavigationUI.onNavDestinationSelected(it, navController)
                 true

}
    * */
//
//    private lateinit var currentNavController: LiveData<NavController>
//
//    private fun setupBottomNavigationBar() {
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.my_bottom_nav)
//        val navGraphIds = listOf(R.id.mainFragment, R.id.newsFragment, R.id.themeFragment, R.id.reportFragment, R.id.noticeFragment)
//        val controller = bottomNavigationView.setupWithNavController(
//            navController = mNavController
//        )
//        controller.apply {
//            AppBarConfiguration(navGraphIds.toSet())
//            NavigationUI.setupWithNavController(mBinding?.myBottomNav!!, mNavController)
//        }
//        currentNavController = controller
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return currentNavController!!.value?.navigateUp() ?: false
//    }


//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        Log.d("onOptionsItemSelected", item.title.toString())
////        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
////        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
//        return super.onOptionsItemSelected(item)
//    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}