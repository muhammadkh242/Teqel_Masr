package com.example.teqelmasr.network


import com.example.teqelmasr.model.Product
import retrofit2.Response

import com.example.teqelmasr.model.ProductItem
import com.example.teqelmasr.model.ProductPost


interface RemoteSource {
    suspend fun storeProduct(product: ProductPost): Response<ProductItem>
    suspend fun fetchSpareParts() : Response<ProductItem>

    suspend fun getMyProducts(): Response<ProductItem>

    suspend fun deleteProduct(product: Product)
}