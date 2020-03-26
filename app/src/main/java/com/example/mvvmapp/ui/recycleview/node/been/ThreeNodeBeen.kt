package com.example.mvvmapp.ui.recycleview.node.been

import android.database.Observable
import androidx.databinding.ObservableField
import com.chad.library.adapter.base.entity.node.BaseNode

class ThreeNodeBeen(override val childNode: MutableList<BaseNode>? = null) : BaseNode() {
    companion object{
        val VIEW_TYPE = 2
    }
    val contentOb = ObservableField<String>()

    fun setContentOb(conentText:String):ThreeNodeBeen{
        contentOb.set(conentText)
        return this
    }
}
