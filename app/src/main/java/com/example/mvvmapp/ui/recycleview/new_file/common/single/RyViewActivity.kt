package com.example.mvvmapp.ui.recycleview.new_file.common.single

import android.os.Bundle
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityRyviewNewBinding
import com.example.mvvmapp.ui.NullBaseViewModel
import me.goldze.mvvmhabit.base.BaseActivity
import me.tatarka.bindingcollectionadapter2.BR

class RyViewActivity : BaseActivity<ActivityRyviewNewBinding, NullBaseViewModel>() {
    val arrayListOb = mutableListOf<RyViewItemViewModel>();
    val adapter = SingDataBindingAdapter()
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_ryview_new
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        binding.recycleview.apply {
            adapter = this@RyViewActivity.adapter
            layoutManager = LinearLayoutManager(this@RyViewActivity)

        }

        for (number in 1..20){
            arrayListOb.add(RyViewItemViewModel("初始值：${number}"));
        }
        adapter.setNewData(arrayListOb)

    }


}