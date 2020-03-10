package com.example.mvvmapp.ui.viewPager

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityViewpagerBinding
import com.example.mvvmapp.ui.viewPager.base.BaseFragmentPageAdapter
import me.goldze.mvvmhabit.base.BaseActivity
import me.tatarka.bindingcollectionadapter2.BR
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView

class ViewPageActivity:BaseActivity<ActivityViewpagerBinding,ViewPagerViewModel>(){

    val titles = arrayOf("标题1","标题2","标题3123412341234")
    val fragments = mutableListOf<Fragment>()
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_viewpager
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }


    override fun initData() {
        super.initData()

        fragments.add(MyFragment())
        fragments.add(MyFragment())
        fragments.add(MyFragment())


        initMagicIndicator()
    }

    fun initMagicIndicator() {
        val magicIndicator = binding.tbs
        val commonNavigator = CommonNavigator(this)
        commonNavigator.setAdjustMode(true)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return titles.size
            }
            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
                colorTransitionPagerTitleView.normalColor = Color.parseColor("#969696")
                colorTransitionPagerTitleView.selectedColor = Color.parseColor("#000000")
                colorTransitionPagerTitleView.text = titles[index]
                colorTransitionPagerTitleView.setOnClickListener { view ->
                    binding.viewPager.currentItem =
                        index
                }
                return colorTransitionPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val linePagerIndicator = LinePagerIndicator(context)
                linePagerIndicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                linePagerIndicator.setColors(
                    ContextCompat.getColor(
                        this@ViewPageActivity,
                        R.color.color_F7B500
                    )
                )
                linePagerIndicator.lineHeight = 12f
                return linePagerIndicator
            }
        }

        magicIndicator.navigator = commonNavigator
        // 框架缺陷，手动改造。
        val childCount = commonNavigator.titleContainer.childCount
        for (i in 0 until childCount) {
            commonNavigator.titleContainer.getChildAt(i).layoutParams.width =
                LinearLayout.LayoutParams.WRAP_CONTENT
        }
        ViewPagerHelper.bind(magicIndicator, binding.viewPager)


        binding.viewPager.offscreenPageLimit = 4
        binding.viewPager.adapter = BaseFragmentPageAdapter(fragments,supportFragmentManager)
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        binding.viewPager.currentItem = 0
    }

}