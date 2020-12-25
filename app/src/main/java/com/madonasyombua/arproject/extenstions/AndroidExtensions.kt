package com.madonasyombua.arproject.extenstions

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.util.Log
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
fun AppCompatActivity.handleFullScreenWindow() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setFlags(
        FLAG_FULLSCREEN,
        FLAG_FULLSCREEN
    )
}

private const val MIN_OPENGL_VERSION = 3.0

fun Activity.isSupportedDeviceOrFinish(): Boolean {
    val openGlVersionString = (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
        .deviceConfigurationInfo
        .glEsVersion

    if (openGlVersionString.toDouble() < MIN_OPENGL_VERSION) {
        Log.e("AR Project", "Sceneform will require OpenGL ES 3.0 later")
        Toast.makeText(this, "Sceneform will require OpenGL ES 3.0 or later", Toast.LENGTH_LONG).show()
        finish()
        return false
    }
    return true
}





