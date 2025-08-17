package io.kdomskia.sample.pesto.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.navigation.compose.NavHost
import io.kdomskia.navigation.compose.composable
import io.kdomskia.sample.pesto.ui.navigation.destination.AppDestination
import io.kdomskia.sample.pesto.ui.screen.about.AboutScreen
import io.kdomskia.sample.pesto.ui.screen.account.AccountScreen
import io.kdomskia.sample.pesto.ui.screen.notes.NotesScreen
import io.kdomskia.sample.pesto.ui.screen.recipe.RecipeDetailScreen
import io.kdomskia.sample.pesto.ui.screen.recipe.RecipeListScreen
import io.kdomskia.sample.pesto.ui.screen.settings.SettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    contentPadding: PaddingValues,
    onMenuClick: () -> Unit
) {
    NavHost(
        modifier = Modifier.fillMaxWidth(),
        navController = navController,
        startDestination = AppDestination.RecipeList::class
    ) {
        composable(
            route = AppDestination.RecipeList::class
        ) {
            RecipeListScreen(
                contentPadding = contentPadding,
                onMenuClick = onMenuClick
            )
        }

        composable(
            route = AppDestination.RecipeDetail::class
        ) {
            RecipeDetailScreen(
                contentPadding = contentPadding,
                recipeId = it.toRoute<AppDestination.RecipeDetail>().recipeId,
                onClose = { navController.navigateUp() }
            )
        }
        composable(
            route = AppDestination.Notes::class
        ) {
            NotesScreen(
                contentPadding = contentPadding,
                onClose = { navController.navigateUp() }
            )
        }

        composable(
            route = AppDestination.Account::class
        ) {
            AccountScreen(
                contentPadding = contentPadding,
                onClose = { navController.navigateUp() }
            )
        }

        composable(
            route = AppDestination.Settings::class
        ) {
            SettingsScreen(
                contentPadding = contentPadding,
                onClose = { navController.navigateUp() }
            )
        }

        composable(
            route = AppDestination.About::class
        ) {
            AboutScreen(
                contentPadding = contentPadding,
                onClose = { navController.navigateUp() }
            )
        }
    }
}

fun NavController.navigateToStartDestination() {
    navigate(
        route = AppDestination.RecipeList
    ) {
        popUpTo<AppDestination.RecipeList> {
            inclusive = true
        }
    }
}