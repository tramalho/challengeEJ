package br.com.tramalho.enjoeitest.infraestructure

import br.com.tramalho.enjoeitest.data.model.ProductResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("products/home")
    fun retrieveProductsByPage(@Query("page") page: Int) : Deferred<ProductResponse>

}
