package io.kdomskia.sample.pesto.controller

import io.kdomskia.sample.pesto.domain.model.recipe.Recipe
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recipe")
class RecipeController {

    @Value("\${image.protocol:http}")
    private val protocol: String? = null

    private val recipes: List<Recipe> by lazy {
        ClassPathResource("recipes.json", javaClass.classLoader).inputStream.use {
            Json.decodeFromStream<List<Recipe>>(it)
        }
    }

    @GetMapping
    fun getAll(
        @RequestHeader("Host") host: String
    ): List<Recipe> {
        return recipes.withImageUrl(host)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @GetMapping("/{id}")
    fun getById(
        @RequestHeader("Host") host: String,
        @PathVariable id: String
    ): Recipe {
        return recipes
            .first { it.id == id }
            .withImageUrl(host)
    }

    @GetMapping("/favorites")
    fun getFavorites(
        @RequestHeader("Host") host: String
    ): List<Recipe> {
        val favoritesId = listOf(
            "c3d4e5f6a7b80123456789cd",
            "f6a7b8c9d0e1234567890fab",
            "e5f6a7b8c9d0123456789efa",
            "a7b8c9d0e1f234567890abcd",
            "e1f2a3b4c5d678901234ef01",
            "c5d6e7f8a9b012345678f345",
            "e7f8a9b0c1d234567890f567",
            "a9b0c1d2e3f456789012f789"
        )
        return recipes
            .filter { it.id in favoritesId }
            .withImageUrl(host)
    }

    private fun Recipe.withImageUrl(host: String) = copy(
        imgUrl = "$protocol://$host/recipe/$id/image"
    )

    private fun List<Recipe>.withImageUrl(host: String) = map { it.withImageUrl(host) }

}