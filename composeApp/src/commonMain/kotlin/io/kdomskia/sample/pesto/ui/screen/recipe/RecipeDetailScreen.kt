package io.kdomskia.sample.pesto.ui.screen.recipe


import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.kdomskia.compose.foundation.Image
import io.kdomskia.compose.foundation.background
import io.kdomskia.compose.foundation.dom.DomScrollOptions
import io.kdomskia.compose.foundation.dom.DomScrollTarget
import io.kdomskia.compose.foundation.dottedShapeBackground
import io.kdomskia.compose.foundation.layout.Arrangement
import io.kdomskia.compose.foundation.layout.Box
import io.kdomskia.compose.foundation.layout.BoxWithConstraints
import io.kdomskia.compose.foundation.layout.Column
import io.kdomskia.compose.foundation.layout.FloatingContainer
import io.kdomskia.compose.foundation.layout.FlowRow
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.compose.foundation.layout.Placement
import io.kdomskia.compose.foundation.layout.Row
import io.kdomskia.compose.foundation.layout.RowScope
import io.kdomskia.compose.foundation.layout.WindowInsets
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.height
import io.kdomskia.compose.foundation.layout.padding
import io.kdomskia.compose.foundation.layout.safeDrawing
import io.kdomskia.compose.foundation.layout.size
import io.kdomskia.compose.foundation.layout.start
import io.kdomskia.compose.foundation.layout.widthIn
import io.kdomskia.compose.foundation.rememberScrollState
import io.kdomskia.compose.foundation.verticalScroll
import io.kdomskia.compose.material3.ExtendedFloatingActionButton
import io.kdomskia.compose.material3.Icon
import io.kdomskia.compose.material3.MaterialTheme
import io.kdomskia.compose.material3.Text
import io.kdomskia.compose.ui.Alignment
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.layout.onGloballyPositioned
import io.kdomskia.compose.ui.resource.img
import io.kdomskia.compose.ui.unit.pxToDp
import io.kdomskia.sample.pesto.Res
import io.kdomskia.sample.pesto.domain.model.recipe.Recipe
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeIngredient
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning
import io.kdomskia.sample.pesto.ic_plus
import io.kdomskia.sample.pesto.ic_recipes
import io.kdomskia.sample.pesto.ui.component.AppBar
import io.kdomskia.sample.pesto.ui.component.AppBarIconType
import io.kdomskia.sample.pesto.ui.component.ErrorStateContainer
import io.kdomskia.sample.pesto.ui.component.recipe.RecipeImage
import io.kdomskia.sample.pesto.ui.extension.getBottom
import io.kdomskia.sample.pesto.ui.extension.icon
import io.kdomskia.sample.pesto.ui.extension.label
import io.kdomskia.sample.pesto.ui.extension.use
import io.kdomskia.sample.pesto.ui.model.recipe.RecipeDetailViewModel
import io.kdomskia.sample.pesto.ui.res.dimens
import io.kdomskia.sample.pesto.ui.res.strings
import io.kdomskia.sample.pesto.ui.theme.Nickel80
import io.kdomskia.sample.pesto.ui.util.isWindowWidthExpanded
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.viewmodel.koinViewModel

private val logoHeight = 24.dp
private val imageWidth = 628.dp
private val expandedDescriptionHorizontalPadding = 64.dp
private val expandedTitleSpacing = 30.dp
private val expandedIngredientsHorizontalPadding = 54.dp
private val ingredientDotBottomPadding = 10.dp
private val ingredientItemVerticalPadding = 12.dp
private val ingredientDotSize = 2.dp
private val ingredientDotSpacing = ingredientDotSize * 2.5f
private val estimatedCookingButtonHeight = 84.dp

@Composable
fun RecipeDetailScreen(
    contentPadding: PaddingValues,
    recipeId: String,
    onClose: () -> Unit
) {
    val viewModel = koinViewModel<RecipeDetailViewModel>()
    val state by viewModel.recipe.collectAsStateWithLifecycle()

    LaunchedEffect(recipeId) {
        viewModel.fetchRecipe(recipeId)
    }

    state.use(
        success = {
            RecipeDetailContent(
                contentPadding = contentPadding,
                recipe = it,
                onClose = onClose
            )
        },
        error = {
            ErrorStateContainer(
                onTryAgain = {
                    viewModel.fetchRecipe(recipeId)
                }
            )
        }
    )
}

@Composable
private fun RecipeDetailContent(
    contentPadding: PaddingValues,
    recipe: Recipe,
    onClose: () -> Unit
) {
    val style = rememberRecipeDetailStyle(isWindowWidthExpanded)
    var headerHeight by remember { mutableStateOf(0) }
    val paddingStart = contentPadding.start
    val paddingBottom = dimens.paddingMedium + WindowInsets.safeDrawing.getBottom()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FloatingContainer(
            horizontalPlacement = Placement.Horizontal.Fill(
                paddingStart = paddingStart
            ),
            verticalPlacement = Placement.Vertical.AlignTop()
        ) {
            AppBar(
                modifier = Modifier.onGloballyPositioned {
                    headerHeight = it.size.height
                },
                onIconClick = onClose,
                iconType = AppBarIconType.Close,
                logoHeight = logoHeight,
                logoPadding = dimens.paddingExtraLarge
            )
        }
        Column(
            modifier = Modifier
                .padding(
                    top = headerHeight.pxToDp(),
                    start = paddingStart + style.containerHorizontalPadding
                )
                .fillMaxWidth()
                .verticalScroll(
                    state = rememberScrollState(),
                    domOptions = DomScrollOptions(
                        target = DomScrollTarget.Window
                    )
                ),
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        end = style.containerHorizontalPadding,
                        bottom = paddingBottom + estimatedCookingButtonHeight
                    )
                    .widthIn(max = dimens.recipeDetailMaxWidth)
                    .align(Alignment.CenterHorizontally),
            ) {
                Content(
                    recipe = recipe,
                    style = style
                )
                Warnings(
                    warnings = recipe.warnings,
                    style = style
                )
                Ingredients(
                    ingredients = recipe.ingredients,
                    style = style
                )
            }
        }
        FloatingContainer(
            horizontalPlacement = Placement.Horizontal.AlignCenter(
                paddingStart = paddingStart
            ),
            verticalPlacement = Placement.Vertical.AlignBottom(
                paddingBottom = paddingBottom
            )
        ) {
            StartCookingButton()
        }
    }
}

@Composable
private fun rememberRecipeDetailStyle(isExpandedWindow: Boolean): RecipeDetailStyle {
    val isExpanded = isExpandedWindow
    val typography = MaterialTheme.typography
    val shapes = MaterialTheme.shapes
    return remember(isExpanded, typography, shapes) {
        if (isExpanded)
            RecipeDetailStyle(
                containerHorizontalPadding = dimens.none,
                contentVerticalPadding = dimens.paddingLarge,
                titleTopPadding = expandedTitleSpacing,
                titleTextStyle = typography.displayLarge,
                descriptionHorizontalPadding = expandedDescriptionHorizontalPadding,
                imageShape = shapes.extraLarge,
                ingredientsHorizontalPadding = expandedIngredientsHorizontalPadding
            )
        else
            RecipeDetailStyle(
                containerHorizontalPadding = dimens.paddingMedium,
                contentVerticalPadding = dimens.paddingMedium,
                titleTopPadding = dimens.paddingSmall,
                titleTextStyle = typography.displaySmall,
                descriptionHorizontalPadding = dimens.none,
                imageShape = shapes.medium,
                ingredientsHorizontalPadding = dimens.none
            )
    }
}

@Composable
private fun Content(
    recipe: Recipe,
    style: RecipeDetailStyle
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val imageWidth = min(imageWidth, this.maxWidth)
            val imageHeight = imageWidth * dimens.imageAspectRatio

            RecipeImage(
                modifier = Modifier
                    .size(
                        width = imageWidth,
                        height = imageHeight
                    ),
                imgUrl = recipe.imgUrl.orEmpty(),
                shape = MaterialTheme.shapes.extraLarge
            )
        }
        Text(
            text = recipe.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = style.titleTopPadding,
                    bottom = 0.dp,
                    start = style.descriptionHorizontalPadding,
                    end = style.descriptionHorizontalPadding
                ),
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Center,
            style = style.titleTextStyle
        )
        Text(
            text = recipe.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = style.contentVerticalPadding,
                    start = style.descriptionHorizontalPadding,
                    end = style.descriptionHorizontalPadding
                ),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun Warnings(
    warnings: List<RecipeWarning>,
    style: RecipeDetailStyle
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = style.contentVerticalPadding,
                start = style.descriptionHorizontalPadding,
                end = style.descriptionHorizontalPadding
            ),
        horizontalArrangement = Arrangement.spacedBy(
            space = dimens.paddingExtraLarge,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        warnings.forEach {
            WarningItem(it)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun WarningItem(
    warning: RecipeWarning
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            img = warning.icon.img,
            contentDescription = null
        )
        Text(
            text = warning.label,
            modifier = Modifier.padding(start = dimens.paddingSmall),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun Ingredients(
    ingredients: List<RecipeIngredient>,
    style: RecipeDetailStyle
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = style.ingredientsHorizontalPadding)
    ) {
        Text(
            text = strings.ingredients,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimens.paddingLarge,
                    bottom = dimens.paddingSmall
                ),
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )
        ingredients.forEach {
            IngredientItem(it)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun IngredientItem(ingredient: RecipeIngredient) {
    val color = MaterialTheme.colorScheme.primary
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = ingredientItemVerticalPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            img = Res.drawable.ic_plus.img,
            contentDescription = null,
            tint = color
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = ingredient.description,
                modifier = Modifier
                    .padding(start = dimens.paddingMedium),
                color = color,
                style = MaterialTheme.typography.titleMedium
            )
            IngredientLine()
            Text(
                text = ingredient.amount,
                color = color,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
private fun RowScope.IngredientLine() {
    Box(
        modifier = Modifier
            .padding(
                start = dimens.paddingSmall,
                end = dimens.paddingSmall,
                bottom = ingredientDotBottomPadding
            )
            .weight(1f)
            .height(ingredientDotSize)
            .dottedShapeBackground(
                color = Nickel80,
                size = ingredientDotSize,
                spacing = ingredientDotSpacing
            )
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun StartCookingButton() {
    ExtendedFloatingActionButton(
        text = {
            Text(text = strings.startCooking)
        },
        icon = {
            Icon(
                img = Res.drawable.ic_recipes.img,
                contentDescription = null
            )
        },
        onClick = { },
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    )
}

@Immutable
private data class RecipeDetailStyle(
    val containerHorizontalPadding: Dp,
    val contentVerticalPadding: Dp,
    val titleTopPadding: Dp,
    val titleTextStyle: TextStyle,
    val descriptionHorizontalPadding: Dp,
    val imageShape: Shape,
    val ingredientsHorizontalPadding: Dp
)