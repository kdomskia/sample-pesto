package io.kdomskia.sample.pesto.domain.model.recipe

import kotlinx.serialization.Serializable

@Serializable
data class RecipeIngredient(
    val description: String,
    val amount: String
)