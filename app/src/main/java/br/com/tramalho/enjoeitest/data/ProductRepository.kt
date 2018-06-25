package br.com.tramalho.enjoeitest.data

import br.com.tramalho.enjoeitest.data.model.ProductResponse
import br.com.tramalho.enjoeitest.infraestructure.ServiceApi
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Call

class ProductRepository(val serviceApi: ServiceApi) {

    fun retrieveByPage(page: Int): Deferred<ProductResponse> {
        return serviceApi.retrieveProductsByPage(page)
    }
}
