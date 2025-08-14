package io.kdomskia.sample.pesto.ui.res

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.kdomskia.sample.pesto.ui.util.isWindowWidthExpanded

val dimens = DimenResource

object DimenResource {

    val none = 0.dp

    val paddingExtraSmall = 4.dp

    val paddingSmall = 8.dp

    val paddingMedium = 16.dp

    val paddingLarge = 24.dp

    val paddingExtraLarge = 32.dp

    val appBarLogoHeight = 48.dp

    val drawerLogoHeight = 32.dp

    val drawerPadding = 12.dp

    val recipeGridItemEvenSpacing = 40.dp

    val recipeHorizontalExtendedPadding = 86.dp

    val recipeExtendedGridColumnMinWidth = 330.dp

    val progressBarHeight = 6.dp

    val recipeListMaxWidth = 1600.dp

    val recipeDetailMaxWidth = 1200.dp

    val imageAspectRatio = 0.57f

}

val recipeHorizontalPadding: Dp
    @Composable
    get() = if (isWindowWidthExpanded) {
        dimens.recipeHorizontalExtendedPadding
    } else {
        dimens.none
    }