package io.github.hoaithu842.spotlight_kmp

import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoinAndroid(
    appComponent: AndroidApplicationComponent,
    appDeclaration: KoinAppDeclaration = {},
) {
    initKoin(
        listOf(module { single { appComponent } }),
        appDeclaration,
    )
}