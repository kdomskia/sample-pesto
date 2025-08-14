package io.kdomskia.sample.pesto.recipe

import kotlinx.serialization.Serializable

@Serializable
enum class RecipeWarning {

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