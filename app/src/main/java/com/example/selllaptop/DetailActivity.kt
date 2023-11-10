package com.example.selllaptop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.selllaptop.Model.Laptop
import com.example.selllaptop.Model.LaptopItem
import com.example.selllaptop.NetWork.LaptopApi
import com.example.selllaptop.NetWork.LaptopService
import com.example.selllaptop.Utils.constrain
import com.example.selllaptop.databinding.ActivityDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    lateinit var binding :ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val laptopID = intent.getIntExtra("LaptopId",0)
        var priceProduct = 0
        binding.iconBack.setOnClickListener{
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }
        LaptopService.api.getProductById(laptopID).enqueue(object :Callback<Laptop>{
            override fun onResponse(call: Call<Laptop>, response: Response<Laptop>) {
                var laptopItem = response.body()!![0]
                binding.pName.text = laptopItem.name.toString()
                binding.pTitle.text = laptopItem.description.toString()
                binding.pPrice.text = NumberFormat.getNumberInstance(Locale.US).format(laptopItem.price).toString() + "VNĐ"
                priceProduct = laptopItem.price
                Glide.with(binding.mainImg)
                    .load(constrain.BASE_URL_IMAGE + laptopItem.images[0])
                    .into(binding.mainImg)
                Glide.with(binding.img2)
                    .load(constrain.BASE_URL_IMAGE + laptopItem.images[0])
                    .into(binding.img2)
                Glide.with(binding.img3)
                    .load(constrain.BASE_URL_IMAGE + laptopItem.images[1])
                    .into(binding.img3)
                Glide.with(binding.img4)
                    .load(constrain.BASE_URL_IMAGE + laptopItem.images[2])
                    .into(binding.img4)
                binding.img2.setOnClickListener{
                    Glide.with(binding.mainImg)
                        .load(constrain.BASE_URL_IMAGE + laptopItem.images[0])
                        .into(binding.mainImg)
                }
                binding.img3.setOnClickListener{
                    Glide.with(binding.mainImg)
                        .load(constrain.BASE_URL_IMAGE + laptopItem.images[1])
                        .into(binding.mainImg)
                }
                binding.img4.setOnClickListener{
                    Glide.with(binding.mainImg)
                        .load(constrain.BASE_URL_IMAGE + laptopItem.images[2])
                        .into(binding.mainImg)
                }
            }

            override fun onFailure(call: Call<Laptop>, t: Throwable) {

            }

        })
        binding.btnPlus.setOnClickListener {
            var quantity = binding.btnQuantity.text.toString().toInt()
            quantity++
            binding.btnQuantity.setText(quantity.toString())
            binding.pPrice.text = NumberFormat.getNumberInstance(Locale.US).format(priceProduct * quantity).toString() + "VNĐ"


        }

        binding.btnMinus.setOnClickListener {
            var quantity = binding.btnQuantity.text.toString().toInt()
            if(quantity > 1){
                quantity--
                binding.btnQuantity.text = quantity.toString()
                binding.pPrice.text = NumberFormat.getNumberInstance(Locale.US).format(priceProduct * quantity).toString() + "VNĐ"

            }
        }

        binding.btnBuy.setOnClickListener {
            val intent = Intent(this , PaymentActivity::class.java)
                .putExtra("LaptopId",laptopID)
                .putExtra("Quantity",binding.btnQuantity.text.toString().toInt())
            startActivity(intent)
        }

    }
}