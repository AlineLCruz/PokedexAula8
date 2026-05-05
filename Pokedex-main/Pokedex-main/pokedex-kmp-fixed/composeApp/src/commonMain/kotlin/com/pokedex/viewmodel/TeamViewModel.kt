package com.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import com.pokedex.data.model.Pokemon
import com.pokedex.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TeamViewModel : ViewModel() {

    private val _team = MutableStateFlow<List<Pokemon>>(emptyList())
    val team: StateFlow<List<Pokemon>> = _team.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _allPokemons = MutableStateFlow(PokemonRepository.getPokemonList())
    val allPokemons: StateFlow<List<Pokemon>> = _allPokemons.asStateFlow()

    fun addToTeam(pokemon: Pokemon) {
        if (_team.value.size < 6 && _team.value.none { it.id == pokemon.id }) {
            _team.update { current -> current + pokemon }
        }
    }

    fun removeFromTeam(pokemon: Pokemon) {
        _team.update { current -> current.filter { it.id != pokemon.id } }
    }

    fun isInTeam(pokemonId: Int): Boolean = _team.value.any { it.id == pokemonId }

    fun updateSearch(query: String) {
        _searchQuery.update { query }
        _allPokemons.update {
            if (query.isBlank()) {
                PokemonRepository.getPokemonList()
            } else {
                PokemonRepository.getPokemonList().filter {
                    it.name.contains(query, ignoreCase = true) ||
                    it.types.any { t -> t.displayName.contains(query, ignoreCase = true) }
                }
            }
        }
    }

    fun getPokemonById(id: Int) = PokemonRepository.getPokemonById(id)
}
