package io.kdomskia.sample.pesto.ui.navigation.destination

import io.kdomskia.sample.pesto.ui.model.root.RootAction
import io.kdomskia.sample.pesto.ui.navigation.NavigationMenuItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface AppDestination {

    @Serializable
    @SerialName("recipes")
    object RecipeList : AppDestination

    @Serializable
    @SerialName("recipe")
    data class RecipeDetail(
        val recipeId: String
    ) : AppDestination

    @Serializable
    @SerialName("notes")
    object Notes : AppDestination

    @Serializable
    @SerialName("account")
    object Account : AppDestination

    @Serializable
    @SerialName("settings")
    object Settings : AppDestination

    @Serializable
    @SerialName("about")
    object About : AppDestination

    companion object {

        val entries = listOf(
            RecipeList::class,
            RecipeDetail::class,
            Notes::class,
            Account::class,
            Settings::class,
            About::class
        )

    }

}

fun AppDestination.getMenuItem(): NavigationMenuItem = when (this) {
    AppDestination.Notes -> NavigationMenuItem.Notes
    AppDestination.RecipeList, is AppDestination.RecipeDetail -> NavigationMenuItem.Recipes
    AppDestination.Account -> NavigationMenuItem.Account
    AppDestination.Settings -> NavigationMenuItem.Settings
    AppDestination.About -> NavigationMenuItem.About
}

fun AppDestination.getActionType(): RootAction = when (this) {
    AppDestination.RecipeList -> RootAction.Edit
    else -> RootAction.Close
}