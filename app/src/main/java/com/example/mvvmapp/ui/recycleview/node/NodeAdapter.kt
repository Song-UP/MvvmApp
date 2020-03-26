package com.example.mvvmapp.ui.recycleview.node

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.example.mvvmapp.ui.recycleview.node.been.FirstNodeBeen
import com.example.mvvmapp.ui.recycleview.node.been.FootNodeBeen
import com.example.mvvmapp.ui.recycleview.node.been.SecondNodeBeen
import com.example.mvvmapp.ui.recycleview.node.been.ThreeNodeBeen
import com.example.mvvmapp.ui.recycleview.node.nodeProvider.FootNodeProvider
import com.example.mvvmapp.ui.recycleview.node.nodeProvider.RootNodeProvider
import com.example.mvvmapp.ui.recycleview.node.nodeProvider.SecondNodeProvider
import com.example.mvvmapp.ui.recycleview.node.nodeProvider.ThreeNodeProvider

/**
 * 具有展开和收起的功能
 */
class NodeAdapter: BaseNodeAdapter(null){

    init {
        //注册provider的方式，有三种

        //1.需要沾满一行的
        addFullSpanNodeProvider(RootNodeProvider())
        //普通的item provider（二级）
        addNodeProvider(SecondNodeProvider())
        //普通的item provider (三级)
        addNodeProvider(ThreeNodeProvider())
//        脚布局的 provider
        addFooterNodeProvider(FootNodeProvider())
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        return when(data.get(position)){
            is FirstNodeBeen -> FirstNodeBeen.VIEW_TYPE
            is SecondNodeBeen -> SecondNodeBeen.VIEW_TYPE
            is ThreeNodeBeen -> ThreeNodeBeen.VIEW_TYPE
            is FootNodeBeen -> FootNodeBeen.VIEW_TYPE
            else -> FirstNodeBeen.VIEW_TYPE
        }
    }

}
