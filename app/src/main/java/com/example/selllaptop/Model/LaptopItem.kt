package com.example.selllaptop.Model

data class LaptopItem(
    val brand: Brand,
    val category: Category,
    val configurations: Configurations,
    val description: String,
    val id: Int,
    val images: List<String>,
    val name: String,
    val price: Int,
    val quantity: Int
)