package com.example.cristina_salazar_retrofit.data.remote

import com.example.cristina_salazar_retrofit.data.model.PagedResponse
import com.example.cristina_salazar_retrofit.data.model.ItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int = 1
    ): PagedResponse<ItemDto>
}