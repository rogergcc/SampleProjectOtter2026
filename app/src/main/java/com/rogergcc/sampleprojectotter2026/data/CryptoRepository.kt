package com.rogergcc.sampleprojectotter2026.data

import kotlinx.coroutines.flow.flowOf

class CryptoRepository()
{
    // Simulación de obtención de datos
    fun getAssets() = flowOf(
        listOf(
            CryptoAsset("1", "BTC", 50000.0, false),
            CryptoAsset("2", "ETH", 4000.0, true),
            CryptoAsset("3", "XRP", 1.0, false)
        )
    )
}