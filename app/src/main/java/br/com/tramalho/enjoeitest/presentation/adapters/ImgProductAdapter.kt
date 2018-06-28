package br.com.tramalho.enjoeitest.presentation.adapters

import android.databinding.DataBindingUtil.inflate
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.tramalho.enjoeitest.R
import br.com.tramalho.enjoeitest.data.model.Photo
import br.com.tramalho.enjoeitest.databinding.DetailImgItemBinding
import br.com.tramalho.enjoeitest.presentation.viewmodel.ImgProductViewModel

class ImgProductAdapter(val photos: List<Photo>) : RecyclerView.Adapter<ImgProductAdapter.PhotosViewHolder>() {

    private lateinit var itemBinding: DetailImgItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        itemBinding = inflate(layoutInflater,
                R.layout.detail_img_item, parent, false);

        return PhotosViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holderPhotos: PhotosViewHolder, position: Int) {
        holderPhotos.bind(photos[position])
    }

    inner class PhotosViewHolder(val binding: DetailImgItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            val viewModel = ImgProductViewModel()
            binding.viewModel = viewModel
            binding.executePendingBindings();
            viewModel.bind(photo)
        }
    }
}