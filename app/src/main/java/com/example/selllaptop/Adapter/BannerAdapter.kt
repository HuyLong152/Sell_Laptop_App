package com.example.selllaptop.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.selllaptop.Model.Brand
import com.example.selllaptop.R
import com.example.selllaptop.Utils.constrain

class BannerAdapter(var listBanner:List<String>): RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    class BannerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var bannerImage = itemView.findViewById<ImageView>(R.id.brandImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.banner_item ,parent,false))
    }

    override fun getItemCount(): Int {
        return listBanner.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        Glide.with(holder.bannerImage)
            .load(listBanner[position])
            .into(holder.bannerImage)
    }
}
