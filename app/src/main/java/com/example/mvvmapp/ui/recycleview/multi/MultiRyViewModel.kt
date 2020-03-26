package com.example.mvvmapp.ui.recycleview.multi

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.recycleview.been.MultiItemViewModel
import me.goldze.mvvmhabit.base.BaseModel

import me.goldze.mvvmhabit.base.BaseViewModel
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

class MultiRyViewModel(application: Application) : BaseViewModel<BaseModel>(application){


    val itemBinding1 = ItemBinding.of<MultiItemViewModel>(OnItemBind { itemBinding, position, item ->
        when(item.itemType){
            MultiItemViewModel.TYPE_TITLE -> itemBinding.set(BR.viewModel, R.layout.activity_item_mul_title)
            MultiItemViewModel.TYPE_CENTER -> itemBinding.set(BR.viewModel,R.layout.activity_item_mul_center)
            MultiItemViewModel.TYPE_RIGHT -> itemBinding.set(BR.viewModel,R.layout.activity_item_mul_right)
            else -> itemBinding.set(BR.viewModel, R.layout.activity_item_mul_title)
        }
    })
    //给Recycleview添加ObservableList
    val arrayListOb = ObservableArrayList<MultiItemViewModel>();

    fun initData(){
        for (number1 in 1..5){
            arrayListOb.add(MultiItemViewModel(MultiItemViewModel.TYPE_TITLE).setTitle("标题：${number1}"))
            arrayListOb.add(MultiItemViewModel(MultiItemViewModel.TYPE_CENTER).setContent("居中内容：${number1}"))
            for (number in 1..5){
                arrayListOb.add(MultiItemViewModel(MultiItemViewModel.TYPE_RIGHT).setRightText("居右内容：${number}"))
            }
        }

    }
}
