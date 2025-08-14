package io.kdomskia.sample.pesto.ui.screen.recipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.kdomskia.compose.foundation.HorizontalPager
import io.kdomskia.compose.foundation.PagerState
import io.kdomskia.compose.foundation.background
import io.kdomskia.compose.foundation.layout.Box
import io.kdomskia.compose.foundation.layout.BoxScope
import io.kdomskia.compose.foundation.layout.BoxWithConstraints
import io.kdomskia.compose.foundation.layout.Column
import io.kdomskia.compose.foundation.layout.FloatingContainer
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.compose.foundation.layout.Placement.Horizontal
import io.kdomskia.compose.foundation.layout.Placement.Vertical
import io.kdomskia.compose.foundation.layout.WindowInsets
import io.kdomskia.compose.foundation.layout.copy
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.padding
import io.kdomskia.compose.foundation.layout.safeDrawing
import io.kdomskia.compose.foundation.layout.start
import io.kdomskia.compose.foundation.layout.top
import io.kdomskia.compose.foundation.rememberPagerState
import io.kdomskia.compose.material3.MaterialTheme
import io.kdomskia.compose.material3.PrimaryTabRow
import io.kdomskia.compose.material3.Tab
import io.kdomskia.compose.material3.Text
import io.kdomskia.compose.ui.Alignment
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.layout.onGloballyPositioned
import io.kdomskia.compose.ui.unit.pxToDp
import io.kdomskia.sample.pesto.ui.component.AppBar
import io.kdomskia.sample.pesto.ui.component.AppBarIconType
import io.kdomskia.sample.pesto.ui.component.EditFloatingActionButton
import io.kdomskia.sample.pesto.ui.component.ErrorStateContainer
import io.kdomskia.sample.pesto.ui.component.recipe.RecipeList
import io.kdomskia.sample.pesto.ui.extension.getBottom
import io.kdomskia.sample.pesto.ui.extension.use
import io.kdomskia.sample.pesto.ui.model.recipe.RecipeItemsViewModel
import io.kdomskia.sample.pesto.ui.model.recipe.RecipeTab
import io.kdomskia.sample.pesto.ui.res.dimens
import io.kdomskia.sample.pesto.ui.size.computeRecipeListDimensions
import io.kdomskia.sample.pesto.ui.util.isWindowWidthExpanded
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RecipeListScreen(
    contentPadding: PaddingValues,
    onMenuClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { RecipeTab.entries.size }
    var headerHeight by remember { mutableStateOf(0) }
    val computedPadding = contentPadding.copy(
        top = contentPadding.top + headerHeight.pxToDp()
    )

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Pager(
            pagerState = pagerState,
            contentPadding = computedPadding
        )
        Header(
            modifier = Modifier.onGloballyPositioned {
                headerHeight = it.size.height
            },
            pagerState = pagerState,
            contentPadding = computedPadding,
            onSelectTab = {
                scope.launch {
                    pagerState.animateScrollToPage(it.ordinal)
                }
            },
            onMenuClick = onMenuClick
        )
        FloatingButtonContainer()
    }
}

@Composable
private fun BoxScope.Header(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    contentPadding: PaddingValues,
    onSelectTab: (RecipeTab) -> Unit,
    onMenuClick: () -> Unit
) {
    val selectedTab = remember(pagerState.currentPage) {
        RecipeTab.entries[pagerState.currentPage]
    }
    FloatingContainer(
        horizontalPlacement = Horizontal.Fill(
            paddingStart = contentPadding.start
        ),
        verticalPlacement = Vertical.AlignTop()
    ) {
        BoxWithConstraints(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        computeRecipeListDimensions(
                            parentWidth = maxWidth
                        ).paddingValues
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppBar(
                    onIconClick = onMenuClick,
                    iconType = AppBarIconType.Menu
                )
                Tabs(
                    modifier = Modifier.fillMaxWidth(),
                    selectedTab = selectedTab,
                    onSelectTab = onSelectTab
                )
            }
        }
    }
}

@Composable
private fun Pager(
    pagerState: PagerState,
    contentPadding: PaddingValues
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) {
        RecipesContent(
            tab = RecipeTab.entries[it],
            contentPadding = contentPadding
        )
    }
}

@Composable
private fun Tabs(
    modifier: Modifier = Modifier,
    selectedTab: RecipeTab,
    onSelectTab: (RecipeTab) -> Unit
) {
    PrimaryTabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab.ordinal
    ) {
        RecipeTab.entries.forEach { tab ->
            Tab(
                text = {
                    Text(
                        text = tab.label,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                selected = selectedTab == tab,
                onClick = { onSelectTab(tab) }
            )
        }
    }
}

@Composable
private fun BoxScope.FloatingButtonContainer() {
    if (isWindowWidthExpanded.not()) {
        val paddingEnd = dimens.paddingExtraLarge
        val paddingBottom = paddingEnd + WindowInsets.safeDrawing.getBottom()
        FloatingContainer(
            horizontalPlacement = Horizontal.AlignEnd(
                paddingEnd = paddingEnd
            ),
            verticalPlacement = Vertical.AlignBottom(
                paddingBottom = paddingBottom
            )
        ) {
            EditFloatingActionButton()
        }
    }
}

@Composable
private fun RecipesContent(
    tab: RecipeTab,
    contentPadding: PaddingValues
) {
    val viewModel = koinViewModel<RecipeItemsViewModel>()
    val state by viewModel.recipes.collectAsStateWithLifecycle()

    state.use(
        success = {
            RecipeList(
                recipes = it,
                contentPadding = contentPadding
            )
        },
        error = {
            ErrorStateContainer(
                modifier = Modifier.padding(contentPadding),
                onTryAgain = viewModel::fetchRecipes
            )
        }
    )
}