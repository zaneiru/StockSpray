package com.spray.stock.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spray.stock.R
import com.spray.stock.models.product.MembershipProduct

class MembershipProductAdapter(private val context: Context, private val membershipProducts: List<MembershipProduct>):
    RecyclerView.Adapter<MembershipProductAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.membership_product_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name.text = membershipProducts[position].name
    }

    override fun getItemCount(): Int = membershipProducts.size

    inner class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_membership_product_name)
    }

//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view == `object`
//    }
//
//    override fun getCount(): Int {
//        return membershipProducts.size
//    }
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//
//        // inflate layout membership_product_item : xml에 정의된 부분들을 객체화 시켜 코드에서 사용할 수 있게 하는 부
//        val view = LayoutInflater.from(context).inflate(R.layout.membership_product_item, container, false)
//
//        // get data
//        val item = membershipProducts[position]
//        val name = item.name
//        val description = item.description
//        val image = item.image
//
//        val ivMembershipProduct: ImageView = view.findViewById(R.id.iv_membership_product_banner)
//        val tvMembershipProductName: TextView = view.findViewById(R.id.tv_membership_product_name)
//
//        ivMembershipProduct.setImageResource(image)
//        tvMembershipProductName.text = name
//
//        container.addView(view, position)
//
//        return view
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
//    }
}