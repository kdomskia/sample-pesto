package io.kdomskia.sample.pesto.ui.model.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.kdomskia.sample.pesto.domain.model.fetch.FetchError
import io.kdomskia.sample.pesto.domain.model.recipe.Recipe
import io.kdomskia.sample.pesto.domain.usecase.recipe.GetRecipesUseCase
import io.kdomskia.sample.pesto.ui.extension.collectAsFetchState
import io.kdomskia.sample.pesto.ui.model.fetch.FetchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

typealias GetRecipesState = FetchState<List<Recipe>, FetchError>

class RecipeItemsViewModel(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _recipes = MutableStateFlow<GetRecipesState>(FetchState.Idle())

    val recipes = _recipes.asStateFlow()

    fun fetchRecipes(favoritesOnly: Boolean) {
        viewModelScope.launch {
            getRecipesUseCase(
                favoritesOnly = favoritesOnly
            ).collectAsFetchState {
                _recipes.value = it
            }
        }
    }

}