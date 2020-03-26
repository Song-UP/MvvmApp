package com.example.mvvmapp.ui.recycleview.new_file.common.multi

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmapp.BR
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityRyviewNewBinding
import com.example.mvvmapp.ui.NullBaseViewModel
import com.example.mvvmapp.ui.recycleview.been.MultiItemViewModel
import com.example.mvvmapp.ui.recycleview.new_file.common.multi.itemType.MultipleItemQuickAdapter
import com.example.mvvmapp.ui.recycleview.new_file.common.multi.provider.ProviderMultiAdapter
import me.goldze.mvvmhabit.base.BaseActivity

class MultiActivity : BaseActivity<ActivityRyviewNewBinding, NullBaseViewModel>() {

    val dataList = ArrayList<MultiItemViewModel>()
    val adapter = MultipleItemQuickAdapter()

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_ryview_new
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        binding.recycleview.apply {
            layoutManager = LinearLayoutManager(this@MultiActivity)
            adapter = this@MultiActivity.adapter
            setHasFixedSize(true)
        }

        for (number1 in 1..20){
            dataList.add(MultiItemViewModel(MultiItemViewModel.TYPE_TITLE).setTitle("标题：${number1}")
                .setContent("居中内容：${number1}")
                .setRightText("居中内容：${number1}"))
            dataList.add(MultiItemViewModel(MultiItemViewModel.TYPE_CENTER).setContent("居中内容：${number1}"))
            for (number in 1..5){
                dataList.add(MultiItemViewModel(MultiItemViewModel.TYPE_RIGHT).setRightText("居右内容：${number}"))
            }
        }

        adapter.setNewData(dataList)

//        binding.recycleview?.adapter?.notifyDataSetChanged();

    }

}