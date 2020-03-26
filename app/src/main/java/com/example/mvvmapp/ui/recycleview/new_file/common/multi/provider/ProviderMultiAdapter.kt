package com.example.mvvmapp.ui.recycleview.new_file.common.multi.provider

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.example.mvvmapp.ui.recycleview.been.MultiItemViewModel
import com.example.mvvmapp.ui.recycleview.new_file.common.multi.provider.provideEntry.ConterItemProvider
import com.example.mvvmapp.ui.recycleview.new_file.common.multi.provider.provideEntry.RightItemProvider
import com.example.mvvmapp.ui.recycleview.new_file.common.multi.provider.provideEntry.TitleItemProvider

/**
 * 不同类型的item，分散到不同的provicer里面(可以用来取代阿里的vlayout)
 */

class ProviderMultiAdapter : BaseProviderMultiAdapter<MultiItemViewModel>(){

    init {
        addItemProvider(TitleItemProvider())
        addItemProvider(ConterItemProvider())
        addItemProvider(RightItemProvider())
    }

    override fun getItemType(data: List<MultiItemViewModel>, position: Int): Int {
        /**
         * 自行根据数据、位置等内容，返回 item 类型
         */
        return data.get(position).itemType
    }

}
