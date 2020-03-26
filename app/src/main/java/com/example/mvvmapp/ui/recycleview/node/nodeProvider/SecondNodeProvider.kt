package com.example.mvvmapp.ui.recycleview.node.nodeProvider

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityItemMulNodeCenterBinding
import com.example.mvvmapp.ui.recycleview.node.been.SecondNodeBeen

class SecondNodeProvider() : BaseNodeProvider(){

    override val itemViewType: Int
        get() = SecondNodeBeen.VIEW_TYPE
    override val layoutId: Int
        get() = R.layout.activity_item_mul_node_center

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val viewHolder = super.onCreateViewHolder(parent, viewType)
        DataBindingUtil.bind<ActivityItemMulNodeCenterBinding>(viewHolder.itemView)
        return viewHolder
    }

    override fun convert(helper: BaseViewHolder, data: BaseNode) {
        //数据类型自己转换
        data as SecondNodeBeen
        helper.getBinding<ActivityItemMulNodeCenterBinding>()?.childItem = data
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        getAdapter()?.expandOrCollapse(position)

    }

}