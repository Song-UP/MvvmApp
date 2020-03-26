package com.example.mvvmapp.ui.recycleview.node.been

import androidx.databinding.ObservableField
import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode

/**
 * BaseExpandNode: 需要展开，折叠的；需要继承BaseExpandNode，默认展开
 */
class FirstNodeBeen(override val childNode: MutableList<BaseNode>?) : BaseExpandNode() {
    companion object{
        val VIEW_TYPE = 0
    }
    val contentOb = ObservableField<String>()

    fun setContentOb(conentText:String):FirstNodeBeen{
        contentOb.set(conentText)
        return this
    }
}
