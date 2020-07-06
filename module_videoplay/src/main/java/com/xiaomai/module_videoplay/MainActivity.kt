package com.xiaomai.module_videoplay

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xiaomai.module_videoplay.live.LiveActivity
import com.xiaomai.module_videoplay.video.media.IPlayerInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    fun initView(){
        liveButton.setOnClickListener { startActivity(Intent(this, LiveActivity::class.java)) }
    }

}
