package com.spray.stock.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.user.UserApiClient
import com.spray.stock.databinding.ActivityAuthKakaoBinding

class AuthKakaoActivity : AppCompatActivity() {

    private lateinit var mBiding: ActivityAuthKakaoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val keyHash = Utility.getKeyHash(this)
//        Log.d(TAG_OF_KAKAO_AUTH, keyHash)

        // 사용자가 카카오에 로그인되어 있는 상태인지 체크
        //this.getKakaoLoginStatus()

        // 바인딩
        mBiding = ActivityAuthKakaoBinding.inflate(layoutInflater)
        setContentView(mBiding.root)

        // 닫기 버튼 클릭시 이전 화면으로 이동
        mBiding.btnCloseAtAuthKakao.setOnClickListener {
            onBackPressed();
        }

        // 카카오 로그인 버튼 클릭시 카카오 로그인 화면으로 이동
        mBiding.btnAuthKakaoLogin.setOnClickListener {
            LoginClient.instance.loginWithKakaoTalk(mBiding.root.context) { token, error ->
                if (error != null) {
                    Log.e(TAG_OF_KAKAO_AUTH, "로그인 실패", error)
                } else if (token != null) {
                    Log.i(TAG_OF_KAKAO_AUTH, "로그인 성공 ${token.accessToken}")
                }
            }
        }
    }

    // 사용자가 카카오에 로그인되어 있는지 여부 체크
    private fun getKakaoLoginStatus() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.d(TAG_OF_KAKAO_AUTH, "사용자 정보 요청 실패", error)
            } else {
                if (user != null) {
                    Log.i(
                        TAG_OF_KAKAO_AUTH, "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                } else {
                    Log.i(TAG_OF_KAKAO_AUTH, "비로그인 상태")
                }
            }
        }
    }

    companion object {
        private const val TAG_OF_KAKAO_AUTH = "[Log] : 카카오 로그인"
    }
}