package br.com.tramalho.enjoeitest.interactor

import br.com.tramalho.enjoeitest.data.ProductRepository
import br.com.tramalho.enjoeitest.data.model.ProductResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class ProductsUseCase(val productRepository: ProductRepository) {

    interface UseCaseContract<T> {
        fun onSuccess(result: ProductResponse?)
        fun onError(t: Throwable)
    }

    fun loadFromPage(page: Int, contract: UseCaseContract<ProductResponse>) {

        launch(UI) {
            try {

                val request = productRepository.retrieveByPage(page)
                val response = request.await()
                contract.onSuccess(response)

            } catch (e: Exception) {
                contract.onError(e)
            }
        }
    }
}
