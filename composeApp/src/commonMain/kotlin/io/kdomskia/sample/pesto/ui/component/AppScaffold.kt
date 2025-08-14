package io.kdomskia.sample.pesto.ui.component

import androidx.compose.runtime.Composable
import io.kdomskia.compose.foundation.layout.Box
import io.kdomskia.compose.foundation.layout.FloatingContainer
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.compose.foundation.layout.Placement
import io.kdomskia.compose.foundation.layout.WindowInsets
import io.kdomskia.compose.foundation.layout.WindowInsetsSides
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.fillViewportHeight
import io.kdomskia.compose.foundation.layout.padding
import io.kdomskia.compose.foundation.layout.safeDrawing
import io.kdomskia.compose.material3.DrawerState
import io.kdomskia.compose.material3.ModalNavigationDrawer
import io.kdomskia.compose.material3.Surface
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.draw.clipToBounds
import io.kdomskia.sample.pesto.ui.extension.asPaddingValues
import io.kdomskia.sample.pesto.ui.model.root.RootAction
import io.kdomskia.sample.pesto.ui.navigation.NavigationMenuItem
import io.kdomskia.sample.pesto.ui.navigation.destination.AppDestination
import io.kdomskia.sample.pesto.ui.navigation.destination.getActionType
import io.kdomskia.sample.pesto.ui.navigation.destination.getMenuItem
import io.kdomskia.sample.pesto.ui.util.isWindowWidthExpanded

@Composable
fun AppScaffold(
    destination: AppDestination?,
    drawerState: DrawerState,
    onActionClick: (RootAction) -> Unit,
    onCloseDrawer: () -> Unit,
    content: @Composable () -> Unit
) {
    val contentPadding = WindowInsets.safeDrawing.asPaddingValues(exclude = WindowInsetsSides.Bottom)
    val actionType = destination?.getActionType() ?: RootAction.Close
    val selectedMenu = destination?.getMenuItem() ?: NavigationMenuItem.Recipes

    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isWindowWidthExpanded) {
            ExpandedWindowNavigableContainer(
                actionType = actionType,
                onActionClick = { onActionClick(actionType) },
                selectedMenu = selectedMenu,
                contentPadding = contentPadding,
                onMenuClick = onCloseDrawer,
                content = { content() }
            )
        } else {
            NonExpandedWindowNavigableContainer(
                drawerState = drawerState,
                selectedMenu = selectedMenu,
                contentPadding = contentPadding,
                onMenuClick = onCloseDrawer,
                content = { content() }
            )
        }
    }
}

@Composable
private fun NonExpandedWindowNavigableContainer(
    drawerState: DrawerState,
    selectedMenu: NavigationMenuItem,
    contentPadding: PaddingValues,
    onMenuClick: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        modifier = Modifier.fillMaxWidth(),
        drawerContent = {
            AppDrawer(
                selectedMenu = selectedMenu,
                onMenuClick = onMenuClick
            )
        },
        drawerState = drawerState
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .clipToBounds()
        ) {
            content()
        }
    }
}

@Composable
private fun ExpandedWindowNavigableContainer(
    actionType: RootAction,
    onActionClick: () -> Unit,
    selectedMenu: NavigationMenuItem,
    contentPadding: PaddingValues,
    onMenuClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillViewportHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .clipToBounds()
        ) {
            content()
        }
        FloatingContainer(
            horizontalPlacement = Placement.Horizontal.AlignStart(),
            verticalPlacement = Placement.Vertical.Fill()
        ) {
            AppRail(
                actionType = actionType,
                onActionClick = onActionClick,
                selectedMenu = selectedMenu,
                onMenuClick = onMenuClick
            )
        }
    }
}