package io.kdomskia.sample.pesto.ui.screen.about

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import io.kdomskia.compose.foundation.background
import io.kdomskia.compose.foundation.dom.DomScrollOptions
import io.kdomskia.compose.foundation.dom.DomScrollTarget
import io.kdomskia.compose.foundation.layout.Column
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.compose.foundation.layout.Spacer
import io.kdomskia.compose.foundation.layout.ViewportContainer
import io.kdomskia.compose.foundation.layout.WindowInsets
import io.kdomskia.compose.foundation.layout.copy
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.height
import io.kdomskia.compose.foundation.layout.padding
import io.kdomskia.compose.foundation.layout.safeDrawing
import io.kdomskia.compose.foundation.layout.top
import io.kdomskia.compose.foundation.layout.widthIn
import io.kdomskia.compose.foundation.rememberScrollState
import io.kdomskia.compose.foundation.verticalScroll
import io.kdomskia.compose.material3.MaterialTheme
import io.kdomskia.compose.material3.Text
import io.kdomskia.compose.material3.TextButton
import io.kdomskia.compose.navigation.NavExternalLink
import io.kdomskia.compose.ui.Alignment
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.layout.onGloballyPositioned
import io.kdomskia.compose.ui.unit.pxToDp
import io.kdomskia.sample.pesto.data.external.ExternalLink
import io.kdomskia.sample.pesto.ui.component.AppBar
import io.kdomskia.sample.pesto.ui.component.AppBarIconType
import io.kdomskia.sample.pesto.ui.extension.getBottom
import io.kdomskia.sample.pesto.ui.res.dimens
import io.kdomskia.sample.pesto.ui.res.strings

@Composable
fun AboutScreen(
    contentPadding: PaddingValues,
    onClose: () -> Unit
) {
    var headerHeight by remember { mutableStateOf(0) }
    val rootPaddingTop = contentPadding.top + headerHeight.pxToDp()

    ViewportContainer(
        contentAlignment = Alignment.TopCenter
    ) {
        AppBar(
            modifier = Modifier.onGloballyPositioned {
                headerHeight = it.size.height
            },
            onIconClick = onClose,
            iconType = AppBarIconType.Close
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = rootPaddingTop)
            .verticalScroll(
                state = rememberScrollState(),
                domOptions = DomScrollOptions(
                    target = DomScrollTarget.Window
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AboutContent()
    }
}

@Composable
private fun AboutContent() {
    val padding = PaddingValues(dimens.paddingLarge).copy(
        bottom = dimens.paddingLarge + WindowInsets.safeDrawing.getBottom()
    )
    val text = buildAnnotatedString {
        append(strings.aboutLine1Part1)
        withStyle(
            SpanStyle(fontWeight = FontWeight.W900)
        ) {
            append(strings.kdomskia)
        }
        append(strings.aboutLine1Part2)
        withStyle(
            SpanStyle(fontWeight = FontWeight.W900)
        ) {
            append(strings.composeMultiplatform)
        }
        append(".")
        append("\n")
        append("\n")
        append("\n")
        append(strings.aboutLine2)
        withStyle(
            SpanStyle(fontWeight = FontWeight.W900)
        ) {
            append(strings.kotlinMultiplatform)
        }
        append(".")
        append("\n")
        append("\n")
        append("\n")
        append(strings.aboutLine3Part1)
        withStyle(
            SpanStyle(fontStyle = FontStyle.Italic)
        ) {
            append(strings.aboutLine3Part2)
        }
        append(".")
    }
    Column(
        modifier = Modifier
            .widthIn(max = dimens.aboutMaxWidth)
            .padding(padding)
    ) {
        Spacer(modifier = Modifier.height(dimens.paddingLarge))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.height(dimens.paddingLarge))
        Text(
            modifier = Modifier.padding(
                top = dimens.paddingExtraLarge,
                bottom = dimens.paddingLarge
            ),
            text = strings.resources,
            style = MaterialTheme.typography.headlineSmall
        )
        NavExternalLink(
            uri = ExternalLink.kdomskiaRepository
        ) {
            TextButton(
                onClick = navigate()
            ) {
                Text(
                    text = strings.kdomskiaRepository
                )
            }
        }
        NavExternalLink(
            uri = ExternalLink.pestoRepository
        ) {
            TextButton(
                onClick = navigate()
            ) {
                Text(
                    text = strings.pestoRepository
                )
            }
        }
        NavExternalLink(
            uri = ExternalLink.pestoFigmaPrototype
        ) {
            TextButton(
                onClick = navigate()
            ) {
                Text(
                    text = strings.pestoFigmaPrototype
                )
            }
        }
    }
}