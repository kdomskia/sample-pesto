package io.kdomskia.sample.pesto.controller

import io.kdomskia.sample.pesto.domain.model.recipe.Recipe
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recipe")
class RecipeController {

    @Value("\${server.address}")
    private val serverAddress: String? = null

    @Value("\${server.port}")
    private val serverPort: String? = null

    private val recipes: List<Recipe> by lazy {
        ClassPathResource("recipes.json", javaClass.classLoader).inputStream.use {
            Json.decodeFromStream<List<Recipe>>(it)
        }.map {
            it.copy(
                imgUrl = "http://$serverAddress:$serverPort/recipe/${it.id}/image"
            )
        }
    }

    @GetMapping
    fun findAll(): List<Recipe> {
        return recipes
    }

    @OptIn(ExperimentalSerializationApi::class)
    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): Recipe {
        return recipes.first { it.id == id }
    }

}