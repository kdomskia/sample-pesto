package io.kdomskia.sample.pesto.di.module

import io.kdomskia.sample.pesto.data.repository.recipe.RecipeRepositoryImpl
import io.kdomskia.sample.pesto.domain.repository.recipe.RecipeRepository
import io.kdomskia.sample.pesto.domain.usecase.recipe.GetRecipeByIdUseCase
import io.kdomskia.sample.pesto.domain.usecase.recipe.GetRecipesUseCase
import io.kdomskia.sample.pesto.ui.model.recipe.RecipeDetailViewModel
import io.kdomskia.sample.pesto.ui.model.recipe.RecipeItemsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val recipeModule = module {
    single<RecipeRepository> {
        RecipeRepositoryImpl(
            httpClient = get()
        )
    }
    single {
        GetRecipesUseCase(
            repository = get()
        )
    }
    single {
        GetRecipeByIdUseCase(
            repository = get()
        )
    }
    viewModel {
        RecipeItemsViewModel(
            getRecipesUseCase = get()
        )
    }
    viewModel {
        RecipeDetailViewModel(
            getRecipeByIdUseCase = get()
        )
    }
}