package com.pokedex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokedex.ui.components.TypeChip
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import androidx.compose.ui.layout.ContentScale
import com.pokedex.ui.components.getPokemonEmoji
import com.pokedex.viewmodel.TeamViewModel

// actual para iOS: estilo Apple Human Interface Guidelines
@Composable
actual fun TeamBuilderScreen(
    viewModel: TeamViewModel,
    onPokemonClick: (Int) -> Unit
) {
    val team by viewModel.team.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7)) // iOS system background
    ) {
        // iOS-style large title section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Favoritos",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // iOS-style segmented control for slots
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFE5E5EA))
                    .padding(3.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                repeat(6) { index ->
                    val filled = index < team.size
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (filled) Color(0xFF007AFF) else Color.Transparent)
                            .padding(vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (filled) "✓" else "${index + 1}",
                            color = if (filled) Color.White else Color(0xFF8E8E93),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        HorizontalDivider(color = Color(0xFFE5E5EA), thickness = 0.5.dp)

        if (team.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // iOS empty state style
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color(0xFFE5E5EA)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("💔", fontSize = 48.sp)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Nenhum Favorito",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Clique no ícone de coração dos\nPokémons favoritos e eles\naparecerão aqui.",
                        color = Color(0xFF8E8E93),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(team, key = { it.id }) { pokemon ->
                    // iOS-style grouped list row
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .clickable { onPokemonClick(pokemon.id) }
                                .padding(horizontal = 20.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            // iOS-style icon
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(Color(0xFFF2F2F7)),
                                contentAlignment = Alignment.Center
                            ) {
                                KamelImage(
                                    resource = asyncPainterResource(pokemon.imageUrl),
                                    contentDescription = pokemon.name,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.size(44.dp),
                                    onLoading = { Text(getPokemonEmoji(pokemon.id), fontSize = 26.sp) },
                                    onFailure = { Text(getPokemonEmoji(pokemon.id), fontSize = 26.sp) }
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = pokemon.name,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "#${pokemon.id.toString().padStart(3, '0')} • ${
                                        pokemon.types.joinToString(" / ") { it.displayName }
                                    }",
                                    color = Color(0xFF8E8E93),
                                    fontSize = 13.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    pokemon.types.forEach { TypeChip(it) }
                                }
                            }

                            // iOS chevron + delete
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "HP ${pokemon.stats.hp}",
                                    color = Color(0xFF8E8E93),
                                    fontSize = 13.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                IconButton(
                                    onClick = { viewModel.removeFromTeam(pokemon) },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Remover",
                                        tint = Color(0xFFFF3B30), // iOS red
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                        HorizontalDivider(
                            modifier = Modifier.padding(start = 90.dp),
                            color = Color(0xFFE5E5EA),
                            thickness = 0.5.dp
                        )
                    }
                }
            }
        }
    }
}
