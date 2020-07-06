/**
 * Designed and developed by Nemo (nemo@seektopser.com)
 *
 */
package com.xiaomai.module_videoplay.utils

import com.google.android.exoplayer2.util.Log


inline fun Any.tryCatchAll(message: String = "", action: () -> Unit) {
    try {
        action()
    } catch (e: Exception) {
        Log.w("tryCatchAll","Failed to $message. ${e.message}")
        e.printStackTrace()
    }
}
