package com.example.selllaptop.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.selllaptop.Model.Brand
import com.example.selllaptop.R
import com.example.selllaptop.Utils.constrain.BASE_URL_IMAGE

class BrandAdapter(var listBrand:List<Brand>):RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {
    class BrandViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var brandImage = itemView.findViewById<ImageView>(R.id.brandImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.brand_item ,parent,false))
    }

    override fun getItemCount(): Int {
        return listBrand.size
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        Glide.with(holder.brandImage)
            .load(BASE_URL_IMAGE + listBrand[position].imageurl)
            .into(holder.brandImage)
    }
}