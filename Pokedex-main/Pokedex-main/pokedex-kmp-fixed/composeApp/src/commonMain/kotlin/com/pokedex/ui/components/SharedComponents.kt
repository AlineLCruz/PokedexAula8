package com.pokedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokedex.data.model.PokemonType

@Composable
fun TypeChip(type: PokemonType) {
    val color = Color(type.colorHex)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(alpha = 0.85f))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = type.displayName,
            color = Color.White,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun StatBar(label: String, value: Int, maxValue: Int = 255) {
    val color = when {
        value < 50 -> Color(0xFFEF5350)
        value < 80 -> Color(0xFFFF9800)
        value < 110 -> Color(0xFF66BB6A)
        else -> Color(0xFF42A5F5)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, fontSize = 12.sp, color = Color.Gray)
            Text(
                text = value.toString(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { value / maxValue.toFloat() },
            modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
            color = color,
            trackColor = color.copy(alpha = 0.2f)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun PokemonNumberBadge(number: Int) {
    Text(
        text = "#${number.toString().padStart(3, '0')}",
        color = Color.White.copy(alpha = 0.6f),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    )
}

fun pokemonGradient(types: List<PokemonType>): Brush {
    val colors = if (types.size >= 2) {
        listOf(
            Color(types[0].colorHex).copy(alpha = 0.8f),
            Color(types[1].colorHex).copy(alpha = 0.8f)
        )
    } else {
        listOf(
            Color(types[0].colorHex).copy(alpha = 0.8f),
            Color(types[0].colorHex).copy(alpha = 0.4f)
        )
    }
    return Brush.linearGradient(colors)
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}
