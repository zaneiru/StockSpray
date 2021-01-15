package com.spray.stock.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.spray.stock.R
import com.spray.stock.adapters.IntroAdapter
import com.spray.stock.databinding.ActivityIntroBinding
import com.spray.stock.models.Intro

class IntroActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityIntroBinding
    private lateinit var mIntroAdapter: IntroAdapter
    private var mIntros = ArrayList<Intro>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnPreviousByIntro.setOnClickListener {
            Log.d(TAG, "MainActivity - 이전 버튼 클릭")
            mBinding.vpIntro.currentItem = mBinding.vpIntro.currentItem.minus(1)
        }

        mBinding.btnNextByIntro.setOnClickListener {
            Log.d(TAG, "MainActivity - 다음 버튼 클릭")
            mBinding.vpIntro.currentItem = mBinding.vpIntro.currentItem?.plus(1)
        }

        mIntros.add(Intro(R.color.colorOrange, R.drawable.ic_intro_pager_one, "안녕하세요!\n개발하는 정대리입니다!"))
        mIntros.add(Intro(R.color.colorBlue, R.drawable.ic_intro_pager_two, "구독, 좋아요 눌러주세요!"))
        mIntros.add(Intro(R.color.colorWhite, R.drawable.ic_intro_pager_three, "알람설정 부탁드립니다!"))

        mIntroAdapter = IntroAdapter(this, mIntros)

        // 뷰페이저에 설정
        mBinding.vpIntro.apply {
            this.adapter = mIntroAdapter
            this.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            this.let { mBinding.dotsIndicator.setViewPager2(it) }
        }
    }

    companion object {
        const val TAG: String = "로그"
    }
}