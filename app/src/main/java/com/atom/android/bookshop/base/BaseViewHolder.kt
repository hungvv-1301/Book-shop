package com.atom.android.bookshop.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    binding: ViewDataBinding,
    onClickItem: (T) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var item: T? = null;

    init {
        itemView.setOnClickListener {
            item?.let(onClickItem)
        }
    }

    open fun binView(item: T) {
        this.item = item
    }
}
