package br.com.tramalho.enjoeitest.presentation

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.tramalho.enjoeitest.R
import br.com.tramalho.enjoeitest.databinding.FragmentHomeBinding
import br.com.tramalho.enjoeitest.presentation.adapters.ProductsAdapter
import br.com.tramalho.enjoeitest.presentation.viewmodel.HomeViewModel
import java.util.*

private const val COLUMN: Int = 2

class HomeFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewDataBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                        container, true)

        return viewDataBinding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecyclerView()

        setupViewModel()
    }

    private fun configRecyclerView() {
        viewDataBinding.rvListItens.adapter = ProductsAdapter(ArrayList())

        val gridLayoutManager = GridLayoutManager(context, COLUMN)

        gridLayoutManager.spanSizeLookup = SpanSizeLookup()

        viewDataBinding.rvListItens.layoutManager = gridLayoutManager
    }

    private fun setupViewModel() {
        val viewModel = ViewModelProviders.of(activity!!).get(HomeViewModel::class.java)

        viewDataBinding.viewModel = viewModel

        viewModel.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewDataBinding.unbind()
    }

    private inner class SpanSizeLookup : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (position) {
                0 -> 2
                else -> 1
            }
        }
    }

}
