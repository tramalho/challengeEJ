package br.com.tramalho.enjoeitest.presentation

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import br.com.tramalho.enjoeitest.R
import br.com.tramalho.enjoeitest.data.model.Product
import br.com.tramalho.enjoeitest.databinding.ActivityDetailProductBinding
import kotlinx.android.synthetic.main.activity_detail_product.*


class DetailProductActivity : AppCompatActivity() {

    companion object {
        const val PRODUCT_EXTRA : String = "PRODUCT_EXTRA"
    }

    private lateinit var viewDataBinding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = setContentView<ActivityDetailProductBinding>(this, R.layout.activity_detail_product)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViewModel()
    }

    private fun setupViewModel() {

        val viewModel = ViewModelProviders.of(this)
                .get(ProductDetailViewModel::class.java)

         intent?.apply {
            val product = getParcelableExtra<Product>(PRODUCT_EXTRA)

             viewDataBinding.viewModel = viewModel

            viewModel.bind(product)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewDataBinding.unbind()
    }
}
