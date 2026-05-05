package com.pokedex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokedex.ui.components.*
import com.pokedex.viewmodel.TeamViewModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun PokemonDetailScreen(
    pokemonId: Int,
    viewModel: TeamViewModel
) {
    val pokemon = remember(pokemonId) { viewModel.getPokemonById(pokemonId) }
    val team by viewModel.team.collectAsState()
    val inTeam = team.any { it.id == pokemonId }

    if (pokemon == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Pokémon não encontrado", color = Color.Gray)
        }
        return
    }

    val gradient = pokemonGradient(pokemon.types)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // Hero header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(gradient)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = pokemon.name,
                            fontSize = 34.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            pokemon.types.forEach { TypeChip(it) }
                        }
                    }
                    PokemonNumberBadge(pokemon.id)
                }

                // Real Pokémon image
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    KamelImage(
                        resource = asyncPainterResource(pokemon.imageUrl),
                        contentDescription = pokemon.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(160.dp),
                        onLoading = { Text(getPokemonEmoji(pokemon.id), fontSize = 100.sp) },
                        onFailure = { Text(getPokemonEmoji(pokemon.id), fontSize = 100.sp) }
                    )
                }
            }
        }

        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            SectionTitle("Sobre")
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = pokemon.description,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            SectionTitle("Dados")
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    InfoCard("⚖️ Peso", "${pokemon.weight} kg", Modifier.weight(1f))
                    InfoCard("📏 Altura", "${pokemon.height} m", Modifier.weight(1f))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    InfoCard("📦 Categoria", pokemon.category, Modifier.weight(1f))
                    InfoCard("✨ Habilidade", pokemon.ability, Modifier.weight(1f))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            SectionTitle("Atributos Base")
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    StatBar("HP", pokemon.stats.hp)
                    StatBar("Ataque", pokemon.stats.attack)
                    StatBar("Defesa", pokemon.stats.defense)
                    StatBar("Atq. Especial", pokemon.stats.spAttack)
                    StatBar("Def. Especial", pokemon.stats.spDefense)
                    StatBar("Velocidade", pokemon.stats.speed)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            val teamSize = team.size
            Button(
                onClick = {
                    if (inTeam) viewModel.removeFromTeam(pokemon)
                    else viewModel.addToTeam(pokemon)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (inTeam) Color(0xFF43A047) else MaterialTheme.colorScheme.primary
                ),
                enabled = inTeam || teamSize < 6
            ) {
                Icon(
                    imageVector = if (inTeam) Icons.Default.Check else Icons.Default.Add,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when {
                        inTeam -> "No Time! Toque para Remover"
                        teamSize >= 6 -> "Time Cheio (6/6)"
                        else -> "Adicionar ao Time ($teamSize/6)"
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun InfoCard(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
