package com.spray.stock.config

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.spray.stock.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StockApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // 카카오 로그인을 위한 초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}