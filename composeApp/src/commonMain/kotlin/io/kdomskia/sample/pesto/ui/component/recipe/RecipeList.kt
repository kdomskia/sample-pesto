package io.kdomskia.sample.pesto.ui.component.recipe

import androidx.compose.runtime.Composable
import io.kdomskia.compose.foundation.dom.DomOverflow
import io.kdomskia.compose.foundation.dom.DomScrollOptions
import io.kdomskia.compose.foundation.dom.DomScrollTarget
import io.kdomskia.compose.foundation.layout.Arrangement
import io.kdomskia.compose.foundation.layout.BoxWithConstraints
import io.kdomskia.compose.foundation.layout.Column
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.compose.foundation.layout.Spacer
import io.kdomskia.compose.foundation.layout.WindowInsets
import io.kdomskia.compose.foundation.layout.bottom
import io.kdomskia.compose.foundation.layout.copy
import io.kdomskia.compose.foundation.layout.end
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.height
import io.kdomskia.compose.foundation.layout.padding
import io.kdomskia.compose.foundation.layout.safeDrawing
import io.kdomskia.compose.foundation.layout.start
import io.kdomskia.compose.foundation.layout.top
import io.kdomskia.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import io.kdomskia.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import io.kdomskia.compose.foundation.lazy.staggeredgrid.itemsIndexed
import io.kdomskia.compose.foundation.rememberScrollState
import io.kdomskia.compose.foundation.verticalScroll
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.sample.pesto.domain.extension.isOdd
import io.kdomskia.sample.pesto.domain.model.recipe.Recipe
import io.kdomskia.sample.pesto.ui.extension.byWidthSizeClass
import io.kdomskia.sample.pesto.ui.extension.getBottom
import io.kdomskia.sample.pesto.ui.res.dimens
import io.kdomskia.sample.pesto.ui.size.computeRecipeListDimensions
import io.kdomskia.sample.pesto.ui.util.isWindowWidthExpanded
import kotlin.math.max

private const val GRID_MIN_COLUMNS = 2

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    contentPadding: PaddingValues
) {
    val computedPadding = contentPadding.copy(
        bottom = contentPadding.bottom + WindowInsets.safeDrawing.getBottom() + dimens.paddingMedium
    )
    if (isWindowWidthExpanded)
        RecipeGrid(
            recipes = recipes,
            contentPadding = computedPadding
        )
    else
        RecipeColumn(
            recipes = recipes,
            contentPadding = computedPadding
        )
}

@Composable
private fun RecipeColumn(
    recipes: List<Recipe>,
    contentPadding: PaddingValues
) {
    val extraPaddingHorizontal = byWidthSizeClass(
        compact = { dimens.paddingMedium },
        medium = { dimens.paddingLarge },
        expanded = { dimens.paddingLarge }
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(
                state = rememberScrollState(),
                domOptions = DomScrollOptions(
                    target = DomScrollTarget.Window,
                    overflow = DomOverflow.Auto
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    contentPadding.copy(
                        start = contentPadding.start + extraPaddingHorizontal,
                        top = contentPadding.top + dimens.paddingMedium,
                        end = contentPadding.end + extraPaddingHorizontal,
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(dimens.paddingExtraLarge)
        ) {
            recipes.forEach { recipe ->
                RecipeItem(
                    recipe = recipe
                )
            }
        }
    }
}

@Composable
private fun RecipeGrid(
    recipes: List<Recipe>,
    contentPadding: PaddingValues
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val computedDimensions = computeRecipeListDimensions(
            parentWidth = maxWidth,
            paddingValues = contentPadding.copy(
                top = contentPadding.top + dimens.paddingMedium
            )
        )
        val columns = max(
            GRID_MIN_COLUMNS,
            (computedDimensions.width / dimens.recipeExtendedGridColumnMinWidth).toInt()
        )
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(count = columns),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = computedDimensions.paddingValues,
            verticalItemSpacing = dimens.paddingExtraLarge,
            horizontalArrangement = Arrangement.spacedBy(dimens.paddingExtraLarge),
            domScrollOptions = DomScrollOptions(
                target = DomScrollTarget.Window
            )
        ) {
            itemsIndexed(recipes) { index, recipe ->
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val firstRow = index < columns
                    val oddColumn = index.isOdd
                    if (firstRow && oddColumn)
                        Spacer(Modifier.height(dimens.recipeGridItemEvenSpacing).fillMaxWidth())
                    RecipeItem(
                        recipe = recipe
                    )
                }
            }
        }
    }
}