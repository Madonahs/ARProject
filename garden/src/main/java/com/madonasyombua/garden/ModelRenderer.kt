package com.madonasyombua.garden

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.view.MotionEvent
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.madonasyombua.garden.AnimalModels.DEFAULT_POSITION
import kotlin.random.Random

object ModelRenderer {

    //Render a model using the [ModelRenderable]. The context gets the model source
    //and the renderingModel renders the information. The lambda executes if ready to
    //use rendered model
    inline fun renderObject(
        context: Context,
        renderingModel: RenderingModel,
        crossinline accept: (Renderable) -> Unit
    ) {
        ModelRenderable.builder()
            .setSource(context, Uri.parse(renderingModel.model))
            .build()
            .thenAccept { accept(it) }
            .exceptionally {
                AlertDialog.Builder(context)
                    .setMessage(it.localizedMessage)
                    .show()
                return@exceptionally null
            }
    }

    // add my sheep on the scene
    fun addAnimalsOnGardenScene(
        fragment: ArFragment,
        anchor: Anchor,
        renderable: Renderable,
        renderingModel: RenderingModel
    ) {
        val anchorNode = AnchorNode(anchor)
        TransformableNode(fragment.transformationSystem).apply {
            name = renderingModel.modelName
            localPosition = renderingModel.localPosition
            this.renderable = renderable
            translationController.isEnabled =
                false //Returns the controller that translates this node using a drag gesture.
            setParent(anchorNode)
            setLookDirection(renderingModel.direction)
            scaleController.minScale = renderingModel.scaleType
            scaleController.maxScale = renderingModel.scaleType + 0.05f
            fragment.arSceneView.scene.addChild(anchorNode)
            setOnTouchListener { hitTestResult, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    hitTestResult.node?.let { node ->
                        node.setLookDirection(Vector3(0f, 0f, 0f))
                        ModelAnimation.translateModel(
                            anchorNode = node,
                            vectorPosition = Vector3(
                                localPosition.x,
                                localPosition.y + 0.25f,
                                localPosition.z
                            ),
                            doWhenFinished = {
                                val localPosition = renderingModel.localPosition
                                ModelAnimation.translateModel(node, localPosition)
                            }

                        )
                    }
                }
                true
            }
        }
    }

    //add the garden to the Scene
    fun addGardenScene(
        fragment: ArFragment,
        anchor: Anchor,
        renderable: Renderable,
        renderingModel: RenderingModel
    ) {
        val anchorNode = AnchorNode(anchor)
        TransformableNode(fragment.transformationSystem).apply {
            name = renderingModel.modelName
            localPosition = renderingModel.localPosition
            this.renderable = renderable
            translationController.isEnabled = false
            setParent(anchorNode)
            setLookDirection(renderingModel.direction)
            scaleController.minScale = renderingModel.scaleType
            scaleController.maxScale = renderingModel.scaleType + 0.05f
            fragment.arSceneView.scene.addChild(anchorNode)
        }
    }

    //feed the animals? Let's see how this will work out
    fun addGrainsOnScene(
        fragment: ArFragment,
        anchor: Anchor,
        animal: Anchor,
        renderable: Renderable,
        renderingModel: RenderingModel,
        sheep: RenderingModel,
        doAfterEat: () -> Unit
    ) {
        val anchorNode = AnchorNode(anchor)
        TransformableNode(fragment.transformationSystem).apply {
            name = renderingModel.modelName
            localPosition = renderingModel.localPosition
            this.renderable = renderable
            setParent(anchorNode)
            setLookDirection(renderingModel.direction)
            scaleController.minScale = renderingModel.scaleType
            scaleController.maxScale = renderingModel.scaleType + 0.05f
            fragment.arSceneView.scene.addChild(anchorNode)
            setOnTapListener { hitTestResult, _ ->
                hitTestResult.node?.let { node ->
                    val sheepPosition = sheep.localPosition
                    //here we use the vector to calculate the x, y and z
                    val targetPosition = Vector3(
                        sheepPosition.x + getRandomPosition(),
                        sheepPosition.y + getRandomPosition(),
                        sheepPosition.z + getRandomPosition()
                    )

                    ModelAnimation.translateModel(
                        anchorNode = node,
                        vectorPosition = targetPosition,
                        durationTime = 750L
                    ) {
                        val length = Vector3.subtract(sheepPosition, targetPosition).length()
                        when {
                            length > 0.45 -> {
                                ModelAnimation.translateModel(
                                    anchorNode = node,
                                    vectorPosition = DEFAULT_POSITION,
                                    durationTime = 0
                                )
                            }
                            else -> {
                                doAfterEat()
                                animal.detach()
                            }
                        }
                    }

                    ModelAnimation.rotateModel(
                        anchorNode = node,
                        durationTime = 500L
                    ) {
                        node.setLookDirection(Vector3(0f, 0f, 0f))
                    }
                }
            }
            select()
        }

    }

    private fun getRandomPosition(): Float {
        val position = Random.nextFloat()
        return when {
            position <= 0.5f -> {
                position
            }
            else -> {
                position - 1
            }
        }
    }

}