package com.example.mvvmapp.ui.videoview

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityVideoviewBinding
import me.goldze.mvvmhabit.base.NullBaseViewModel
import me.goldze.mvvmhabit.base.BaseActivity
import me.tatarka.bindingcollectionadapter2.BR

class VideoViewActivity: BaseActivity<ActivityVideoviewBinding, NullBaseViewModel>(){
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_videoview
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()

        startLoopVideo()

    }

    override fun onResume() {
        super.onResume()
        if (binding.viewVideo != null) {
            binding.viewVideo.resume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (binding.viewVideo.canPause()) {
            binding.viewVideo.pause()
        }
    }

    fun startLoopVideo() {
        //bugly崩溃
        try {
            val uri = "android.resource://" + packageName + "/" //+ R.raw.logo_loop
            binding.viewVideo.setVideoPath(uri)
            binding.viewVideo.setVideoURI(Uri.parse(uri))
            binding.viewVideo.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
                mp.setVolume(0f, 0f)
                mp.start()
                mp.isLooping = true
            })
            binding.viewVideo.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    override fun onDestroy() {
        binding.viewVideo.stopPlayback()
        super.onDestroy()
    }

}
