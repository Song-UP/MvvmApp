package com.example.mvvmapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmapp.databinding.ActivityMainBinding
import me.goldze.mvvmhabit.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }
    override fun initVariableId(): Int {
       return BR.viewModel
    }

}
