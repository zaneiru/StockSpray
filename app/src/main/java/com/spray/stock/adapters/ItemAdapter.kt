package com.spray.stock.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.spray.stock.views.ItemActivity
import com.spray.stock.R
import com.spray.stock.models.Items

class ItemAdapter(
    private val context: Context?,
    private val items: ArrayList<Items>,
    function: () -> Unit
) : RecyclerView.Adapter<ItemAdapter.CustomViewHolder>() {

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_items, parent, false)

        return CustomViewHolder(view).apply {

            // start : 구글 전면 광고 정보
            MobileAds.initialize(context) {}
            mInterstitialAd = InterstitialAd(context)
            mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
            // end : 구글 전면 광고 정보

            itemView.setOnClickListener {
                mInterstitialAd.loadAd(AdRequest.Builder().build())
                mInterstitialAd.adListener = object: AdListener() {
                    override fun onAdLoaded() {
                        if (mInterstitialAd.isLoaded) {
                            mInterstitialAd.show()
                        }
                    }

                    // 전면광고 load 실패할 경우엔 바로 종목 상세 정보로 이동할 수 있도록 한다.
                    override fun onAdFailedToLoad(errorCode: Int) {
                        Log.d("d", "Google interstitial Ad load fail")
                        moveToItemActivity(itemView, adapterPosition)
                    }

                    // 전면광고를 close 하면 종목 상세 정보를 이동할 수 있도록 한다.
                    override fun onAdClosed() {
                        moveToItemActivity(itemView, adapterPosition)
                    }
                }
            }
        }
    }

    private fun moveToItemActivity(itemView: View, adapterPosition: Int) {
        val intent = Intent(context, ItemActivity::class.java)
        intent.putExtra("name", items[adapterPosition].name)
        intent.putExtra("number", 103840)
        itemView.context.startActivity(intent)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
     //   holder.name.text = items[position].name
        holder.type.text = items[position].type
        holder.marketCap.text = items[position].marketCap
        holder.updown.text = items[position].updown
        holder.tradingMoney.text = items[position].tradingMoney
        holder.tag.text = items[position].tag
        holder.number.text = items[position].number
    }

    override fun getItemCount() = items.size

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val name: TextView = view.findViewById(R.id.tv_items_item_title)   // 종목명
        val type: TextView = view.findViewById(R.id.tv_type)        // 타입
        val marketCap: TextView = view.findViewById(R.id.tv_market_cap)  // 거래소 / 코스닥 종류
        val updown: TextView = view.findViewById(R.id.tv_updown)
        val tradingMoney: TextView = view.findViewById(R.id.tv_trading_money)
        val tag: TextView = view.findViewById(R.id.tv_tag)
        val number: TextView = view.findViewById(R.id.tv_item_number)
    }
}