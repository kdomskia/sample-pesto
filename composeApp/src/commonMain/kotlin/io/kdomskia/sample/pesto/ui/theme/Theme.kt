package io.kdomskia.sample.pesto.ui.theme

import io.kdomskia.compose.material3.MaterialTheme
import io.kdomskia.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme = lightColorScheme(
    primary = OliveGreen40,
    onPrimary = OliveGreen100,
    primaryContainer = OliveGreen90,
    onPrimaryContainer = OliveGreen10,
    inversePrimary = OliveGreen80,
    secondary = Nickel40,
    onSecondary = Nickel100,
    secondaryContainer = Nickel90,
    onSecondaryContainer = Nickel10,
    tertiary = Sinopia60,
    onTertiary = Sinopia100,
    tertiaryContainer = Sinopia90,
    onTertiaryContainer = Sinopia10,
    background = Latte99,
    onBackground = Latte10,
    surface = Latte99,
    onSurface = Latte10,
    surfaceVariant = Gray90,
    onSurfaceVariant = Gray30,
    inverseSurface = Latte20,
    inverseOnSurface = Latte95,
    error = Carnelian50,
    onError = Carnelian100,
    errorContainer = Carnelian90,
    onErrorContainer = Carnelian10,
    outline = Gray50,
    outlineVariant = Gray90,
    scrim = Latte10
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        shapes = Shapes
    ) {
        content()
    }
}