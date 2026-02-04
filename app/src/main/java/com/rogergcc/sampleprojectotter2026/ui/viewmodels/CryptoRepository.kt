package com.rogergcc.sampleprojectotter2026.ui.viewmodels


class CryptoRepository()
{
    // Simulación de obtención de datos
    fun getAssets() = kotlinx.coroutines.flow.flowOf(
        listOf(
            com.rogergcc.sampleprojectotter2026.data.CryptoAsset("1", "BTC", 50000.0, false),
            com.rogergcc.sampleprojectotter2026.data.CryptoAsset("2", "ETH", 4000.0, true),
            com.rogergcc.sampleprojectotter2026.data.CryptoAsset("3", "XRP", 1.0, false)
        )
    )
}
