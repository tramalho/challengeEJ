package br.com.tramalho.enjoeitest.presentation

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import br.com.tramalho.enjoeitest.R
import br.com.tramalho.enjoeitest.data.model.Product
import br.com.tramalho.enjoeitest.databinding.ActivityDetailProductBinding
import br.com.tramalho.enjoeitest.presentation.adapters.ImgProductAdapter
import br.com.tramalho.enjoeitest.presentation.viewmodel.ProductDetailViewModel
import kotlinx.android.synthetic.main.activity_detail_product.*


class DetailProductActivity : AppCompatActivity() {

    companion object {
        const val PRODUCT_EXTRA: String = "PRODUCT_EXTRA"
    }

    private lateinit var viewDataBinding: ActivityDetailProductBinding

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = setContentView(this, R.layout.activity_detail_product)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent?.apply {
            product = getParcelableExtra(PRODUCT_EXTRA)
        }

        configRecyclerView()
        setupViewModel()
    }

    private fun configRecyclerView() {

        viewDataBinding.carouselImgs.adapter = ImgProductAdapter(product.photos)

        viewDataBinding.carouselImgs.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupViewModel() {

        val viewModel = ViewModelProviders.of(this)
                .get(ProductDetailViewModel::class.java)

        viewDataBinding.viewModel = viewModel

        viewModel.bind(product)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (android.R.id.home == item?.itemId) {
            NavUtils.navigateUpFromSameTask(this)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewDataBinding.unbind()
    }
}
