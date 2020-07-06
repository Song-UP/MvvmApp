package com.xiaomai.module_videoplay.live

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xiaomai.module_videoplay.R
import com.xiaomai.module_videoplay.video.media.IPlayerInterface
import kotlinx.android.synthetic.main.activity_live.*

class LiveActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)

        initView()

    }

    private fun initView(){
        supportFragmentManager.beginTransaction()
            .add(R.id.linearlayout, LiveFragment(),LiveFragment::class.java.simpleName)
            .commitAllowingStateLoss()
    }



}