package com.madonasyombua.garden

import com.google.ar.sceneform.math.Vector3
import kotlin.random.Random

object AnimalModels {

    private fun getSheep() = RenderingModel(
        name = "sheep1",
        model = "sheep1.sfb",
        localPosition = DEFAULT_ANIMAL_POSITION
    )

    private fun getPushingSheep() = RenderingModel(
        name = "pushing_sheep",
        model = "pushing_sheep.sfb",
        scaleType = 2.0f,
        localPosition = DEFAULT_ANIMAL_POSITION
    )

    private fun getScene() = RenderingModel(
        name = "scene",
        model = "scene.sfb",
        scaleType = 0.7f,
        localPosition = DEFAULT_ANIMAL_POSITION
    )

    fun garden() = RenderingModel(
        name = "garden",
        model = "garden.sfb",
        localPosition = DEFAULT_POSITION_GARDEN
    )

    fun getRandomAnimal(): RenderingModel {
        return when (Random.nextInt(3)) {
            0 -> getPushingSheep()
            1 -> getSheep()
            2 -> getScene()
            else -> getSheep()
        }
    }

    fun getAnimalByName(name: String): RenderingModel {
        return when (name) {
            "pushing_sheep" -> getPushingSheep()
            "sheep1" -> getSheep()
            "scene" -> getScene()
            else -> getSheep()
        }
    }

    val DEFAULT_POSITION = Vector3(0f, -0.5f, 0.5f)
    private val DEFAULT_ANIMAL_POSITION = Vector3(0f, -0.25f, -3f)
    private val DEFAULT_POSITION_GARDEN = Vector3(0f, -1f, -3f)
}