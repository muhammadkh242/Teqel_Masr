package com.example.teqelmasr.model

import android.content.Context
import com.example.teqelmasr.network.RemoteSource
import retrofit2.Response

class Repository private constructor(
    var remoteSource: RemoteSource,
    //var localSource: LocalSource,
    var context: Context
) : RepositoryInterface {

    companion object{
        private var instance: Repository? = null
        fun getInstance(remoteSource: RemoteSource,
                        context: Context
        ): Repository{
            return instance?: Repository(
                remoteSource,context)
        }
    }

    override suspend fun getMyProducts(): Response<ProductItem> = remoteSource.getMyProducts()
    override suspend fun getAllProducts(): Response<ProductItem> = remoteSource.getAllProducts()

    override suspend fun storeProduct(product: ProductPost): Response<ProductItem>{
        return  remoteSource.storeProduct(product)
    }


    override suspend fun getProductsByCategory(productCategory: Long) :  Response<ProductItem> {
        return remoteSource.getProductsByCategory(productCategory)
    }

    override suspend fun updateProduct(productPost: ProductPost) {
        remoteSource.updateProduct(productPost)
    }

    override suspend fun postCustomer(customer: Customer) {
        remoteSource.postCustomer(customer)
    }

    override suspend fun deleteProduct(product: Product) {
        remoteSource.deleteProduct(product)
    }


}