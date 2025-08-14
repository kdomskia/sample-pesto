package io.kdomskia.sample.pesto.di

import io.kdomskia.sample.pesto.di.module.appModule
import io.kdomskia.sample.pesto.di.module.recipeModule
import org.koin.dsl.KoinAppDeclaration

val appDi: KoinAppDeclaration = {
    modules(
        appModule,
        recipeModule
    )
}