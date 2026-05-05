package com.pokedex.viewmodel

import com.pokedex.data.model.Post
import com.pokedex.data.remote.MappedException
import com.pokedex.data.repository.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PostUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val endReached: Boolean = false,
    val currentPage: Int = 1,
    val userIdFilter: Int? = null
)

class PostViewModel(private val repository: PostRepository) {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private val limit = 10

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    init { loadPosts(reset = true) }

    fun loadPosts(reset: Boolean = false) {
        val state = _uiState.value
        if (state.isLoading || state.isLoadingMore) return
        if (!reset && state.endReached) return

        val page = if (reset) 1 else state.currentPage

        scope.launch {
            _uiState.update {
                if (reset) it.copy(isLoading = true, posts = emptyList(), error = null)
                else it.copy(isLoadingMore = true, error = null)
            }

            repository.getPosts(page, limit, _uiState.value.userIdFilter)
                .fold(
                    onSuccess = { newPosts ->
                        _uiState.update { s ->
                            s.copy(
                                posts = if (reset) newPosts else s.posts + newPosts,
                                isLoading = false,
                                isLoadingMore = false,
                                currentPage = page + 1,
                                endReached = newPosts.size < limit,
                                error = null
                            )
                        }
                    },
                    onFailure = { e ->
                        val message = if (e is MappedException) {
                            e.error.toMessage()
                        } else {
                            "Erro inesperado. Tente novamente."
                        }
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isLoadingMore = false,
                                error = message
                            )
                        }
                    }
                )
        }
    }

    fun loadMore() = loadPosts(reset = false)

    fun onUserIdChange(input: String) {
        _uiState.update { it.copy(userIdFilter = input.toIntOrNull(), currentPage = 1) }
        loadPosts(reset = true)
    }

    fun retry() = loadPosts(reset = true)
}