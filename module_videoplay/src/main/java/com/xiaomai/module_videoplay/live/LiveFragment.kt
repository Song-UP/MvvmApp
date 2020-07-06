package com.xiaomai.module_videoplay.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xiaomai.module_videoplay.R
import com.xiaomai.module_videoplay.video.control.VideoControlView
import com.xiaomai.module_videoplay.video.control.VideoIconsCallback
import com.xiaomai.module_videoplay.video.media.IPlayerInterface
import com.xiaomai.module_videoplay.video.media.OnPlayerStateListener
import kotlinx.android.synthetic.main.activity_live.*
import kotlinx.android.synthetic.main.fragment_live.*

class LiveFragment :Fragment(){

    var mPlayerSurface: IPlayerInterface? = null
    val testUrl = "https://vdse.bdstatic.com/e0c2f7ae496b212fb511c1b8970bdc07.mp4?authorization=bce-auth-v1%2Ffb297a5cc0fb434c971b8fa103e8dd7b%2F2017-05-11T09%3A02%3A31Z%2F-1%2F%2F1b938745bce143d0d302a33e6e9995e764dd6c5b65b1a73a0c367608cf52e339";

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_live,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    fun initView(){
        initExoplayer()

        mPlayerSurface?.setOnPlayerStateListener(object : OnPlayerStateListener() {
            override fun onPlayerStatePlaying() {
                viewControlView.changePlayControlView(VideoControlView.TYPE_PLAYER)
            }

        })

        viewControlView.videoControlCallback = object :VideoIconsCallback(){
            override fun onPleyVideo() {
                startMediaPlayer(testUrl)
            }
            override fun onPauseVideo() {
                mPlayerSurface?.pausePlay()
            }

            override fun onReleaseVideo() {
                mPlayerSurface?.releasePlayer()
            }

            override fun onResume() {
                super.onResume()
                mPlayerSurface?.resumePlay()
            }

        }

    }

    fun initExoplayer(){
//        exo_play.setOnNotWorkedCallback(OnNotWorkedCallback {  })
//        exo_play.setOnPlayerStateListener()
        mPlayerSurface = exoPlayerView

    }

    private fun startMediaPlayer(url:String){
        mPlayerSurface?.let {
            it.setPlayUrl(url)
            it.setReady(true)
            it.startPlay(true)
        }
    }

    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }

    private fun releasePlayer(){
        mPlayerSurface?.apply {
            this.pausePlay()
            this.releasePlayer()
        }
    }




}