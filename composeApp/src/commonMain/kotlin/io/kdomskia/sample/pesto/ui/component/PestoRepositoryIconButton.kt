package io.kdomskia.sample.pesto.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import io.kdomskia.compose.foundation.layout.size
import io.kdomskia.compose.material3.Icon
import io.kdomskia.compose.material3.IconButton
import io.kdomskia.compose.navigation.NavExternalLink
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.resource.img
import io.kdomskia.sample.pesto.Res
import io.kdomskia.sample.pesto.data.external.ExternalLink
import io.kdomskia.sample.pesto.ic_github

@Composable
fun PestoRepositoryIconButton() {
    NavExternalLink(
        uri = ExternalLink.pestoRepository
    ) {
        IconButton(
            onClick = navigate()
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                img = Res.drawable.ic_github.img,
                contentDescription = ""
            )
        }
    }
}