package io.kdomskia.sample.pesto.ui.extension

import io.kdomskia.sample.pesto.Res
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning.AnimalWelfare
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning.EggFree
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning.GlutenFree
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning.LocallySourced
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning.LowSodium
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning.NoAddedSugar
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning.NutFree
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning.OrganicProduce
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning.SustainableSeafood
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning.Vegan
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeWarning.Vegetarian
import io.kdomskia.sample.pesto.ic_animal_welfare
import io.kdomskia.sample.pesto.ic_egg_free
import io.kdomskia.sample.pesto.ic_gluten_free
import io.kdomskia.sample.pesto.ic_locally_sourced
import io.kdomskia.sample.pesto.ic_low_sodium
import io.kdomskia.sample.pesto.ic_no_added_sugar
import io.kdomskia.sample.pesto.ic_nut_free
import io.kdomskia.sample.pesto.ic_organic_produce
import io.kdomskia.sample.pesto.ic_sustainable_seafood
import io.kdomskia.sample.pesto.ic_vegan
import io.kdomskia.sample.pesto.ic_vegetarian
import io.kdomskia.sample.pesto.ui.res.strings
import org.jetbrains.compose.resources.DrawableResource

val RecipeWarning.label: String
    get() = when (this) {
        GlutenFree -> strings.glutenFree
        EggFree -> strings.eggFree
        NutFree -> strings.nutFree
        Vegan -> strings.vegan
        Vegetarian -> strings.vegetarian
        OrganicProduce -> strings.organicProduce
        SustainableSeafood -> strings.sustainableSeafood
        LocallySourced -> strings.locallySourced
        NoAddedSugar -> strings.noAddedSugar
        LowSodium -> strings.lowSodium
        AnimalWelfare -> strings.animalWelfare
    }

val RecipeWarning.icon: DrawableResource
    get() = when (this) {
        GlutenFree -> Res.drawable.ic_gluten_free
        EggFree -> Res.drawable.ic_egg_free
        NutFree -> Res.drawable.ic_nut_free
        Vegan -> Res.drawable.ic_vegan
        Vegetarian -> Res.drawable.ic_vegetarian
        OrganicProduce -> Res.drawable.ic_organic_produce
        SustainableSeafood -> Res.drawable.ic_sustainable_seafood
        LocallySourced -> Res.drawable.ic_locally_sourced
        NoAddedSugar -> Res.drawable.ic_no_added_sugar
        LowSodium -> Res.drawable.ic_low_sodium
        AnimalWelfare -> Res.drawable.ic_animal_welfare
    }