package com.pokedex.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PokedexRed = Color(0xFFCC0000)
val PokedexDarkRed = Color(0xFF990000)
val PokedexGold = Color(0xFFFFD700)
val PokedexBlue = Color(0xFF1565C0)
val SurfaceDark = Color(0xFF1A1A2E)
val SurfaceCard = Color(0xFF16213E)
val OnSurfaceLight = Color(0xFFF5F5F5)

private val DarkColorScheme = darkColorScheme(
    primary = PokedexRed,
    secondary = PokedexGold,
    tertiary = PokedexBlue,
    background = SurfaceDark,
    surface = SurfaceCard,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = OnSurfaceLight,
    onSurface = OnSurfaceLight,
)

private val LightColorScheme = lightColorScheme(
    primary = PokedexRed,
    secondary = PokedexGold,
    tertiary = PokedexBlue,
    background = Color(0xFFF5F5F5),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color(0xFF1A1A1A),
    onSurface = Color(0xFF1A1A1A),
)

@Composable
fun PokedexTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
