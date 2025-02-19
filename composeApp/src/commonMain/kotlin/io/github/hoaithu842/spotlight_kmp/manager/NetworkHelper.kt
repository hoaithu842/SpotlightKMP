package io.github.hoaithu842.spotlight_kmp.manager

interface NetworkHelper {
    fun registerListener(onNetworkAvailable: () -> Unit, onNetworkLost: () -> Unit)
    fun unregisterListener()
}