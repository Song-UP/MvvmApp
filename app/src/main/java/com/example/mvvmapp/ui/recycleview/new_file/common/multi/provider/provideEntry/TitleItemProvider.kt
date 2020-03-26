package com.example.mvvmapp.ui.recycleview.new_file.common.multi.provider.provideEntry

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityItemMulTitleBinding
import com.example.mvvmapp.ui.recycleview.been.MultiItemViewModel

class TitleItemProvider() : BaseItemProvider<MultiItemViewModel>() {
    override val itemViewType: Int
        get() = MultiItemViewModel.TYPE_TITLE
    override val layoutId: Int
        get() = R.layout.activity_item_mul_title

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val viewHolder = super.onCreateViewHolder(parent, viewType);
        DataBindingUtil.bind<ActivityItemMulTitleBinding>(viewHolder.itemView)
        return viewHolder
    }

    override fun convert(helper: BaseViewHolder, data: MultiItemViewModel) {
        helper.getBinding<ActivityItemMulTitleBinding>()?.apply {
            childItem = data
            executePendingBindings()
        }

    }

}
