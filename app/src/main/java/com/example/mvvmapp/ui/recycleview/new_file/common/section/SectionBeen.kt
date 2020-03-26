package com.example.mvvmapp.ui.recycleview.new_file.common.section

import androidx.databinding.ObservableField
import com.chad.library.adapter.base.entity.SectionEntity

class SectionBeen(override val isHeader: Boolean, contentText:String) : SectionEntity{
    val contentOb = ObservableField<String>()
    init {
        contentOb.set(contentText)
    }
}
