package com.example.mvvmapp.ui.recycleview.multi

import androidx.databinding.ObservableField
import com.chad.library.adapter.base.entity.MultiItemEntity

class MultiItemViewModel(var type:Int): MultiItemEntity {

    companion object{
        const val TYPE_TITLE = 0
        const val TYPE_CENTER = 1
        const val TYPE_RIGHT = 2
    }

    val titleOb = ObservableField<String>()
    val contentOb = ObservableField<String>()
    val rightOb = ObservableField<String>()


    fun setTitle(title:String):MultiItemViewModel{
        titleOb.set(title)
        return this
    }

    fun setContent(content:String):MultiItemViewModel{
        contentOb.set(content)
        return this
    }

    fun setRightText(rightText:String):MultiItemViewModel{
        rightOb.set(rightText)
        return this
    }

    override fun getItemType(): Int {
        return type
    }




}
