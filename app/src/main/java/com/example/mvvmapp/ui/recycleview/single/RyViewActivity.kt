package com.example.mvvmapp.ui.recycleview.single

import android.os.Bundle
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityMainBinding
import me.goldze.mvvmhabit.base.BaseActivity
import me.tatarka.bindingcollectionadapter2.BR

class RyViewActivity : BaseActivity<ActivityMainBinding, RyViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_ryview
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        viewModel.initData()
    }


}