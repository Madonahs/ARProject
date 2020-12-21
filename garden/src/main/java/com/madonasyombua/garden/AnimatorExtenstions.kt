package com.madonasyombua.garden

import android.animation.Animator
import android.animation.ObjectAnimator

inline fun ObjectAnimator.doWhenFinished(
    crossinline block: () -> Unit
) {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) = Unit
        override fun onAnimationEnd(p0: Animator?) = block()
        override fun onAnimationCancel(p0: Animator?) = Unit
        override fun onAnimationStart(p0: Animator?) = Unit

    })
}