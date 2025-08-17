package io.kdomskia.sample.pesto.ui.navigation

import io.kdomskia.sample.pesto.Res
import io.kdomskia.sample.pesto.ic_about
import io.kdomskia.sample.pesto.ic_account
import io.kdomskia.sample.pesto.ic_notes
import io.kdomskia.sample.pesto.ic_recipes
import io.kdomskia.sample.pesto.ic_settings
import io.kdomskia.sample.pesto.ui.navigation.destination.AppDestination
import io.kdomskia.sample.pesto.ui.res.strings
import org.jetbrains.compose.resources.DrawableResource

enum class NavigationMenuItem(
    val label: String,
    val icon: DrawableResource
) {

    Notes(
        label = strings.notes,
        icon = Res.drawable.ic_notes
    ),

    Recipes(
        label = strings.recipes,
        icon = Res.drawable.ic_recipes
    ),

    Account(
        label = strings.account,
        icon = Res.drawable.ic_account
    ),

    Settings(
        label = strings.settings,
        icon = Res.drawable.ic_settings
    ),

    About(
        label = strings.about,
        icon = Res.drawable.ic_about
    )

}

val NavigationMenuItem.destination: AppDestination
    get() = when (this) {
        NavigationMenuItem.Notes -> AppDestination.Notes
        NavigationMenuItem.Recipes -> AppDestination.RecipeList
        NavigationMenuItem.Account -> AppDestination.Account
        NavigationMenuItem.Settings -> AppDestination.Settings
        NavigationMenuItem.About -> AppDestination.About
    }