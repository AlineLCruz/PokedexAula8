package com.pokedex.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object Home : Screen()

    @Serializable
    object PokedexList : Screen()

    @Serializable
    data class PokemonDetail(val pokemonId: Int) : Screen()

    @Serializable
    object TeamBuilder : Screen()
}
