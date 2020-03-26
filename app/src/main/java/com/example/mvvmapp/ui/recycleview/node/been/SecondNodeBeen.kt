package com.example.mvvmapp.ui.recycleview.node.been

import android.database.Observable
import androidx.databinding.ObservableField
import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode

class SecondNodeBeen(override val childNode: MutableList<BaseNode>?) : BaseExpandNode() {
    companion object{
        val VIEW_TYPE = 1
    }
    val contentOb = ObservableField<String>()

    fun setContentOb(conentText:String):SecondNodeBeen{
        contentOb.set(conentText)
        return this
    }
}
