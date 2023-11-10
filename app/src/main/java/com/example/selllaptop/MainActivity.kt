package com.example.selllaptop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.selllaptop.Adapter.BannerAdapter
import com.example.selllaptop.Adapter.BrandAdapter
import com.example.selllaptop.Adapter.LaptopAdapter
import com.example.selllaptop.Model.Brand
import com.example.selllaptop.Model.Laptop
import com.example.selllaptop.NetWork.LaptopService
import com.example.selllaptop.Utils.constrain.listBanner
import com.example.selllaptop.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager2.adapter = BannerAdapter(listBanner)

        LaptopService.api.getBrandLaptop().enqueue(object :Callback<List<Brand>>{
            override fun onResponse(call: Call<List<Brand>>, response: Response<List<Brand>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    binding.recyclerView.adapter = BrandAdapter(data!!)
                } else {
                    // Xử lý lỗi ở đây
                }
            }

            override fun onFailure(call: Call<List<Brand>>, t: Throwable) {

            }

        })
        LaptopService.api.getAllLaptop().enqueue((object  :Callback<Laptop>{
            override fun onResponse(call: Call<Laptop>, response: Response<Laptop>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    binding.ProductRecy.adapter = LaptopAdapter(data!!){

                        val intent = Intent(this@MainActivity , DetailActivity::class.java)
                            .putExtra("LaptopId",it.id)

                        startActivity(intent)
                    }

                } else {
                    // Xử lý lỗi ở đây
                }
            }

            override fun onFailure(call: Call<Laptop>, t: Throwable) {

            }

        }))
    }
}