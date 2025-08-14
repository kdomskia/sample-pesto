package io.kdomskia.sample.pesto.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import io.kdomskia.compose.foundation.Image
import io.kdomskia.compose.foundation.layout.WindowInsets
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.height
import io.kdomskia.compose.material3.IconButton
import io.kdomskia.compose.material3.TopAppBar
import io.kdomskia.compose.ui.Alignment
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.resource.img
import io.kdomskia.sample.pesto.Res
import io.kdomskia.sample.pesto.ic_close
import io.kdomskia.sample.pesto.ic_menu
import io.kdomskia.sample.pesto.ui.res.dimens
import io.kdomskia.sample.pesto.ui.util.isWindowWidthExpanded
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    onIconClick: () -> Unit,
    iconType: AppBarIconType,
    logoHeight: Dp = dimens.appBarLogoHeight,
    logoPadding: Dp = dimens.paddingMedium
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            AppLogo(
                modifier = Modifier.height(logoHeight)
            )
        },
        navigationIcon = {
            if (isWindowWidthExpanded.not()) {
                IconButton(
                    onClick = onIconClick
                ) {
                    Image(
                        img = iconType.getIconResource().img,
                        contentDescription = null
                    )
                }
            }
        },
        titleHorizontalAlignment = Alignment.CenterHorizontally,
        expandedHeight = logoHeight + logoPadding,
        windowInsets = WindowInsets()
    )
}

private fun AppBarIconType.getIconResource(): DrawableResource = when (this) {
    AppBarIconType.Menu -> Res.drawable.ic_menu
    AppBarIconType.Close -> Res.drawable.ic_close
}

enum class AppBarIconType {

    Menu,

    Close

}