package com.example.selllaptop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.selllaptop.Model.Laptop
import com.example.selllaptop.NetWork.LaptopService
import com.example.selllaptop.Utils.constrain
import com.example.selllaptop.databinding.ActivityPaymentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.Locale

class PaymentActivity : AppCompatActivity() {
    lateinit var binding : ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val laptopId = intent.getIntExtra("LaptopId",0)
        val quantity = intent.getIntExtra("Quantity",0)

        LaptopService.api.getProductById(laptopId).enqueue(object :Callback<Laptop>{
            override fun onResponse(call: Call<Laptop>, response: Response<Laptop>) {
                var laptop = response.body()!![0]
                binding.txtOldPrice.text = NumberFormat.getNumberInstance(Locale.US).format(laptop.price).toString()
                binding.txtQuantity.text = "X ${quantity}"
                binding.txtTotal.text = NumberFormat.getNumberInstance(Locale.US).format(laptop.price * quantity).toString() + "VNĐ"
                Glide.with(binding.pImg)
                    .load(constrain.BASE_URL_IMAGE + laptop.images[0])
                    .into(binding.pImg)
                binding.pName.text = laptop.name
                binding.pPrice.text = NumberFormat.getNumberInstance(Locale.US).format(laptop.price).toString() +"VNĐ"
            }

            override fun onFailure(call: Call<Laptop>, t: Throwable) {

            }

        })
        binding.btnBack.setOnClickListener{
            finish()
        }
        binding.btnHome.setOnClickListener{
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }
    }
}