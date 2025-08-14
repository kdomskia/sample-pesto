package io.kdomskia.sample.pesto.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import io.kdomskia.compose.foundation.Image
import io.kdomskia.compose.foundation.layout.Arrangement
import io.kdomskia.compose.foundation.layout.Box
import io.kdomskia.compose.foundation.layout.Column
import io.kdomskia.compose.foundation.layout.fillMaxHeight
import io.kdomskia.compose.foundation.layout.height
import io.kdomskia.compose.foundation.layout.padding
import io.kdomskia.compose.material3.Icon
import io.kdomskia.compose.material3.IconButton
import io.kdomskia.compose.material3.MaterialTheme
import io.kdomskia.compose.material3.NavigationRail
import io.kdomskia.compose.material3.NavigationRailItem
import io.kdomskia.compose.material3.Text
import io.kdomskia.compose.navigation.NavLink
import io.kdomskia.compose.ui.Alignment
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.layout.onGloballyPositioned
import io.kdomskia.compose.ui.resource.img
import io.kdomskia.sample.pesto.Res
import io.kdomskia.sample.pesto.ic_close
import io.kdomskia.sample.pesto.ui.model.root.RootAction
import io.kdomskia.sample.pesto.ui.navigation.LocalNavHostController
import io.kdomskia.sample.pesto.ui.navigation.NavigationMenuItem
import io.kdomskia.sample.pesto.ui.navigation.destination
import io.kdomskia.sample.pesto.ui.res.dimens
import io.kdomskia.sample.pesto.ui.size.LocalFixedLeftFloatingContainerWidthState
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun AppRail(
    modifier: Modifier = Modifier,
    actionType: RootAction,
    onActionClick: () -> Unit,
    selectedMenu: NavigationMenuItem,
    onMenuClick: () -> Unit
) {
    var appRailWidth by remember { mutableStateOf(0) }
    val navController = LocalNavHostController.current
    val fixedLeftFloatingContainerWidthState = LocalFixedLeftFloatingContainerWidthState.current

    LaunchedEffect(appRailWidth) {
        fixedLeftFloatingContainerWidthState.value = appRailWidth
    }

    DisposableEffect(Unit) {
        onDispose {
            fixedLeftFloatingContainerWidthState.value = 0
        }
    }

    if (navController != null) {
        NavigationRail(
            modifier = modifier.onGloballyPositioned {
                appRailWidth = it.size.width
            },
            header = {
                Box(
                    modifier = Modifier.height(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    RootActionHeader(
                        actionType = actionType,
                        onActionClick = onActionClick
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                NavigationMenuItem.entries.forEach { menu ->
                    val selected = menu == selectedMenu
                    val color = if (selected) {
                        MaterialTheme.colorScheme.onSecondaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                    NavLink(
                        navController = navController,
                        route = menu.destination
                    ) {
                        NavigationRailItem(
                            selected = selected,
                            onClick = navigateThen { onMenuClick() },
                            label = {
                                Text(
                                    text = menu.label,
                                    style = MaterialTheme.typography.labelLarge
                                )
                            },
                            icon = {
                                Icon(
                                    img = menu.icon.img,
                                    contentDescription = null,
                                    tint = color
                                )
                            },
                            alwaysShowLabel = false
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RootActionHeader(
    actionType: RootAction,
    onActionClick: () -> Unit
) {
    when (actionType) {
        RootAction.Edit -> {
            EditFloatingActionButton(
                modifier = Modifier.padding(top = dimens.paddingSmall)
            )
        }

        RootAction.Close -> {
            IconButton(
                onClick = onActionClick
            ) {
                Image(
                    img = Res.drawable.ic_close.img,
                    contentDescription = null
                )
            }
        }
    }
}