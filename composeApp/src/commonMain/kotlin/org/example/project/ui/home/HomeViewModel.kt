package org.example.project.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.Movie
import org.example.project.movies

class HomeViewMode: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(isLoading = true)
            delay(1000)
            state = UiState(isLoading = false, movies = movies)
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}