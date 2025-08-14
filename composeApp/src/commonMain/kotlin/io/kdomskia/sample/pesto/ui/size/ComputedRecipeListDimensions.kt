package io.kdomskia.sample.pesto.ui.size

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.compose.foundation.layout.copy
import io.kdomskia.compose.foundation.layout.end
import io.kdomskia.compose.foundation.layout.start
import io.kdomskia.sample.pesto.ui.res.dimens
import io.kdomskia.sample.pesto.ui.util.isWindowWidthExpanded

@Immutable
data class ComputedRecipeListDimensions(
    val width: Dp,
    val paddingValues: PaddingValues
)

@Composable
fun computeRecipeListDimensions(
    parentWidth: Dp,
    paddingValues: PaddingValues = PaddingValues()
): ComputedRecipeListDimensions {
    val extraHorizontalPadding = if (isWindowWidthExpanded)
        dimens.recipeHorizontalExtendedPadding
    else
        0.dp
    val totalHorizontalPadding = paddingValues.start + paddingValues.end + extraHorizontalPadding * 2
    val minWidth = min(
        parentWidth - totalHorizontalPadding,
        dimens.recipeListMaxWidth
    )
    val targetWidth = parentWidth - minWidth
    val widthByExcludingIncomingPadding = targetWidth - paddingValues.start - paddingValues.end
    val computedHorizontalPadding = widthByExcludingIncomingPadding / 2

    return ComputedRecipeListDimensions(
        width = minWidth,
        paddingValues = paddingValues.copy(
            start = paddingValues.start + computedHorizontalPadding,
            end = computedHorizontalPadding
        )
    )
}