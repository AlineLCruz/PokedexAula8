package com.pokedex

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pokedex.navigation.Screen
import com.pokedex.ui.screens.*
import com.pokedex.ui.theme.PokedexTheme
import com.pokedex.viewmodel.TeamViewModel
import com.pokedex.viewmodel.PostViewModel
import com.pokedex.ui.screens.PostScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(postViewModel: PostViewModel)() {
    PokedexTheme {
        val navController = rememberNavController()
        val teamViewModel: TeamViewModel = viewModel { TeamViewModel() }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val showBottomBar = currentDestination?.let {
            it.hasRoute(Screen.PokedexList::class) || it.hasRoute(Screen.TeamBuilder::class)
        } ?: false

        val topBarTitle = when {
            currentDestination?.hasRoute(Screen.Home::class) == true -> "Pokédex"
            currentDestination?.hasRoute(Screen.PokedexList::class) == true -> "Pokédex"
            currentDestination?.hasRoute(Screen.TeamBuilder::class) == true -> "Meu Time"
            else -> "Detalhes"
        }

        val showTopBar = currentDestination?.hasRoute(Screen.Home::class) != true

        Scaffold(
            topBar = {
                if (showTopBar) {
                    TopAppBar(
                        title = { Text(topBarTitle) },
                        navigationIcon = {
                            val isDetailScreen = currentDestination?.hasRoute(Screen.PokemonDetail::class) == true
                            if (isDetailScreen) {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Text("←", style = MaterialTheme.typography.titleLarge)
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    )
                }
            },
            bottomBar = {
                if (showBottomBar) {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface
                    ) {
                        val isPokedexSelected = currentDestination?.hasRoute(Screen.PokedexList::class) == true
                        val isTeamSelected = currentDestination?.hasRoute(Screen.TeamBuilder::class) == true

                        NavigationBarItem(
                            selected = isPokedexSelected,
                            onClick = {
                                navController.navigate(Screen.PokedexList) {
                                    popUpTo(Screen.PokedexList) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(Icons.Default.List, contentDescription = "Pokédex") },
                            label = { Text("Pokédex") }
                        )

                        NavigationBarItem(
                            selected = isTeamSelected,
                            onClick = {
                                navController.navigate(Screen.TeamBuilder) {
                                    popUpTo(Screen.PokedexList) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(Icons.Default.Favorite, contentDescription = "Meu Time") },
                            label = { Text("Meu Time") }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<Screen.Home> {
                    HomeScreen(
                        onNavigateToPokedex = { navController.navigate(Screen.PokedexList) },
                        onNavigateToTeam = { navController.navigate(Screen.TeamBuilder) }
                    )
                }
                composable<Screen.PokedexList> {
                    PokedexListScreen(
                        viewModel = teamViewModel,
                        onPokemonClick = { id ->
                            navController.navigate(Screen.PokemonDetail(id))
                        }

                    )
                }
                composable<Screen.PokemonDetail> { backStackEntry ->
                    val route = backStackEntry.toRoute<Screen.PokemonDetail>()
                    PokemonDetailScreen(
                        pokemonId = route.pokemonId,
                        viewModel = teamViewModel
                    )
                }
                composable<Screen.TeamBuilder> {
                    TeamBuilderScreen(
                        viewModel = teamViewModel,
                        onPokemonClick = { id ->
                            navController.navigate(Screen.PokemonDetail(id))
                        }

                    )
                }
                composable("posts") {
                    PostScreen(viewModel = postViewModel)
                }
            }
        }
    }
}
