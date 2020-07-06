package com.xiaomai.module_videoplay.video.control

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.xiaomai.module_videoplay.R
import com.xiaomai.module_videoplay.utils.AndroidUtils
import com.xiaomai.module_videoplay.utils.getStatusBarHeight
import kotlinx.android.synthetic.main.sport_video_ui_control.view.*


open class VideoControlView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        // 是否是全屏
        var isMaxScreen = false
        const val TYPE_IDEA = 0
        const val TYPE_PAUSE = 1
        const val TYPE_LOADING = 2
        const val TYPE_PLAYER = 3
    }

    //系统点击监听
    var mGestureDetector: GestureDetector? = null

    var curPlayType = TYPE_IDEA
    // 是否显示UI
    var isShow = false
    var videoControlCallback: VideoIconsCallback? = null

    private var handler = object : Handler(Looper.getMainLooper()) {}

    val hideVisibleRun = Runnable {
        changeViewAnimal(false)
    }

    /**
     * 改变top，bottom的view显示和隐藏动画
     */
    fun changeViewAnimal(toIsShow: Boolean) {
        if (isShow != toIsShow) {
            isShow = toIsShow
            if (toIsShow) {
                showBottom()
                showTop()
                showStatusBar()
                // 自动隐藏
                handler.postDelayed(hideVisibleRun, 3000)
            } else {
                hideBottom()
                hideTop()
                hideStatusBar()
                handler.removeCallbacks(hideVisibleRun)
            }
        }
    }

    init {
        inflate(getContext(), R.layout.sport_video_ui_control, this)
//        setOnTouchListener(this)
        changeViewAnimal(true)
        initViewListener()
    }


    private fun initViewListener() {
        // 横竖屏切换
        showMaxVideoScreen.setOnClickListener {
            if (isMaxScreen) {
                gotoScreenNormal()
            } else {
                gotoScreenFullscreen()
            }
            isMaxScreen = !isMaxScreen
        }

        // 全屏模式下，title的返回按钮
        back.setOnClickListener {
            if (isMaxScreen) {
                gotoScreenNormal()
            } else {
                dispatchCloseEvent()
            }
        }

        // 路线切换
        tvChangeLine.setOnClickListener {
            videoControlCallback?.changeLine()
        }

        //暂停和播放视频
        iv_video_play_pause.setOnClickListener {
            when (curPlayType) {
                TYPE_IDEA -> {
                    changePlayControlView(TYPE_LOADING)
                    videoControlCallback?.onPleyVideo()
                }
                TYPE_PAUSE -> {
                    changePlayControlView(TYPE_LOADING)
                    videoControlCallback?.onResume()
                }
                TYPE_PLAYER -> {
                    changePlayControlView(TYPE_PAUSE)
                    videoControlCallback?.onPauseVideo()
                }
            }
        }
    }

    /**
     * 改变播放控件的状态
     */
    fun changePlayControlView(playerType: Int) {
        curPlayType = playerType
        when (curPlayType) {
            TYPE_IDEA, TYPE_PAUSE -> { //要暂停
                progress_loading.visibility = View.GONE
                iv_video_play_pause.visibility = View.VISIBLE
                iv_video_play_pause.setImageResource(R.drawable.ic_video_pause)
            }
            TYPE_PLAYER -> { //要播放
                progress_loading.visibility = View.GONE
                iv_video_play_pause.visibility = View.VISIBLE
                iv_video_play_pause.setImageResource(R.drawable.ic_video_play)
            }
            TYPE_LOADING -> { //加载中
                progress_loading.visibility = View.VISIBLE
                iv_video_play_pause.visibility = View.GONE
            }
        }
    }

    private fun dispatchCloseEvent() {
        videoControlCallback?.close()
    }


    fun gotoScreenNormal() {
        AndroidUtils.showStatusBar(context)
        AndroidUtils.setRequestedOrientation(context, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        AndroidUtils.showSystemUI(context)
    }

    fun gotoScreenFullscreen() {
        AndroidUtils.hideStatusBar(context)
        AndroidUtils.setRequestedOrientation(
            context,
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        )
        AndroidUtils.hideSystemUI(context)
        isFocusableInTouchMode = true
        requestFocus()
    }

    override fun onDetachedFromWindow() {
        videoControlCallback?.onReleaseVideo()
        videoControlCallback = null
        super.onDetachedFromWindow()
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        mGestureDetector?.onTouchEvent(ev)
        when (ev?.action) {
            MotionEvent.ACTION_UP -> changeViewAnimal(true)
        }
        return true
    }

//    var lastMills = 0.toLong()
//    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        // 如果是双击事件，不触发触摸事件
//        if (event?.action == MotionEvent.ACTION_DOWN) {
//            val currentTimeMillis = System.currentTimeMillis()
//            if (currentTimeMillis - lastMills < 300) {
//                handler.removeCallbacks(callbackDelay)
//            } else {
//                handler.postDelayed(callbackDelay, 300)
//            }
//            lastMills = currentTimeMillis
//        }
//        return false
//    }


        @SuppressLint("RestrictedApi")
        open fun showStatusBar() {
            if (!isMaxScreen) return
            AndroidUtils.showStatusBar(context)
            AndroidUtils.showSystemUI(context)
            AndroidUtils.getWindow(context)?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        }

        @SuppressLint("RestrictedApi")
        open fun hideStatusBar() {
            if (!isMaxScreen) return
            AndroidUtils.hideStatusBar(context)
            AndroidUtils.hideSystemUI(context)

        }

        private fun showBottom() {
            val translationY: Float = videoControlBottom.translationY
            val animator =
                ObjectAnimator.ofFloat(videoControlBottom, "translationY", translationY, 0f)
            animator.duration = 300
            animator.start()

        }

        private fun hideBottom() {
            val translationY: Float = videoControlBottom.translationY
            val height = videoControlBottom.height.toFloat()
            val animator =
                ObjectAnimator.ofFloat(videoControlBottom, "translationY", translationY, height)
            animator.duration = 300
            animator.start()
        }

        private fun hideTop() {
            val translationY: Float = videoControlTop.translationY
            val height = videoControlTop.height.toFloat()
            val animator =
                ObjectAnimator.ofFloat(videoControlTop, "translationY", translationY, -height)
            animator.duration = 300
            animator.start()
        }

        private fun showTop() {
            val translationY: Float = videoControlTop.translationY
            val animator = ObjectAnimator.ofFloat(videoControlTop, "translationY", translationY, 0f)
            animator.duration = 300
            animator.start()
        }

        override fun onConfigurationChanged(newConfig: Configuration?) {
            super.onConfigurationChanged(newConfig)
        }

        override fun onRestoreInstanceState(state: Parcelable?) {
            super.onRestoreInstanceState(state)
//        (state as Bundle)?.apply {
//            isMaxScreen = getBoolean("isMaxScreen", false)
//            showMaxVideoScreen.setImageResource(
//                if(isMaxScreen) R.drawable.full_screen_zoom_in
//                else R.drawable.full_screen_btn )
//            changeChatLocation(isMaxScreen)
//        }
        }

        override fun onSaveInstanceState(): Parcelable? {
            return super.onSaveInstanceState()
//        return Bundle().apply { putBoolean("isMaxScreen", isMaxScreen) }
        }

    }

    open class VideoIconsCallback {
        open fun close() {}
        open fun changeLine() {}
        open fun onPleyVideo() {}
        open fun onPauseVideo() {}
        open fun onResume() {}
        open fun onReleaseVideo() {}
    }
