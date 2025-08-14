package io.kdomskia.sample.pesto.ui.extension

import io.kdomskia.sample.pesto.Res
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeLabel
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeLabel.AnimalWelfare
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeLabel.EggFree
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeLabel.GlutenFree
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeLabel.LocallySourced
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeLabel.LowSodium
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeLabel.NoAddedSugar
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeLabel.NutFree
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeLabel.OrganicProduce
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeLabel.SustainableSeafood
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeLabel.Vegan
import io.kdomskia.sample.pesto.domain.model.recipe.RecipeLabel.Vegetarian
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

val RecipeLabel.text: String
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

val RecipeLabel.icon: DrawableResource
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