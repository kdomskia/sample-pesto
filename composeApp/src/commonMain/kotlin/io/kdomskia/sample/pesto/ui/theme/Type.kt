package io.kdomskia.sample.pesto.ui.theme

import io.kdomskia.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.kdomskia.sample.pesto.Res
import io.kdomskia.sample.pesto.lekton_bold
import io.kdomskia.sample.pesto.lekton_regular
import io.kdomskia.sample.pesto.montserrat_light
import io.kdomskia.sample.pesto.montserrat_medium
import io.kdomskia.sample.pesto.montserrat_regular
import io.kdomskia.sample.pesto.montserrat_semibold
import org.jetbrains.compose.resources.Font

val Typography: Typography
    @Composable
    get() {
        val montserrat = FontFamily(
            Font(resource = Res.font.montserrat_regular, weight = FontWeight.Normal),
            Font(resource = Res.font.montserrat_medium, weight = FontWeight.Medium),
            Font(resource = Res.font.montserrat_light, weight = FontWeight.Light),
            Font(resource = Res.font.montserrat_semibold, weight = FontWeight.SemiBold)
        )
        val lekton = FontFamily(
            Font(resource = Res.font.lekton_regular, weight = FontWeight.Normal),
            Font(resource = Res.font.lekton_bold, weight = FontWeight.Bold)
        )

        return Typography(
            displayLarge = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Normal,
                fontSize = 57.sp,
                lineHeight = 64.sp,
                letterSpacing = 0.sp,
            ),
            displayMedium = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Normal,
                fontSize = 45.sp,
                lineHeight = 52.sp,
                letterSpacing = 0.sp,
            ),
            displaySmall = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 36.sp,
                lineHeight = 44.sp,
                letterSpacing = 0.sp,
            ),
            headlineLarge = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Normal,
                fontSize = 32.sp,
                lineHeight = 40.sp,
                letterSpacing = 0.sp,
            ),
            headlineMedium = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Normal,
                fontSize = 28.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.sp,
            ),
            headlineSmall = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                letterSpacing = 0.sp,
            ),
            titleLarge = TextStyle(
                fontFamily = lekton,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp,
            ),
            titleMedium = TextStyle(
                fontFamily = lekton,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.1.sp,
            ),
            titleSmall = TextStyle(
                fontFamily = lekton,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp,
            ),
            bodyLarge = TextStyle(
                fontFamily = lekton,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.1.sp,
            ),
            bodyMedium = TextStyle(
                fontFamily = lekton,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.2.sp,
            ),
            bodySmall = TextStyle(
                fontFamily = lekton,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
            ),
            labelLarge = TextStyle(
                fontFamily = lekton,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp,
            ),
            labelMedium = TextStyle(
                fontFamily = lekton,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
            ),
            labelSmall = TextStyle(
                fontFamily = lekton,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
            )
        )
    }