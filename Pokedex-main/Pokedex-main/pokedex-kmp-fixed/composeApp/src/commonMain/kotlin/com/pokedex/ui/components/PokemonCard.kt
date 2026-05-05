package com.pokedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokedex.data.model.Pokemon
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun PokemonCard(pokemon: Pokemon, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.8f)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFF16213E)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(pokemonGradient(pokemon.types))
        ) {
            // Background circle decoration
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 20.dp, y = (-20).dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.1f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                PokemonNumberBadge(pokemon.id)

                // Pokemon image via Kamel (falls back to emoji)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    KamelImage(
                        resource = asyncPainterResource(pokemon.imageUrl),
                        contentDescription = pokemon.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(80.dp),
                        onLoading = {
                            Text(
                                text = getPokemonEmoji(pokemon.id),
                                fontSize = 48.sp
                            )
                        },
                        onFailure = {
                            Text(
                                text = getPokemonEmoji(pokemon.id),
                                fontSize = 48.sp
                            )
                        }
                    )
                }

                Column {
                    Text(
                        text = pokemon.name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        pokemon.types.take(2).forEach { type ->
                            TypeChip(type)
                        }
                    }
                }
            }
        }
    }
}

fun getPokemonEmoji(id: Int): String = when (id) {
    1 -> "🌿"
    4 -> "🔥"
    7 -> "💧"
    25 -> "⚡"
    39 -> "🎵"
    52 -> "🐱"
    94 -> "👻"
    130 -> "🐉"
    143 -> "😴"
    149 -> "🦋"
    150 -> "🧬"
    151 -> "✨"
    else -> "⭐"
}
