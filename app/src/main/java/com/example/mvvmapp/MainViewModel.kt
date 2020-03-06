package com.example.mvvmapp

import android.app.Application
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.ObservableField
import me.goldze.mvvmhabit.base.BaseModel

import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand
import me.goldze.mvvmhabit.utils.ToastUtils
import org.w3c.dom.Text

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
        ToastUtils.showShort("点击了:click2")
    })

    //点击-调用方法
    fun onClick3(it: View){
        it as TextView
        ToastUtils.showShort("点击了${it.text}")
    }

}
