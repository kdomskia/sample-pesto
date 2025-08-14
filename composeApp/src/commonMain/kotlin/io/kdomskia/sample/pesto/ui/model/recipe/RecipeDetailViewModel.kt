package io.kdomskia.sample.pesto.ui.model.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.kdomskia.sample.pesto.domain.model.recipe.Recipe
import io.kdomskia.sample.pesto.domain.usecase.recipe.GetRecipeByIdUseCase
import io.kdomskia.sample.pesto.ui.extension.collectAsFetchState
import io.kdomskia.sample.pesto.domain.model.fetch.FetchError
import io.kdomskia.sample.pesto.ui.model.fetch.FetchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

typealias GetRecipeState = FetchState<Recipe, FetchError>

class RecipeDetailViewModel(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase
) : ViewModel() {

    private val _recipe = MutableStateFlow<GetRecipeState>(FetchState.Idle())

    val recipe = _recipe.asStateFlow()

    fun fetchRecipe(id: String) {
        viewModelScope.launch {
            getRecipeByIdUseCase(id).collectAsFetchState {
                _recipe.value = it
            }
        }
    }

}