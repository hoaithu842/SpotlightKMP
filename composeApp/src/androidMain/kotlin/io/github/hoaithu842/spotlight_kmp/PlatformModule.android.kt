package io.github.hoaithu842.spotlight_kmp

import io.github.hoaithu842.spotlight_kmp.manager.AndroidNetworkHelper
import io.github.hoaithu842.spotlight_kmp.manager.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single<NetworkHelper> { AndroidNetworkHelper(androidContext()) }
}