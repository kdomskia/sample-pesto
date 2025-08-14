package io.kdomskia.sample.pesto.ui.component.recipe

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.kdomskia.compose.foundation.clickable
import io.kdomskia.compose.foundation.layout.Box
import io.kdomskia.compose.foundation.layout.BoxWithConstraints
import io.kdomskia.compose.foundation.layout.Column
import io.kdomskia.compose.foundation.layout.Spacer
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.height
import io.kdomskia.compose.foundation.layout.size
import io.kdomskia.compose.foundation.layout.widthIn
import io.kdomskia.compose.material3.MaterialTheme
import io.kdomskia.compose.material3.Text
import io.kdomskia.compose.navigation.NavLink
import io.kdomskia.compose.ui.Alignment
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.draw.clip
import io.kdomskia.compose.ui.semantics.Role
import io.kdomskia.sample.pesto.domain.model.recipe.Recipe
import io.kdomskia.sample.pesto.ui.navigation.LocalNavHostController
import io.kdomskia.sample.pesto.ui.navigation.destination.AppDestination
import io.kdomskia.sample.pesto.ui.res.dimens
import io.kdomskia.sample.pesto.ui.util.isWindowWidthExpanded

@Composable
fun RecipeItem(
    recipe: Recipe
) {
    val navController = LocalNavHostController.current

    if (navController != null) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            NavLink(
                modifier = Modifier.widthIn(
                    max = 550.dp
                ),
                navController = navController,
                route = AppDestination.RecipeDetail(recipeId = recipe.id)
            ) {
                val shape = MaterialTheme.shapes.extraLarge
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape)
                        .clickable(
                            role = Role.Button,
                            onClick = navigate()
                        )
                ) {
                    BoxWithConstraints(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        val modifier = if (isWindowWidthExpanded) {
                            Modifier
                                .fillMaxWidth()
                                .height(240.dp)
                        } else {
                            val imageWidth = this.maxWidth
                            val imageHeight = imageWidth * dimens.imageAspectRatio
                            Modifier.size(
                                width = imageWidth,
                                height = imageHeight
                            )
                        }

                        RecipeImage(
                            modifier = modifier,
                            imgUrl = recipe.imgUrl.orEmpty(),
                            shape = MaterialTheme.shapes.extraLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(dimens.paddingSmall))
                    Text(
                        text = recipe.title,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.tertiary,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.displaySmall
                    )
                    Spacer(modifier = Modifier.height(dimens.paddingExtraSmall))
                    Text(
                        text = recipe.description,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}