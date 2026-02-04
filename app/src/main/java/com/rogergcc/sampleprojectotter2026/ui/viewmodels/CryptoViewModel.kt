package com.rogergcc.sampleprojectotter2026.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogergcc.sampleprojectotter2026.data.DashboardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn


/**
 * Created on enero.
 * year 2026 .
 */
class CryptoViewModel(private val repository: CryptoRepository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    // 🚀 DerivedStateOf (equivalente en Flow): Combinamos estados eficientemente
    val uiState: StateFlow<DashboardUiState> = repository.getAssets()
        .combine(_searchQuery) { assets, query ->
            DashboardUiState(
                assets = assets.filter { it.symbol.contains(query, ignoreCase = true) },
                searchQuery = query
            )
        }
        .stateIn(
            scope = viewModelScope,
            // Evitamos init {}: Solo carga si la UI está escuchando
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DashboardUiState(isLoading = true)
        )

    fun onIntent(intent: DashboardIntent) {
        when (intent) {
            is DashboardIntent.UpdateSearch -> _searchQuery.value = intent.query
            is DashboardIntent.ToggleFavorite -> toggleFavorite(intent.id)
        }
    }

    private fun toggleFavorite(id: String) { /* Lógica de actualización */ }
}

sealed class DashboardIntent {
    data class UpdateSearch(val query: String) : DashboardIntent()
    data class ToggleFavorite(val id: String) : DashboardIntent()
}