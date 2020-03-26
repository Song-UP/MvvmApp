package com.example.mvvmapp.ui.recycleview.new_file.common.section

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.entity.SectionEntity
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityItemMulSectionCenterBinding
import com.example.mvvmapp.databinding.ActivityItemMulSectionTitleBinding
import com.example.mvvmapp.databinding.ActivityItemMulTitleBinding
import com.example.mvvmapp.ui.recycleview.been.MultiItemViewModel

/**
 * 头部显示的内容
 * 它本身是继承 BaseMultiItemQuickAdapter 因此之前的方法都还能用
 *
 */
class SectionQuickAdapter :BaseSectionQuickAdapter<SectionBeen,BaseViewHolder>(R.layout.activity_item_mul_section_title){

    init {
        setNormalLayout(R.layout.activity_item_mul_section_center)
        //注册需要点击的子view
        //        addChildClickViewIds()

        //1.还用一种写法
        //addItemType(SectionEntity.HEADER_TYPE, R.layout.activity_item_mul_section_title)
        //

    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        when(viewType){
            SectionEntity.HEADER_TYPE -> DataBindingUtil.bind<ActivityItemMulSectionTitleBinding>(viewHolder.itemView)
            SectionEntity.NORMAL_TYPE -> DataBindingUtil.bind<ActivityItemMulSectionCenterBinding>(viewHolder.itemView)
            else -> DataBindingUtil.bind<ActivityItemMulSectionCenterBinding>(viewHolder.itemView)
        }


    }

    override fun convert(helper: BaseViewHolder, item: SectionBeen) {
        helper.getBinding<ActivityItemMulSectionCenterBinding>()?.childItem = item
    }

    override fun convertHeader(helper: BaseViewHolder, item: SectionBeen) {
        helper.getBinding<ActivityItemMulSectionTitleBinding>()?.childItem = item

    }

}