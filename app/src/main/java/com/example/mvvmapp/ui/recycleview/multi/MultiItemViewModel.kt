package com.example.mvvmapp.ui.recycleview.multi

import androidx.databinding.ObservableField

class MultiItemViewModel(var type:Int){

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




}
