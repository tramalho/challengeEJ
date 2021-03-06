package br.com.tramalho.enjoeitest.presentation.adapters

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.tramalho.enjoeitest.R
import br.com.tramalho.enjoeitest.data.model.Product
import br.com.tramalho.enjoeitest.databinding.ProductItemBinding
import br.com.tramalho.enjoeitest.presentation.viewmodel.ProductItemViewModel

private const val BANNER: Int = 0
private const val ITEM: Int = 1

class ProductsAdapter(val itens: ArrayList<Product>) : RecyclerView.Adapter<ProductsAdapter.BaseViewHolder>(),
        CustomAdapter<Product> {

    private var hasHeader: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val viewHolder : BaseViewHolder = when (viewType) {
            ITEM -> createProductViewHolder(parent)
            else -> createBannerViewHolder(parent)
        }

        return viewHolder
    }

    private fun createBannerViewHolder(parent: ViewGroup): BannerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.banner_item, parent, false)

        return BannerViewHolder(view)
    }

    private fun createProductViewHolder(parent: ViewGroup): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemBinding = DataBindingUtil.inflate<ProductItemBinding>(layoutInflater,
                R.layout.product_item, parent, false);

        return ProductViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return itens.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(itens[position])
    }

    override fun updateItens(list: ObservableArrayList<Product>) {
        this.itens.addAll(list as Collection<Product>)

        if (itens.isNotEmpty() && !hasHeader) {
            val copy = itens[0].copy()
            itens.add(0, copy)
            hasHeader = true
        }

        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) BANNER else ITEM
    }

    inner abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(product: Product)
    }

    inner open class ProductViewHolder(var binding: ProductItemBinding) : BaseViewHolder(binding.root) {

        override fun bind(product: Product) {
            val viewModel = ProductItemViewModel()
            binding.viewModel = viewModel
            binding.executePendingBindings();
            viewModel.bind(product)
        }
    }


    inner private class BannerViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(product: Product) {

        }
    }
}
