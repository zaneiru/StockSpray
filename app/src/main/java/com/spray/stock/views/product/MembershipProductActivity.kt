package com.spray.stock.views.product

import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.spray.stock.R
import com.spray.stock.adapters.MembershipProductAdapter
import com.spray.stock.databinding.ActivityMembershipProductBinding
import com.spray.stock.models.product.MembershipProduct

class MembershipProductActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMembershipProductBinding

    private lateinit var mMembershipProducts: MutableList<MembershipProduct>
    private lateinit var mMembershipAdapter: MembershipProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩
        mBinding = ActivityMembershipProductBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        loadMembershipProduct()

        mBinding.tvCloseMembershipProduct.setOnClickListener {
            onBackPressed()
        }
    }

    private fun loadMembershipProduct() {
        mMembershipProducts = ArrayList()
        mMembershipProducts.add(MembershipProduct(1, "a", "무료 1일", "1일 동안 무료로 뉴스를 30초 주기로 확인할 수 있는 상품입니다.\\n기존 뉴스 알림이 2분 주기로 업데이트 되는 것과 다르게 30초 주기로 업데이트 되는 뉴스를 받으실 수 있습니다.", R.drawable.bg_background))
        mMembershipProducts.add(MembershipProduct(2, "d", "유료 1일", "되냐?", R.drawable.background_round))

        mMembershipAdapter = MembershipProductAdapter(this, mMembershipProducts)

        mBinding.vpMembershipProduct.apply {
            adapter = mMembershipAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        mBinding.vpMembershipProduct.setPadding(100, 0, 100, 0)

    }

    override fun onPause() {
        super.onPause()
    }

    override fun unbindService(conn: ServiceConnection) {
        try {
            super.unbindService(conn)
        } catch (e: IllegalArgumentException) {
            // do something, ignore or report...
        }
    }
}