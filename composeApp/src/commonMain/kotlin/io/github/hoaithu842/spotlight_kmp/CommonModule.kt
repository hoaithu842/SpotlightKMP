package io.github.hoaithu842.spotlight_kmp

import io.github.hoaithu842.spotlight_kmp.manager.NetworkListener
import org.koin.dsl.module

val commonModule = module {
    single { NetworkListener(get()) }
}