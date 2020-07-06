package com.xiaomai.module_videoplay.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

public class AndroidUtils {
    public static final String TAG = "AndroidUtils";
    public static int SYSTEM_UI = 0;

    public static Activity scanForActivity(Context context) {
        if (context == null) return null;

        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }

        return null;
    }

    public static AppCompatActivity getAppCompActivity(Context context) {
        if (context == null) return null;
        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        } else if (context instanceof ContextThemeWrapper) {
            return getAppCompActivity(((ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }

    public static void setRequestedOrientation(Context context, int orientation) {
        if (AndroidUtils.getAppCompActivity(context) != null) {
            AndroidUtils.getAppCompActivity(context).setRequestedOrientation(
                    orientation);
        } else {
            AndroidUtils.scanForActivity(context).setRequestedOrientation(
                    orientation);
        }
    }

    public static Window getWindow(Context context) {
        if (AndroidUtils.getAppCompActivity(context) != null) {
            return AndroidUtils.getAppCompActivity(context).getWindow();
        } else {
            return AndroidUtils.scanForActivity(context).getWindow();
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @SuppressLint("RestrictedApi")
    public static void showStatusBar(Context context) {
        AndroidUtils.getWindow(context).clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //如果是沉浸式的，全屏前就没有状态栏
    @SuppressLint("RestrictedApi")
    public static void hideStatusBar(Context context) {
        AndroidUtils.getWindow(context).addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @SuppressLint("NewApi")
    public static void hideSystemUI(Context context) {
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        ;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            uiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }
        SYSTEM_UI = AndroidUtils.getWindow(context).getDecorView().getSystemUiVisibility();
        AndroidUtils.getWindow(context).getDecorView().setSystemUiVisibility(uiOptions);

    }

    @SuppressLint("NewApi")
    public static void showSystemUI(Context context) {
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        AndroidUtils.getWindow(context).getDecorView().setSystemUiVisibility(SYSTEM_UI);
    }

}
