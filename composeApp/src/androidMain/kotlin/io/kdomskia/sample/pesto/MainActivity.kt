package io.kdomskia.sample.pesto

import android.R.attr.text
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                val annotated = AnnotatedString.Builder().apply {
                    // Paragraph 1: Centered with line height
                    pushStyle(ParagraphStyle(textAlign = TextAlign.Center, lineHeight = 28.sp))
                    append("Centered Paragraph:\n")

                    pushStyle(SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold))
                    append("Bold Red")
                    pop()

                    append(" + ")

                    pushStyle(SpanStyle(fontStyle = FontStyle.Italic))
                    append("Italic")
                    pop()

                    append(" + ")

                    pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
                    append("Underline")
                    pop()

                    append(" + ")

                    pushStyle(SpanStyle(textDecoration = TextDecoration.LineThrough))
                    append("Strikethrough")
                    pop()

                    append(" + ")

                    pushStyle(SpanStyle(textDecoration = TextDecoration.Underline + TextDecoration.LineThrough))
                    append("Underline + Strikethrough")
                    pop()

                    append(" + ")

                    pushStyle(SpanStyle(fontSize = 24.sp, color = Color.Magenta))
                    append("Big Pink Text")
                    pop()
                    pop() // end ParagraphStyle

                    append("\n")

                    // Paragraph 2: Justified with a link
                    pushStyle(ParagraphStyle(textAlign = TextAlign.Justify))
                    append("This is a justified paragraph with a clickable ")

                    pushStyle(SpanStyle(color = Color.Blue))
                    pushStringAnnotation("URL", "https://example.com")
                    append("link")
                    pop() // URL
                    pop() // SpanStyle

                    append(" and normal text after.")
                    pop() // ParagraphStyle
                }.toAnnotatedString()


//                val annotated = AnnotatedString.Builder().apply {
//                    append("Normal, ")
//
//                    pushStyle(SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold))
//                    append("Bold Red")
//                    pop()
//
//                    append(", ")
//
//                    pushStyle(SpanStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline))
//                    append("Italic Underlined")
//                    pop()
//
//                    append(" and a ")
//
//                    pushStyle(SpanStyle(color = Color.Blue))
//                    pushStringAnnotation(tag = "URL", annotation = "https://example.com")
//                    append("link")
//                    pop()
//                    pop()
//
//                }.toAnnotatedString()
                LaunchedEffect(annotated) {
                    Log.d("--html-annotated", "${annotated.toHtml()}")
                }
                Text(
                    text = annotated
                )
            }
        }
    }

}


//fun AnnotatedString.toHtml(): String {
//    val sb = StringBuilder()
//    var lastIndex = 0
//
//    // Go through all styled ranges
//    spanStyles.forEach { range ->
//        // Add text before styled part
//        if (range.start > lastIndex) {
//            sb.append(text.substring(lastIndex, range.start))
//        }
//
//        val spanText = text.substring(range.start, range.end)
//
//        // Example: only handle color + bold
//        val style = buildString {
//            range.item.color.let { c ->
//                val hex = "#%02x%02x%02x".format(
//                    (c.red * 255).toInt(),
//                    (c.green * 255).toInt(),
//                    (c.blue * 255).toInt()
//                )
//                append("color:$hex;")
//            }
//            if (range.item.fontWeight != null) {
//                append("font-weight:bold;")
//            }
//        }
//
//        sb.append("<span style=\"$style\">$spanText</span>")
//        lastIndex = range.end
//    }
//
//    // Add remaining text
//    if (lastIndex < text.length) {
//        sb.append(text.substring(lastIndex))
//    }
//
//    return sb.toString()
//}

fun AnnotatedString.toHtml(): String {
    // If no paragraph styles exist, use the old span-based logic
    if (paragraphStyles.isEmpty()) {
        return buildSpansHtml(this, 0, text.length)
    }

    val sb = StringBuilder()
    var lastParagraphEnd = 0

    paragraphStyles.forEach { para ->
        // Add raw text before first paragraph (rare case)
        if (para.start > lastParagraphEnd) {
            sb.append(escapeHtml(text.substring(lastParagraphEnd, para.start)))
        }

        val css = paragraphStyleToCss(para.item)
        val content = buildSpansHtml(this, para.start, para.end)
        sb.append("<p style=\"$css\">$content</p>")

        lastParagraphEnd = para.end
    }

    // Add leftover text after last paragraph
    if (lastParagraphEnd < text.length) {
        sb.append(buildSpansHtml(this, lastParagraphEnd, text.length))
    }

    return sb.toString()
}

private fun escapeHtml(text: String): String =
    text.replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")
        .replace("\n", "<br>")

/** Build HTML for all spanStyles and URL annotations in a given range */
private fun buildSpansHtml(str: AnnotatedString, start: Int, end: Int): String {
    val sb = StringBuilder()
    var lastIndex = start

    str.spanStyles.forEach { span ->
        if (span.start in start until end || span.end in (start + 1)..end) {
            val safeStart = maxOf(span.start, start)
            val safeEnd = minOf(span.end, end)

            // Add plain text before span
            if (safeStart > lastIndex) {
                sb.append(escapeHtml(str.text.substring(lastIndex, safeStart)))
            }

            val spanText = escapeHtml(str.text.substring(safeStart, safeEnd))
            val css = spanStyleToCss(span.item)

            // Hyperlink?
            val url = str.getStringAnnotations("URL", safeStart, safeEnd).firstOrNull()?.item
            val openTag = if (url != null) "<a href=\"$url\"><span style=\"$css\">" else "<span style=\"$css\">"
            val closeTag = if (url != null) "</span></a>" else "</span>"

            sb.append(openTag).append(spanText).append(closeTag)

            lastIndex = safeEnd
        }
    }

    // Remaining plain text
    if (lastIndex < end) {
        sb.append(escapeHtml(str.text.substring(lastIndex, end)))
    }

    return sb.toString()
}

private fun spanStyleToCss(style: SpanStyle): String {
    val css = mutableListOf<String>()

    style.color.takeIf { it != Color.Unspecified }?.let { c ->
        css.add("color:${colorToHex(c)}")
    }
    style.fontSize.takeIf { it.isSp }?.let { fs ->
        css.add("font-size:${fs.value.roundToInt()}px")
    }
    style.fontWeight?.let {
        if (it >= FontWeight.Bold) css.add("font-weight:bold")
    }
    style.fontStyle?.let {
        if (it == FontStyle.Italic) css.add("font-style:italic")
    }
    style.textDecoration?.let {
        val decorations = mutableListOf<String>()
        if (TextDecoration.Underline in it) decorations.add("underline")
        if (TextDecoration.LineThrough in it) decorations.add("line-through")
        if (decorations.isNotEmpty()) css.add("text-decoration:${decorations.joinToString(" ")}")
    }

    return css.joinToString(";")
}

private fun colorToHex(c: Color): String =
    "#%02x%02x%02x".format(
        (c.red * 255).toInt(),
        (c.green * 255).toInt(),
        (c.blue * 255).toInt()
    )

private fun paragraphStyleToCss(style: ParagraphStyle): String {
    val css = mutableListOf<String>()

    style.textAlign?.let {
        val align = when (it) {
            TextAlign.Left -> "left"
            TextAlign.Right -> "right"
            TextAlign.Center -> "center"
            TextAlign.Justify -> "justify"
            else -> "start"
        }
        css.add("text-align:$align")
    }
    style.lineHeight.takeIf { it.isSp }?.let { lh ->
        css.add("line-height:${lh.value.roundToInt()}px")
    }

    return css.joinToString(";")
}