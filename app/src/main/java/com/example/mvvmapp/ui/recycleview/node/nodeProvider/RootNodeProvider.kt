package com.example.mvvmapp.ui.recycleview.node.nodeProvider

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityItemMulNodeTitleBinding
import com.example.mvvmapp.ui.recycleview.node.been.FirstNodeBeen

class RootNodeProvider() : BaseNodeProvider(){
    override val itemViewType: Int
        get() = FirstNodeBeen.VIEW_TYPE
    override val layoutId: Int
        get() = R.layout.activity_item_mul_node_title

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val viewHolder = super.onCreateViewHolder(parent, viewType)
        DataBindingUtil.bind<ActivityItemMulNodeTitleBinding>(viewHolder.itemView)
        return viewHolder
    }

    override fun convert(helper: BaseViewHolder, data: BaseNode) {
        //数据类型自己转换
        data as FirstNodeBeen
        helper.getBinding<ActivityItemMulNodeTitleBinding>()?.childItem = data
    }

    /**
     * 不设置 adapter.setOnItemClicklistner() 这个方法将会被调用
     */
    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        getAdapter()?.expandOrCollapse(position)

    }

}