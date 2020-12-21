package com.madonasyombua.garden

import android.animation.ObjectAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3Evaluator

/**
 * @author madona
 */

object ModelAnimation {

    /**
     * This model translates a node to a desired position
     * @param anchorNode this is a target [Node] for translating
     * @param vectorPosition this is a position for translating the [Node]
     * @param durationTime this is the duration for the animation execution
     * @param doWhenFinished this is a lambda for executing after the animation is done
     */
    inline fun translateModel(
        anchorNode: Node,
        vectorPosition: Vector3,
        durationTime: Long = 150L,
        crossinline doWhenFinished: () -> Unit = {}
    ) {
        ObjectAnimator().apply {
            setAutoCancel(false)
            target = anchorNode
            duration = durationTime
            setObjectValues(
                anchorNode.localPosition,
                vectorPosition
            )
            setPropertyName("localPosition")
            setEvaluator(Vector3Evaluator())
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }.doWhenFinished {
            doWhenFinished()
        }


    }
}