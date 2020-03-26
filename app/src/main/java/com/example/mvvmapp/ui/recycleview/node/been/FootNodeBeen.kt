package com.example.mvvmapp.ui.recycleview.node.been

import android.database.Observable
import androidx.databinding.ObservableField
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.entity.node.NodeFooterImp
import com.example.mvvmapp.ui.recycleview.node.nodeProvider.RootNodeProvider

class FootNodeBeen(override val childNode: MutableList<BaseNode>? = null,
                   override val footerNode:BaseNode = FirstNodeBeen(null).setContentOb("显示更多...")
) : BaseNode(), NodeFooterImp {

    /**
     * {@link BaseNode}
     * 重写此方法，获取子节点。如果没有子节点，返回 null 或者 空数组
     * @return child nodes
     */

    companion object{
        val VIEW_TYPE = 4
    }
    val contentOb = ObservableField<String>()
}
