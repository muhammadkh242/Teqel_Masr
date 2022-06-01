package com.example.teqelmasr.network


import android.util.Log
import com.example.teqelmasr.model.ProductItem


import com.example.teqelmasr.model.ProductPost

import retrofit2.Response

class Client : RemoteSource {
    /*override suspend fun getCurrentWeather(units: String, lat: String, lng: String, lang: String): WeatherResponse {
        val weatherService = RetrofitHelper.getInstance().create(WeatherService::class.java)
        return weatherService.getCurrentWeather(units, lat, lng, lang)
    }*/
    val productsService = ApiManager.getInstance().create(WebService::class.java)
     companion object{
            private var instance: Client? = null
            fun getInstance(): Client{
                return  instance?: Client()
            }
        }

    override suspend fun fetchSpareParts() : Response<ProductItem> {
        val productsService = ApiManager.getInstance().create(WebService::class.java)
        return productsService.getProducts()

    }

    override suspend fun getMyProducts(): Response<ProductItem> {
        val service = ApiManager.getInstance().create(WebService::class.java)
        return service.getMyProducts()
    }

    override suspend fun storeProduct(product: ProductPost): Response<ProductItem> {
        Log.i("Tag",product.toString() + "imgggggg")
        val productsService = ApiManager.getInstance().create(WebService::class.java)
        return productsService.storeProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        val service = ApiManager.getInstance().create(WebService::class.java)
        service.deleteProduct(product.variants[0].product_id!!)

    }

}