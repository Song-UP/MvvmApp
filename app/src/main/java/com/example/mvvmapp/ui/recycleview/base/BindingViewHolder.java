package com.example.mvvmapp.ui.recycleview.base;

import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mvvmapp.R;

public class BindingViewHolder extends BaseViewHolder {

    public BindingViewHolder(View view) {
        super(view);
    }

    public ViewDataBinding getBinding() {
        return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
    }
}