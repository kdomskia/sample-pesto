package io.kdomskia.sample.pesto.ui.model.recipe

import io.kdomskia.sample.pesto.ui.res.strings

enum class RecipeTab(
    val label: String
) {

    All(
        label = strings.allRecipes
    ),

    Favorites(
        label = strings.favorites
    )

}