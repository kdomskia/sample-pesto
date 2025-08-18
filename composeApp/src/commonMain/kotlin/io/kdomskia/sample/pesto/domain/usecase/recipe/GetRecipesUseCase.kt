package io.kdomskia.sample.pesto.domain.usecase.recipe

import io.kdomskia.sample.pesto.domain.repository.recipe.GetRecipesResult
import io.kdomskia.sample.pesto.domain.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetRecipesUseCase(
    private val repository: RecipeRepository
) {

    operator fun invoke(favoritesOnly: Boolean): Flow<GetRecipesResult> {
        return if (favoritesOnly)
            repository.getFavoriteRecipes()
        else
            repository.getRecipes()
    }

}