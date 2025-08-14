package io.kdomskia.sample.pesto.domain.model.recipe

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val imgUrl: String? = null,
    val labels: List<RecipeLabel>,
    val ingredients: List<RecipeIngredient>
)