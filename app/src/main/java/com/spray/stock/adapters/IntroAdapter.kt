package com.spray.stock.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spray.stock.R
import com.spray.stock.models.Intro

class IntroAdapter(private val context: Context, private val intros: ArrayList<Intro>): RecyclerView.Adapter<IntroAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.intro_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.introImage.setImageResource(intros[position].image)
        holder.introContent.text = intros[position].content

        if(intros[position].bgColor != R.color.colorWhite){
            holder.introContent.setTextColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int = intros.size

    inner class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val introImage: ImageView = view.findViewById(R.id.iv_logo_by_intro_item)
        val introContent: TextView = view.findViewById(R.id.tv_content_by_intro_item)
    }
}