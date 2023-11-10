package com.example.selllaptop.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.selllaptop.Model.Laptop
import com.example.selllaptop.Model.LaptopItem
import com.example.selllaptop.R
import com.example.selllaptop.Utils.constrain.BASE_URL_IMAGE
import java.text.NumberFormat
import java.util.Locale

class LaptopAdapter(
    val laptop: Laptop,
    val onItemClick :(LaptopItem) -> Unit
): RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder>() {
    class LaptopViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var LaptopImage = itemView.findViewById<ImageView>(R.id.product_image)
        var LaptopName = itemView.findViewById<TextView>(R.id.product_name)
        var LaptopPrice = itemView.findViewById<TextView>(R.id.product_price)
        var LaptopItem = itemView.findViewById<ConstraintLayout>(R.id.Select_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaptopViewHolder {
        return LaptopViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product ,parent,false))
    }

    override fun getItemCount(): Int {
        return laptop.size
    }

    override fun onBindViewHolder(holder: LaptopViewHolder, position: Int) {
        Glide.with(holder.LaptopImage)
            .load(BASE_URL_IMAGE + laptop[position].images[0])
            .into(holder.LaptopImage)
        holder.LaptopName.text = laptop[position].name
        holder.LaptopPrice.text = NumberFormat.getNumberInstance(Locale.US).format(laptop[position].price).toString() + "VNĐ"
        holder.LaptopItem.setOnClickListener{
            onItemClick(laptop[position])
        }
    }
}