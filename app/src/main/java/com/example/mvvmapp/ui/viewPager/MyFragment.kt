package com.example.mvvmapp.ui.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvvmapp.BR
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.FragmentMyBinding
import com.example.mvvmapp.ui.NullViewPage

import me.goldze.mvvmhabit.base.BaseFragment

class MyFragment:BaseFragment<FragmentMyBinding, NullViewPage>(){
    override fun initContentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int {
        return R.layout.fragment_my
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

}
