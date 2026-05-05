package com.pokedex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokedex.ui.theme.PokedexGold
import com.pokedex.ui.theme.PokedexRed

@Composable
fun HomeScreen(
    onNavigateToPokedex: () -> Unit,
    onNavigateToTeam: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A1A2E),
                        Color(0xFF16213E),
                        Color(0xFF0F3460)
                    )
                )
            )
    ) {
        // Decorative circles
        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = (-80).dp, y = (-80).dp)
                .clip(androidx.compose.foundation.shape.CircleShape)
                .background(PokedexRed.copy(alpha = 0.15f))
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 60.dp, y = 60.dp)
                .clip(androidx.compose.foundation.shape.CircleShape)
                .background(PokedexGold.copy(alpha = 0.1f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo / Title area
            Text(
                text = "⭕",
                fontSize = 80.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Pokédex",
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                letterSpacing = 2.sp
            )

            Text(
                text = "MULTIPLATFORM",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = PokedexGold,
                letterSpacing = 6.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Bem-vindo, Treinador!",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Explore os Pokémons e monte seu time dos sonhos.",
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.5f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp, bottom = 48.dp)
            )

            // Quick access buttons
            HomeActionButton(
                text = "Ver Pokédex",
                icon = Icons.Default.List,
                gradient = Brush.horizontalGradient(
                    listOf(PokedexRed, Color(0xFFE53935))
                ),
                onClick = onNavigateToPokedex
            )

            Spacer(modifier = Modifier.height(16.dp))

            HomeActionButton(
                text = "Meu Time",
                icon = Icons.Default.Favorite,
                gradient = Brush.horizontalGradient(
                    listOf(Color(0xFF1565C0), Color(0xFF1976D2))
                ),
                onClick = onNavigateToTeam
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "KMP • Compose • Material3",
                fontSize = 11.sp,
                color = Color.White.copy(alpha = 0.3f),
                letterSpacing = 2.sp
            )
        }
    }
}

@Composable
private fun HomeActionButton(
    text: String,
    icon: ImageVector,
    gradient: Brush,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(gradient)
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
