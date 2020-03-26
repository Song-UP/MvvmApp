package com.example.mvvmapp.ui.recycleview.node.nodeProvider

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityItemMulNodeCenterBinding
import com.example.mvvmapp.databinding.ActivityItemMulNodeFootBinding
import com.example.mvvmapp.databinding.ActivityItemMulNodeRightBinding
import com.example.mvvmapp.databinding.ActivityItemMulNodeTitleBinding
import com.example.mvvmapp.ui.recycleview.node.been.FirstNodeBeen
import com.example.mvvmapp.ui.recycleview.node.been.FootNodeBeen
import com.example.mvvmapp.ui.recycleview.node.been.ThreeNodeBeen

class FootNodeProvider() : BaseNodeProvider(){
    override val itemViewType: Int
        get() = FootNodeBeen.VIEW_TYPE
    override val layoutId: Int
        get() = R.layout.activity_item_mul_node_foot

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val viewHolder = super.onCreateViewHolder(parent, viewType)
        DataBindingUtil.bind<ActivityItemMulNodeFootBinding>(viewHolder.itemView)
        return viewHolder
    }

    override fun convert(helper: BaseViewHolder, data: BaseNode) {
        //数据类型自己转换
        data as FootNodeBeen
        helper.getBinding<ActivityItemMulNodeFootBinding>()?.childItem = data
    }

    /**
     * 当setOnItemClickList()不为null的时候会被调用
     */
    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        getAdapter()?.expandOrCollapse(position)

    }

    override fun onLongClick(
        helper: BaseViewHolder,
        view: View,
        data: BaseNode,
        position: Int
    ): Boolean {
        return super.onLongClick(helper, view, data, position)
    }

}