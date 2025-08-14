package io.kdomskia.sample.pesto.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.kdomskia.compose.foundation.Image
import io.kdomskia.compose.foundation.layout.Arrangement
import io.kdomskia.compose.foundation.layout.Column
import io.kdomskia.compose.foundation.layout.Spacer
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.height
import io.kdomskia.compose.foundation.layout.size
import io.kdomskia.compose.material3.MaterialTheme
import io.kdomskia.compose.material3.Text
import io.kdomskia.compose.ui.Alignment
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.resource.ImageResource
import io.kdomskia.compose.ui.resource.img
import io.kdomskia.sample.pesto.Res
import io.kdomskia.sample.pesto.ic_error
import io.kdomskia.sample.pesto.ic_work_in_progress
import io.kdomskia.sample.pesto.ui.res.dimens
import io.kdomskia.sample.pesto.ui.res.strings

@Composable
fun UnavailableContent(
    modifier: Modifier = Modifier,
    type: UnavailableContentType
) {
    val style = type.style
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(
                width = 200.dp,
                height = 180.dp
            ),
            img = style.icon,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(dimens.paddingLarge))
        Text(
            text = style.title,
            color = style.titleTextColor,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(dimens.paddingExtraSmall))
        Text(
            text = style.description,
            color = style.descriptionTextColor,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

enum class UnavailableContentType {

    Error,

    WorkInProgress

}

private val UnavailableContentType.style: UnavailableContentStyle
    @Composable
    get() = when (this) {
        UnavailableContentType.Error -> UnavailableContentStyle(
            icon = Res.drawable.ic_error.img,
            title = strings.genericErrorTitle,
            titleTextColor = MaterialTheme.colorScheme.error,
            description = strings.genericErrorMessage,
            descriptionTextColor = MaterialTheme.colorScheme.error
        )

        UnavailableContentType.WorkInProgress -> UnavailableContentStyle(
            icon = Res.drawable.ic_work_in_progress.img,
            title = strings.workInProgressTitle,
            titleTextColor = MaterialTheme.colorScheme.primary,
            description = strings.workInProgressMessage,
            descriptionTextColor = MaterialTheme.colorScheme.primary
        )
    }

@Immutable
private data class UnavailableContentStyle(
    val icon: ImageResource,
    val title: String,
    val titleTextColor: Color,
    val description: String,
    val descriptionTextColor: Color
)