package io.github.hoaithu842.spotlight_kmp

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    additionalModules: List<Module> = listOf(),
    appDeclaration: KoinAppDeclaration = {},
) {
    startKoin {
        appDeclaration()
        modules(
            additionalModules + listOf(
                commonModule,
                platformModule,
            )
        )
    }
}