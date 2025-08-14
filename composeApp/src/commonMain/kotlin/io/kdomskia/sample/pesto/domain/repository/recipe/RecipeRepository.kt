package io.kdomskia.sample.pesto.domain.repository.recipe

import io.kdomskia.sample.pesto.domain.model.fetch.FetchResult
import io.kdomskia.sample.pesto.domain.model.recipe.Recipe
import io.kdomskia.sample.pesto.domain.model.fetch.FetchError
import kotlinx.coroutines.flow.Flow

typealias GetRecipesResult = FetchResult<List<Recipe>, FetchError>

typealias GetRecipeResult = FetchResult<Recipe, FetchError>

interface RecipeRepository {

    fun getRecipes(): Flow<GetRecipesResult>

    fun getRecipe(id: String): Flow<GetRecipeResult>

}