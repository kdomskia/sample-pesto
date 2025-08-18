package io.kdomskia.sample.pesto.data.repository.recipe

import io.kdomskia.sample.pesto.data.extension.fetchResult
import io.kdomskia.sample.pesto.data.resource.recipe.RecipeResource
import io.kdomskia.sample.pesto.domain.repository.recipe.GetRecipeResult
import io.kdomskia.sample.pesto.domain.repository.recipe.GetRecipesResult
import io.kdomskia.sample.pesto.domain.repository.recipe.RecipeRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.get
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(
    private val httpClient: HttpClient
) : RecipeRepository {

    override fun getRecipes(): Flow<GetRecipesResult> = httpClient.fetchResult { get(RecipeResource()) }

    override fun getRecipe(id: String): Flow<GetRecipeResult> = httpClient.fetchResult { get(RecipeResource.Get(id = id)) }

    override fun getFavoriteRecipes(): Flow<GetRecipesResult> = httpClient.fetchResult { get(RecipeResource.Favorites()) }

}