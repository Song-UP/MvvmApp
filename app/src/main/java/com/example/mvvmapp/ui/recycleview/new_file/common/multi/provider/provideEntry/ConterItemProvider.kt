package com.example.mvvmapp.ui.recycleview.new_file.common.multi.provider.provideEntry

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityItemMulCenterBinding
import com.example.mvvmapp.ui.recycleview.been.MultiItemViewModel

class ConterItemProvider() : BaseItemProvider<MultiItemViewModel>() {
    override val itemViewType: Int
        get() = MultiItemViewModel.TYPE_CENTER
    override val layoutId: Int
        get() = R.layout.activity_item_mul_center

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val viewHolder = super.onCreateViewHolder(parent, viewType);
        DataBindingUtil.bind<ActivityItemMulCenterBinding>(viewHolder.itemView)
        return viewHolder
    }

    override fun convert(helper: BaseViewHolder, data: MultiItemViewModel) {
        helper.getBinding<ActivityItemMulCenterBinding>()?.apply {
            childItem = data
            executePendingBindings()
        }

    }

}
