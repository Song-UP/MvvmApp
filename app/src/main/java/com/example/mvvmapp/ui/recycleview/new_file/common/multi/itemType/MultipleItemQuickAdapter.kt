package com.example.mvvmapp.ui.recycleview.new_file.common.multi.itemType

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityItemMulCenterBinding
import com.example.mvvmapp.databinding.ActivityItemMulRightBinding
import com.example.mvvmapp.databinding.ActivityItemMulTitleBinding
import com.example.mvvmapp.ui.recycleview.been.MultiItemViewModel

/**
 * 最普通的多布局写法
 */
class MultipleItemQuickAdapter : BaseMultiItemQuickAdapter<MultiItemViewModel,BaseViewHolder>(){

    init {
        addItemType(MultiItemViewModel.TYPE_TITLE, R.layout.activity_item_mul_title)
        addItemType(MultiItemViewModel.TYPE_CENTER, R.layout.activity_item_mul_center)
        addItemType(MultiItemViewModel.TYPE_RIGHT, R.layout.activity_item_mul_right)
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        when(viewType){
            MultiItemViewModel.TYPE_TITLE  -> DataBindingUtil.bind<ActivityItemMulTitleBinding>(viewHolder.itemView)
            MultiItemViewModel.TYPE_CENTER -> DataBindingUtil.bind<ActivityItemMulCenterBinding>(viewHolder.itemView)
            MultiItemViewModel.TYPE_RIGHT  -> DataBindingUtil.bind<ActivityItemMulRightBinding>(viewHolder.itemView)

        }
    }

    override fun convert(helper: BaseViewHolder, item: MultiItemViewModel) {
        when(item.itemType){
            MultiItemViewModel.TYPE_TITLE  -> helper.getBinding<ActivityItemMulTitleBinding>()?.childItem = item
            MultiItemViewModel.TYPE_CENTER -> helper.getBinding<ActivityItemMulCenterBinding>()?.childItem = item
            MultiItemViewModel.TYPE_RIGHT  -> helper.getBinding<ActivityItemMulRightBinding>()?.childItem = item
        }
    }

}
