package com.example.mvvmapp.ui.recycleview.multi

import android.os.Bundle
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityMultiRyviewBinding
import me.goldze.mvvmhabit.base.BaseActivity
import me.tatarka.bindingcollectionadapter2.BR

class MultiRyViewActivity:BaseActivity<ActivityMultiRyviewBinding,MultiRyViewModel>(){
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_multi_ryview
    }

    override fun initVariableId(): Int {
        return com.example.mvvmapp.BR.viewModel
    }

    override fun initData() {
        super.initData()
        viewModel.initData()
    }

}
