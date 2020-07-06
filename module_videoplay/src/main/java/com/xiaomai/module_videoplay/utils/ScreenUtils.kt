package com.xiaomai.module_videoplay.utils

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.util.DisplayMetrics
import android.view.View


/**
 * @author : Levi
 * @date   : 2020/6/15 11:27 AM
 * @desc   : 屏幕相关的工具类
 */

/** 判断横竖屏 */
fun isLandscape(): Boolean {
    return AppManager.getsApplication().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

/** 获取屏幕的宽度 */
fun getScreenWidth(): Int {
    val metrics = AppManager.getsApplication().resources.displayMetrics
    return metrics.widthPixels
}

/** 获取屏幕的高度 */
fun getScreenHeight(): Int {
    val metrics = AppManager.getsApplication().resources.displayMetrics
    return metrics.heightPixels
}

/** 获取屏幕的状态栏的高度 */
fun getStatusBarHeight(): Int {
    var height = 0
    val resourceId = AppManager.getsApplication().resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        height = AppManager.getsApplication().resources.getDimensionPixelSize(resourceId)
    }
    if (height == 0) {
//      height = context.getResources().getDimensionPixelOffset(R.dimen.statusbar_view_height);
    }
    return height
}

/**
 * Return whether screen is locked.
 *
 * @return `true`: yes<br></br>`false`: no
 */
fun isScreenLock(): Boolean {
    val km = AppManager.getsApplication().getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            ?: return false
    return km.isKeyguardLocked
}

/**
 * Return the bitmap of screen.
 *
 * @param activity          The activity.
 * @param isDeleteStatusBar True to delete status bar, false otherwise.
 * @return the bitmap of screen
 */
fun screenShot( activity: Activity, isDeleteStatusBar: Boolean): Bitmap? {
    val decorView: View = activity.window.decorView
    val drawingCacheEnabled: Boolean = decorView.isDrawingCacheEnabled
    val willNotCacheDrawing: Boolean = decorView.willNotCacheDrawing()
    decorView.isDrawingCacheEnabled = true
    decorView.setWillNotCacheDrawing(false)
    var bmp: Bitmap? = decorView.drawingCache
    if (bmp == null) {
        decorView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        decorView.layout(0, 0, decorView.measuredWidth, decorView.measuredHeight)
        decorView.buildDrawingCache()
        bmp = Bitmap.createBitmap(decorView.drawingCache)
    }
    if (bmp == null) return null
    val dm = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(dm)
    val ret: Bitmap
    ret = if (isDeleteStatusBar) {
        val resources: Resources = activity.resources
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        val statusBarHeight: Int = resources.getDimensionPixelSize(resourceId)
        Bitmap.createBitmap(
                bmp,
                0,
                statusBarHeight,
                dm.widthPixels,
                dm.heightPixels - statusBarHeight
        )
    } else {
        Bitmap.createBitmap(bmp, 0, 0, dm.widthPixels, dm.heightPixels)
    }
    decorView.destroyDrawingCache()
    decorView.setWillNotCacheDrawing(willNotCacheDrawing)
    decorView.isDrawingCacheEnabled = drawingCacheEnabled
    return ret
}