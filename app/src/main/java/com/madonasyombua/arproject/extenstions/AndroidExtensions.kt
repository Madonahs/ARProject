package com.madonasyombua.arproject.extenstions

import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
fun AppCompatActivity.handleFullScreenWindow() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setFlags(
        FLAG_FULLSCREEN,
        FLAG_FULLSCREEN
    )
}

