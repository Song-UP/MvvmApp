package com.example.mvvmapp

import android.app.Application
import android.view.View
import android.widget.TextView
import androidx.databinding.ObservableField
import com.example.mvvmapp.ui.recycleview.new_file.common.multi.MultiActivity
import com.example.mvvmapp.ui.recycleview.new_file.common.section.SectionActivity
import com.example.mvvmapp.ui.recycleview.new_file.common.single.RyViewActivity
import com.example.mvvmapp.ui.recycleview.node.NodeRyviewActivity
import com.example.mvvmapp.ui.viewPager.ViewPageActivity
import me.goldze.mvvmhabit.base.BaseModel

import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand
import me.goldze.mvvmhabit.utils.ToastUtils

class MainViewModel(application: Application) : BaseViewModel<BaseModel>(application){
    //修改内容
    val textObservable = ObservableField<String>("这里面的内容是可变的")

    //点击时间
    val click1 = View.OnClickListener {
        it as TextView
        ToastUtils.showShort("点击了${it.text}")
    }

    //防止1s中重复点击
    val click2 = BindingCommand<View>(BindingAction {
        startActivity(RyViewActivity::class.java)
    })

    //点击-调用方法
    fun onClick3(it: View){
//        startActivity(MultiRyView02Activity::class.java)
//        startActivity(RyViewActivity::class.java)
        startActivity(MultiActivity::class.java)
    }


    val click4 = BindingCommand<View>(BindingAction { startActivity(ViewPageActivity::class.java) })


    val click5 = BindingCommand<View>(BindingAction {
        startActivity(SectionActivity::class.java)
    })

    val click6 = BindingCommand<View>(BindingAction {
        startActivity(NodeRyviewActivity::class.java)
    })

}
