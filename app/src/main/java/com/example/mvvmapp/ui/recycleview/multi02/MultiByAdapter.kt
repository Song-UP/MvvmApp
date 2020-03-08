package com.example.mvvmapp.ui.recycleview.multi02

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.mvvmapp.BR
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.recycleview.base.BaseMultiBindingAdapter
import com.example.mvvmapp.ui.recycleview.base.BindingViewHolder
import com.example.mvvmapp.ui.recycleview.multi.MultiItemViewModel

class MultiByAdapter(data:List<MultiItemEntity>): BaseMultiBindingAdapter<MultiItemEntity>(data){


    init {
        addItemType(MultiItemViewModel.TYPE_TITLE, R.layout.activity_item_mul_title)
        addItemType(MultiItemViewModel.TYPE_CENTER, R.layout.activity_item_mul_center)
        addItemType(MultiItemViewModel.TYPE_RIGHT, R.layout.activity_item_mul_right)
    }
    override fun convert(helper: BindingViewHolder?, item: MultiItemEntity?) {
        when(helper?.itemViewType){
            MultiItemViewModel.TYPE_TITLE-> {
                item as MultiItemViewModel
                helper?.binding?.apply {
                    this.setVariable(BR.childItem,item)
                    this.executePendingBindings()
                }
            }

            MultiItemViewModel.TYPE_CENTER ->{
                item as MultiItemViewModel
                helper?.binding?.apply {
                    setVariable(BR.childItem,item)
                    executePendingBindings()
                }

            }

            MultiItemViewModel.TYPE_RIGHT->{
                item as MultiItemViewModel
                helper?.binding?.apply {
                    setVariable(BR.childItem,item)
                    executePendingBindings()
                }

            }

            else ->{
                item as MultiItemViewModel
                helper?.binding?.apply {
                    setVariable(BR.childItem,item)
                    executePendingBindings()
                }
            }

        }

    }
}
