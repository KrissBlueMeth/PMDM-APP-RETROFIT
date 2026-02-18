package com.example.cristina_salazar_retrofit.data.model

data class ItemDto(

    val _id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
    val active: Boolean
)
