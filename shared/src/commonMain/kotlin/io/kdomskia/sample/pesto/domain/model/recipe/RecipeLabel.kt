package io.kdomskia.sample.pesto.domain.model.recipe

import kotlinx.serialization.Serializable

@Serializable
enum class RecipeLabel {

    GlutenFree,
    EggFree,
    NutFree,
    Vegan,
    Vegetarian,
    OrganicProduce,
    SustainableSeafood,
    LocallySourced,
    NoAddedSugar,
    LowSodium,
    AnimalWelfare

}