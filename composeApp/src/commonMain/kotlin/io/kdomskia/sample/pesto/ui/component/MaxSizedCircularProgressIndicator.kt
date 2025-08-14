package io.kdomskia.sample.pesto.ui.component

import androidx.compose.runtime.Composable
import io.kdomskia.compose.foundation.layout.Box
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.fillViewportHeight
import io.kdomskia.compose.material3.CircularProgressIndicator
import io.kdomskia.compose.ui.Alignment
import io.kdomskia.compose.ui.Modifier

@Composable
fun MaxSizedCircularProgressIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .fillViewportHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}