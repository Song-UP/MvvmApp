package com.example.mvvmapp.ui.recycleview.new_file.common.section

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

class SectionActivity : BaseActivity<ActivityRyviewNewBinding, NullBaseViewModel>() {

    val dataList = ArrayList<SectionBeen>()
    val adapter = SectionQuickAdapter()

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_ryview_new
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        binding.recycleview.apply {
            layoutManager = LinearLayoutManager(this@SectionActivity)
            adapter = this@SectionActivity.adapter
            setHasFixedSize(true)
        }

        for (number1 in 1..20){
            dataList.add(
                SectionBeen(true,"标题：${number1}"));
            for (number in 1..5){
                dataList.add(SectionBeen(false,"居右内容：${number}"))
            }
        }
        adapter.setNewData(dataList)

//        binding.recycleview?.adapter?.notifyDataSetChanged();

    }

}