package com.pokedex.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
    val description: String,
    val weight: Double,   // kg
    val height: Double,   // m
    val category: String,
    val ability: String,
    val stats: PokemonStats,
    val imageUrl: String  // usaremos emoji ou placeholder
)

@Serializable
data class PokemonStats(
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val spAttack: Int,
    val spDefense: Int,
    val speed: Int
)

enum class PokemonType(val displayName: String, val colorHex: Long) {
    FIRE("Fogo", 0xFFFF6B35),
    WATER("Água", 0xFF4FC3F7),
    GRASS("Grama", 0xFF66BB6A),
    POISON("Veneno", 0xFFAB47BC),
    FLYING("Voador", 0xFF90CAF9),
    ELECTRIC("Elétrico", 0xFFFFEE58),
    PSYCHIC("Psíquico", 0xFFEC407A),
    ICE("Gelo", 0xFF80DEEA),
    DRAGON("Dragão", 0xFF7986CB),
    DARK("Sombrio", 0xFF78909C),
    FIGHTING("Lutador", 0xFFEF5350),
    ROCK("Pedra", 0xFFBCAAA4),
    GHOST("Fantasma", 0xFF7E57C2),
    BUG("Inseto", 0xFFD4E157),
    STEEL("Aço", 0xFFB0BEC5),
    NORMAL("Normal", 0xFFBDBDBD),
    GROUND("Terra", 0xFFFFCC02),
    FAIRY("Fada", 0xFFF48FB1)
}
