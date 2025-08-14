package io.kdomskia.sample.pesto.ui.component

import androidx.compose.runtime.Composable
import io.kdomskia.compose.foundation.background
import io.kdomskia.compose.foundation.layout.Column
import io.kdomskia.compose.foundation.layout.Spacer
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.fillViewportHeight
import io.kdomskia.compose.foundation.layout.height
import io.kdomskia.compose.material3.MaterialTheme
import io.kdomskia.compose.material3.Text
import io.kdomskia.compose.material3.TextButton
import io.kdomskia.compose.ui.Alignment
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.sample.pesto.ui.res.dimens
import io.kdomskia.sample.pesto.ui.res.strings

@Composable
fun ErrorStateContainer(
    modifier: Modifier = Modifier,
    onTryAgain: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillViewportHeight()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.8f))
        UnavailableContent(
            type = UnavailableContentType.Error
        )
        Spacer(modifier = Modifier.height(dimens.paddingLarge))
        TextButton(
            onClick = onTryAgain
        ) {
            Text(
                text = strings.tryAgain
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}