package com.example.cristina_salazar_retrofit.data.repository

import com.example.cristina_salazar_retrofit.data.model.ItemDto
import com.example.cristina_salazar_retrofit.data.remote.ApiService

class MainRepository(private val api: ApiService) {
    suspend fun getProducts(page: Int = 1): List<ItemDto> =
        api.getProducts(page).results
}