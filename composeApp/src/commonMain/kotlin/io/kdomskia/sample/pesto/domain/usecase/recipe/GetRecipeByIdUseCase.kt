package io.kdomskia.sample.pesto.domain.usecase.recipe

import io.kdomskia.sample.pesto.domain.repository.recipe.GetRecipeResult
import io.kdomskia.sample.pesto.domain.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetRecipeByIdUseCase(
    private val repository: RecipeRepository
) {

    operator fun invoke(id: String): Flow<GetRecipeResult> {
        return repository.getRecipe(id)
    }

}