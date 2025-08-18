package io.kdomskia.sample.pesto.data.resource.recipe

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("/recipe")
class RecipeResource() {

    @Resource("{id}")
    class Get(
        val parent: RecipeResource = RecipeResource(),
        val id: String
    )

    @Resource("favorites")
    class Favorites(
        val parent: RecipeResource = RecipeResource()
    )

}