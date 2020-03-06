package com.example.mvvmapp.ui.recycleview

import com.example.mvvmapp.R
import com.example.mvvmapp.app.MyApplication
import com.example.mvvmapp.data.UserInfo
import me.goldze.mvvmhabit.base.BaseModel
import me.goldze.mvvmhabit.base.BaseViewModel
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding

class RyViewViewModel (application: MyApplication):BaseViewModel<BaseModel>(application){

    val itemBinging1 = ItemBinding.of(BR.viewModel,R.layout.item_ryview)



}