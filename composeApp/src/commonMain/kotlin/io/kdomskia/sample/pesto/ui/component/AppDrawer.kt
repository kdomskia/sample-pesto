package io.kdomskia.sample.pesto.ui.component

import androidx.compose.runtime.Composable
import io.kdomskia.compose.ui.Alignment
import io.kdomskia.compose.foundation.layout.Box
import io.kdomskia.compose.foundation.layout.Column
import io.kdomskia.compose.foundation.layout.WindowInsets
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.height
import io.kdomskia.compose.foundation.layout.padding
import io.kdomskia.compose.foundation.layout.safeDrawing
import io.kdomskia.compose.material3.Icon
import io.kdomskia.compose.material3.MaterialTheme
import io.kdomskia.compose.material3.ModalDrawerSheet
import io.kdomskia.compose.material3.NavigationDrawerItem
import io.kdomskia.compose.material3.Text
import io.kdomskia.compose.navigation.NavLink
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.resource.img
import io.kdomskia.sample.pesto.ui.navigation.LocalNavHostController
import io.kdomskia.sample.pesto.ui.navigation.NavigationMenuItem
import io.kdomskia.sample.pesto.ui.navigation.destination
import io.kdomskia.sample.pesto.ui.res.dimens

@Composable
fun AppDrawer(
    selectedMenu: NavigationMenuItem,
    onMenuClick: () -> Unit
) {
    val navController = LocalNavHostController.current

    if (navController != null) {
        ModalDrawerSheet(
            windowInsets = WindowInsets.safeDrawing
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimens.drawerPadding)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = dimens.paddingMedium)
                ) {
                    AppLogo(
                        modifier = Modifier
                            .height(dimens.drawerLogoHeight)
                            .align(alignment = Alignment.Start)
                    )
                }

                NavigationMenuItem.entries.forEach { menu ->
                    val selected = menu == selectedMenu
                    val color = if (selected) {
                        MaterialTheme.colorScheme.onSecondaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                    NavLink(
                        modifier = Modifier.fillMaxWidth(),
                        navController = navController,
                        route = menu.destination
                    ) {
                        NavigationDrawerItem(
                            modifier = Modifier.fillMaxWidth(),
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
                        )
                    }
                }
            }
        }
    }
}