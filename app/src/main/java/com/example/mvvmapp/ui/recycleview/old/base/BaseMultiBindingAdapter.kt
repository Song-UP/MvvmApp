//package com.example.mvvmapp.ui.recycleview.base
//
//import android.view.View
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.databinding.ViewDataBinding
//import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
//import com.chad.library.adapter.base.entity.MultiItemEntity
//import com.example.mvvmapp.R
//
//abstract class BaseMultiBindingAdapter<T : MultiItemEntity>(dataList:List<T>):BaseMultiItemQuickAdapter<T,BindingViewHolder>(dataList){
//
//
//    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View? {
//        val binding = DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutResId, parent, false)
//            ?: return super.getItemView(layoutResId, parent)
//        val view = binding.root
//        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
//        return view
//    }
//
//}
