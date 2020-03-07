package com.example.mvvmapp.ui.recycleview.single

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.example.mvvmapp.R
import me.goldze.mvvmhabit.base.BaseModel

import me.goldze.mvvmhabit.base.BaseViewModel
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding

class RyViewModel(application: Application) : BaseViewModel<BaseModel>(application){
    val itemBinding1 = ItemBinding.of<RyViewItemViewModel>(BR.viewModel,R.layout.activity_item_ryview)
    val arrayListOb = ObservableArrayList<RyViewItemViewModel>();

    fun initData(){
        for (number in 1..20){
            arrayListOb.add(RyViewItemViewModel("初始值：${number}"));
        }
    }
}
