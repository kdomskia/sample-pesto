package io.kdomskia.sample.pesto.ui.extension

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.toRoute
import io.kdomskia.sample.pesto.ui.navigation.destination.AppDestination
import kotlin.reflect.KClass

fun <T : Any> NavBackStackEntry?.routeFrom(items: List<KClass<out T>>): T? {
    val entry = this ?: return null

    for (item in items) {
        if (entry.destination.hasRoute(item))
            return entry.toRoute(item)
    }

    return null
}

val NavBackStackEntry?.appDestination: AppDestination?
    get() = routeFrom(AppDestination.entries)