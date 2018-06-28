package br.com.tramalho.enjoeitest.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableInt
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import br.com.tramalho.enjoeitest.data.ProductRepository
import br.com.tramalho.enjoeitest.data.model.Product
import br.com.tramalho.enjoeitest.data.model.ProductResponse
import br.com.tramalho.enjoeitest.di.NetworkModule
import br.com.tramalho.enjoeitest.interactor.ProductsUseCase

class HomeViewModel() : ViewModel() {

    private enum class States {
        LOADING, LOADING_MORE, LOADING_FINISH, ERROR
    }

    val initLoadVisibility: ObservableInt = ObservableInt(GONE)
    val loadinMoreVisibility: ObservableInt = ObservableInt(GONE)
    val rvVisibility: ObservableInt = ObservableInt(GONE)
    val listProducts: ObservableArrayList<Product> = ObservableArrayList()
    val errorPageVisibility: ObservableInt = ObservableInt(VISIBLE)

    private var actualPage = 1

    fun start() {
        actualPage = 1
        configVisibility(States.LOADING)
        ProductsUseCase(getRepository()).loadFromPage(actualPage, Callback())
    }

    private fun getRepository(): ProductRepository {

        val networkModule = NetworkModule()

        val service = with(networkModule) {
            serviceAPI(retrofit(logInterceptor()))
        }

        return ProductRepository(service)
    }

    private fun configVisibility(state: States) {

        val stateResult = when (state) {
            States.LOADING -> StateResult(VISIBLE, GONE, GONE)
            States.LOADING_MORE -> StateResult(GONE, VISIBLE, VISIBLE)
            States.LOADING_FINISH -> StateResult(GONE, GONE, VISIBLE)
            States.ERROR -> StateResult(GONE, GONE, GONE, VISIBLE)
        }

        initLoadVisibility.set(stateResult.load)
        loadinMoreVisibility.set(stateResult.loadMore)
        rvVisibility.set(stateResult.recyclerView)
        errorPageVisibility.set(stateResult.errorPage)
    }

    private data class StateResult(val load: Int, val loadMore: Int, val recyclerView: Int,
                                   val errorPage: Int = GONE)

    private inner class Callback : ProductsUseCase.UseCaseContract<ProductResponse> {

        override fun onSuccess(result: ProductResponse?) {
            actualPage++

            result?.products?.let {
                listProducts.addAll(it)
            }

            configVisibility(States.LOADING_FINISH)
        }

        override fun onError(t: Throwable) {
            configVisibility(States.ERROR)
        }
    }

    fun onClickRefresh(view: View) {
        start()
    }
}
