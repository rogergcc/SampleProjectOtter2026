package com.rogergcc.sampleprojectotter2026.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable


/**
 * Created on enero.
 * year 2026 .
 */
@Immutable // 🚀 Stability: El compilador sabe que esta clase no cambiará internamente
data class CryptoAsset(
    val id: String,
    val symbol: String,
    val price: Double,
    val isFavorite: Boolean
)

data class DashboardUiState(
    val assets: List<CryptoAsset> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)


// Usamos @Immutable para asegurar al compilador que, aunque sea una interfaz/módulo externo,
// los datos no cambiarán tras su creación.
@Immutable
data class AssetUiState(
    val id: String,
    val price: Double,
    val trend: List<Double> // Las listas estándar de Kotlin son inestables por defecto
)

@Composable
fun AssetRow(asset: AssetUiState) {
    // Al ser AssetUiState @Immutable, si el precio de "BTC" cambia pero el de "ETH" no,
    // el componente de "ETH" NO se recompone.
    // Implementación de la UI aquí...
    
}