package io.github.hoaithu842.spotlight_kmp

import org.koin.dsl.module

fun initKoinIos(appComponent: IosApplicationComponent) {
    initKoin(
        listOf(module { single { appComponent } })
    )
}