package com.pokedex.ui.screens

import androidx.compose.runtime.Composable
import com.pokedex.viewmodel.TeamViewModel

// expect declaration: cada plataforma implementa sua própria versão
@Composable
expect fun TeamBuilderScreen(
    viewModel: TeamViewModel,
    onPokemonClick: (Int) -> Unit
)
