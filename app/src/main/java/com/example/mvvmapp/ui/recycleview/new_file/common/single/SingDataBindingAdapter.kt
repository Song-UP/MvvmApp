package com.example.mvvmapp.ui.recycleview.new_file.common.single

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityItemRyviewNewBinding

class SingDataBindingAdapter : BaseQuickAdapter<RyViewItemViewModel, BaseViewHolder>(R.layout.activity_item_ryview_new){

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ActivityItemRyviewNewBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: RyViewItemViewModel) {
        val binding = helper.getBinding<ActivityItemRyviewNewBinding>()?.apply {
            viewModel = item
            executePendingBindings()
        }
    }

}