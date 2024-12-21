package org.example.project.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.Movie
import org.example.project.data.MovieService
import org.example.project.data.RemoteMovie
import org.example.project.movies

class HomeViewMode(
    private val moviesService: MovieService,
): ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(isLoading = true)
            state = UiState(
                isLoading = false,
                movies = UiState(
                    isLoading = false,
                    movies = moviesService.fetchPopularMovies().results.map { it.toDomainMovie() }
                )
            )
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}

private fun RemoteMovie.toDomainMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        poster = posterPath
    )
}