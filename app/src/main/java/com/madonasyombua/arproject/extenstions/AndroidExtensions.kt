package com.madonasyombua.arproject.extenstions

import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.core.Single

@Suppress("DEPRECATION")
fun AppCompatActivity.handleFullScreenWindow() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setFlags(
        FLAG_FULLSCREEN,
        FLAG_FULLSCREEN
    )
}

fun <T: Any> Single<T>.uiSubscribe(
    lifecycleOwner: LifecycleOwner,
    onError: (Throwable) -> Unit,
    onSuccess: (T) -> Unit
) = uiSubscribe()






