package com.example.teqelmasr.model

import retrofit2.Response

interface RepositoryInterface {

    suspend fun getMyProducts(): Response<ProductItem>

    suspend fun storeProduct(product: ProductPost): Response<ProductItem>


    suspend fun deleteProduct(product: Product)

    suspend fun getProductsByCategory(productCategory: String) :  Response<ProductItem>
}