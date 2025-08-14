package io.kdomskia.sample.pesto.di.module

import io.kdomskia.sample.pesto.data.http.provideHttpClient
import org.koin.dsl.module

val appModule = module {
    single { provideHttpClient() }
}