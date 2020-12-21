package com.madonasyombua.garden

import com.google.ar.sceneform.math.Vector3

/**
 * @author madona
 * This is the rendering model that will be drawn on the AR frame
 * @param name this is the name of the model
 * @param model this is the rendering source
 * @param direction this is usually a default direction of the model when initializing
 * @param scaleType is a default scale of the model when initializing
 * @param localPosition A default local position
 */

data class RenderingModel(
    val name: String,
    val model: String,
    val direction: Vector3 = Vector3(0f, 0f, 0f),
    val scaleType: Float = 1f,
    val localPosition: Vector3 = Vector3(0.5f, 0.5f, 0.5f)
)