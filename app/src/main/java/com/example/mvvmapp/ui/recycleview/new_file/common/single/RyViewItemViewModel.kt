package com.example.mvvmapp.ui.recycleview.new_file.common.single

import androidx.databinding.ObservableField

class RyViewItemViewModel(string: String){
    val nameOb = ObservableField<String>("初始值");

    init {
        nameOb.set(string)

    }
}
