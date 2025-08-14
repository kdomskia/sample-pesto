package io.kdomskia.sample.pesto

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import io.kdomskia.compose.KdomskiaApp
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.compose.material3.DrawerValue
import io.kdomskia.compose.material3.rememberDrawerState
import io.kdomskia.compose.resource.DrawableResourcesRef
import io.kdomskia.compose.ui.unit.pxToDp
import io.kdomskia.navigation.compose.currentBackStackEntryAsState
import io.kdomskia.navigation.compose.rememberNavController
import io.kdomskia.sample.pesto.di.appDi
import io.kdomskia.sample.pesto.ui.component.AppScaffold
import io.kdomskia.sample.pesto.ui.extension.appDestination
import io.kdomskia.sample.pesto.ui.model.root.RootAction
import io.kdomskia.sample.pesto.ui.navigation.AppNavHost
import io.kdomskia.sample.pesto.ui.navigation.LocalNavHostController
import io.kdomskia.sample.pesto.ui.navigation.destination.AppDestination
import io.kdomskia.sample.pesto.ui.navigation.navigateToStartDestination
import io.kdomskia.sample.pesto.ui.size.FixedLeftFloatingContainerWidthState
import io.kdomskia.sample.pesto.ui.size.LocalFixedLeftFloatingContainerWidthState
import io.kdomskia.sample.pesto.ui.theme.AppTheme
import kotlinx.coroutines.launch
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(
        application = appDi
    ) {
        KdomskiaApp(
            drawableResources = DrawableResourcesRef(
                all = Res.allDrawableResources,
                onGetUri = Res::getUri
            )
        ) {
            val scope = rememberCoroutineScope()
            val navController = rememberNavController()
            val fixedLeftFloatingContainerWidthState = remember {
                FixedLeftFloatingContainerWidthState()
            }
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val entry by navController.currentBackStackEntryAsState()
            val destination = entry.appDestination
            val contentPadding = PaddingValues(
                start = fixedLeftFloatingContainerWidthState.value.pxToDp()
            )

            AppTheme {
                CompositionLocalProvider(
                    LocalNavHostController provides navController,
                    LocalFixedLeftFloatingContainerWidthState provides fixedLeftFloatingContainerWidthState
                ) {
                    AppScaffold(
                        destination = destination,
                        drawerState = drawerState,
                        onActionClick = {
                            when (it) {
                                RootAction.Edit -> {

                                }

                                RootAction.Close -> {
                                    if (destination is AppDestination.RecipeDetail) {
                                        navController.navigateUp()
                                    } else {
                                        navController.navigateToStartDestination()
                                    }
                                }
                            }
                        },
                        onCloseDrawer = {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    ) {
                        AppNavHost(
                            navController = navController,
                            contentPadding = contentPadding,
                            onMenuClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}