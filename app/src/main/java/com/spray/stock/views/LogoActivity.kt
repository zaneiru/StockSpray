package com.spray.stock.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.spray.stock.databinding.ActivityLogoBinding

class LogoActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityLogoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLogoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        // TODO : 이 부분에 shared preference 적용해야 함.
        // 최초 한번 봤으면 shared preference에 저장하고
        // 한번 본 경우엔 introActivity가 아니라 MainActivity로 이동해야 함.
        Handler(Looper.getMainLooper()).postDelayed(  {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}