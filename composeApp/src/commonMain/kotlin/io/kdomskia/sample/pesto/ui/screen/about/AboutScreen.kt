package io.kdomskia.sample.pesto.ui.screen.about

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import io.kdomskia.compose.foundation.background
import io.kdomskia.compose.foundation.layout.Column
import io.kdomskia.compose.foundation.layout.ColumnScope
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.compose.foundation.layout.Spacer
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.height
import io.kdomskia.compose.foundation.layout.padding
import io.kdomskia.compose.foundation.layout.widthIn
import io.kdomskia.compose.material3.MaterialTheme
import io.kdomskia.compose.material3.Text
import io.kdomskia.compose.material3.TextButton
import io.kdomskia.compose.navigation.NavExternalLink
import io.kdomskia.compose.ui.Alignment
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.sample.pesto.data.external.ExternalLink
import io.kdomskia.sample.pesto.ui.component.AppBar
import io.kdomskia.sample.pesto.ui.component.AppBarIconType
import io.kdomskia.sample.pesto.ui.res.dimens
import io.kdomskia.sample.pesto.ui.res.strings

@Composable
fun AboutScreen(
    contentPadding: PaddingValues,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppBar(
            onIconClick = onClose,
            iconType = AppBarIconType.Close
        )
        AboutContent()

//        val annotated = AnnotatedString.Builder().apply {
//            // Paragraph 1: Centered with line height
//            pushStyle(ParagraphStyle(textAlign = TextAlign.Center, lineHeight = 28.sp))
//            append("Centered Paragraph:\n")
//
//            pushStyle(SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold))
//            append("Bold Red")
//            pop()
//
//            append(" + ")
//
//            pushStyle(SpanStyle(fontStyle = FontStyle.Italic))
//            append("Italic")
//            pop()
//
//            append(" + ")
//
//            pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
//            append("Underline")
//            pop()
//
//            append(" + ")
//
//            pushStyle(SpanStyle(textDecoration = TextDecoration.LineThrough))
//            append("Strikethrough")
//            pop()
//
//            append(" + ")
//
//            pushStyle(SpanStyle(textDecoration = TextDecoration.Underline + TextDecoration.LineThrough))
//            append("Underline + Strikethrough")
//            pop()
//
//            append(" + ")
//
//            pushStyle(SpanStyle(fontSize = 24.sp, color = Color.Magenta))
//            append("Big Pink Text")
//            pop()
//            pop() // end ParagraphStyle
//
//            append("\n")
//
//            // Paragraph 2: Justified with a link
//            pushStyle(ParagraphStyle(textAlign = TextAlign.Justify))
//            append("This is a justified paragraph with a clickable ")
//
//            pushStyle(SpanStyle(color = Color.Blue))
//            pushStringAnnotation("URL", "https://example.com")
//            append("link")
//            pop() // URL
//            pop() // SpanStyle
//
//            append(" and normal text after.")
//            pop() // ParagraphStyle
//        }.toAnnotatedString()


    }
}

@Composable
private fun ColumnScope.AboutContent() {
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
            .weight(1f)
            .padding(dimens.paddingLarge)
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