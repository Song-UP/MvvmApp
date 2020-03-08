package com.example.mvvmapp.ui.recycleview.multi02

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.mvvmapp.BR
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityRyview02Binding
import com.example.mvvmapp.ui.recycleview.multi.MultiItemViewModel
import me.goldze.mvvmhabit.base.BaseActivity
import me.goldze.mvvmhabit.base.BaseModel
import me.goldze.mvvmhabit.base.BaseViewModel

class MultiRyView02Activity: BaseActivity<ActivityRyview02Binding,MulRyview02ViewModel>(){
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_ryview_02
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    val dataList = ArrayList<MultiItemEntity>()

    override fun initData() {
        super.initData()
        binding.recycleview.layoutManager = LinearLayoutManager(this)
        binding.recycleview.adapter = MultiByAdapter(dataList)

        for (number1 in 1..20){
            dataList.add(MultiItemViewModel(MultiItemViewModel.TYPE_TITLE).setTitle("标题：${number1}")
                .setContent("居中内容：${number1}")
                .setRightText("居中内容：${number1}"))
            dataList.add(MultiItemViewModel(MultiItemViewModel.TYPE_CENTER).setContent("居中内容：${number1}"))
            for (number in 1..5){
                dataList.add(MultiItemViewModel(MultiItemViewModel.TYPE_RIGHT).setRightText("居右内容：${number}"))
            }
        }

        binding.recycleview.setHasFixedSize(true)
        binding.recycleview?.adapter?.notifyDataSetChanged();

    }

}
