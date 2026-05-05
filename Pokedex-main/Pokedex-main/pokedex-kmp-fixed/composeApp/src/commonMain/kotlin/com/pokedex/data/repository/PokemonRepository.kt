package com.pokedex.data.repository

import com.pokedex.data.model.Pokemon
import com.pokedex.data.model.PokemonStats
import com.pokedex.data.model.PokemonType

object PokemonRepository {

    fun getPokemonList(): List<Pokemon> = listOf(
        Pokemon(
            id = 1, name = "Bulbasaur",
            types = listOf(PokemonType.GRASS, PokemonType.POISON),
            description = "Há uma semente de planta nas suas costas desde o dia em que nasceu. A semente cresce lentamente.",
            weight = 6.9, height = 0.7, category = "Seed", ability = "Overgrow",
            stats = PokemonStats(hp = 45, attack = 49, defense = 49, spAttack = 65, spDefense = 65, speed = 45),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        ),
        Pokemon(
            id = 4, name = "Charmander",
            types = listOf(PokemonType.FIRE),
            description = "Prefere lugares quentes. Diz-se que quando chove, vapor sobe da ponta da sua cauda.",
            weight = 8.5, height = 0.6, category = "Lizard", ability = "Blaze",
            stats = PokemonStats(hp = 39, attack = 52, defense = 43, spAttack = 60, spDefense = 50, speed = 65),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"
        ),
        Pokemon(
            id = 7, name = "Squirtle",
            types = listOf(PokemonType.WATER),
            description = "Após o nascimento, as costas vão endurecendo gradualmente e formam a casca. A casca spray de água com precisão.",
            weight = 9.0, height = 0.5, category = "Tiny Turtle", ability = "Torrent",
            stats = PokemonStats(hp = 44, attack = 48, defense = 65, spAttack = 50, spDefense = 64, speed = 43),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png"
        ),
        Pokemon(
            id = 25, name = "Pikachu",
            types = listOf(PokemonType.ELECTRIC),
            description = "Quando vários desses Pokémon se reúnem, sua eletricidade pode causar tempestades de raios.",
            weight = 6.0, height = 0.4, category = "Mouse", ability = "Static",
            stats = PokemonStats(hp = 35, attack = 55, defense = 40, spAttack = 50, spDefense = 50, speed = 90),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"
        ),
        Pokemon(
            id = 39, name = "Jigglypuff",
            types = listOf(PokemonType.NORMAL, PokemonType.FAIRY),
            description = "Quando encontra um oponente, ele infla e canta uma melodia sonífera que induz ao sono.",
            weight = 5.5, height = 0.5, category = "Balloon", ability = "Cute Charm",
            stats = PokemonStats(hp = 115, attack = 45, defense = 20, spAttack = 45, spDefense = 25, speed = 20),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/39.png"
        ),
        Pokemon(
            id = 52, name = "Meowth",
            types = listOf(PokemonType.NORMAL),
            description = "Adormece durante o dia e vaga à noite para recolher moedas brilhantes, que traz de volta para seu esconderijo.",
            weight = 4.2, height = 0.4, category = "Scratch Cat", ability = "Pickup",
            stats = PokemonStats(hp = 40, attack = 45, defense = 35, spAttack = 40, spDefense = 40, speed = 90),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/52.png"
        ),
        Pokemon(
            id = 94, name = "Gengar",
            types = listOf(PokemonType.GHOST, PokemonType.POISON),
            description = "Sob a luz da lua cheia, sua sombra toma forma monstruosa. Rouba o calor ao redor.",
            weight = 40.5, height = 1.5, category = "Shadow", ability = "Cursed Body",
            stats = PokemonStats(hp = 60, attack = 65, defense = 60, spAttack = 130, spDefense = 75, speed = 110),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/94.png"
        ),
        Pokemon(
            id = 130, name = "Gyarados",
            types = listOf(PokemonType.WATER, PokemonType.FLYING),
            description = "Extremamente violento. Diz-se que sua ferocidade nunca diminui mesmo depois de vencer todas as batalhas.",
            weight = 235.0, height = 6.5, category = "Atrocious", ability = "Intimidate",
            stats = PokemonStats(hp = 95, attack = 125, defense = 79, spAttack = 60, spDefense = 100, speed = 81),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/130.png"
        ),
        Pokemon(
            id = 143, name = "Snorlax",
            types = listOf(PokemonType.NORMAL),
            description = "Seu estômago pode digerir qualquer alimento, mesmo que esteja podre. Come 400kg por dia antes de dormir.",
            weight = 460.0, height = 2.1, category = "Sleeping", ability = "Immunity",
            stats = PokemonStats(hp = 160, attack = 110, defense = 65, spAttack = 65, spDefense = 110, speed = 30),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/143.png"
        ),
        Pokemon(
            id = 149, name = "Dragonite",
            types = listOf(PokemonType.DRAGON, PokemonType.FLYING),
            description = "Capaz de voar mais rápido que qualquer avião. Pode circundar o globo em apenas 16 horas.",
            weight = 210.0, height = 2.2, category = "Dragon", ability = "Inner Focus",
            stats = PokemonStats(hp = 91, attack = 134, defense = 95, spAttack = 100, spDefense = 100, speed = 80),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/149.png"
        ),
        Pokemon(
            id = 150, name = "Mewtwo",
            types = listOf(PokemonType.PSYCHIC),
            description = "Foi criado por um cientista após anos de horrível engenharia genética. Tem o coração mais cruel.",
            weight = 122.0, height = 2.0, category = "Genetic", ability = "Pressure",
            stats = PokemonStats(hp = 106, attack = 110, defense = 90, spAttack = 154, spDefense = 90, speed = 130),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/150.png"
        ),
        Pokemon(
            id = 151, name = "Mew",
            types = listOf(PokemonType.PSYCHIC),
            description = "Acredita-se que este Pokémon contenha o código genético de todos os outros Pokémon. Pode ser invisível.",
            weight = 4.0, height = 0.4, category = "New Species", ability = "Synchronize",
            stats = PokemonStats(hp = 100, attack = 100, defense = 100, spAttack = 100, spDefense = 100, speed = 100),
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/151.png"
        )
    )

    fun getPokemonById(id: Int): Pokemon? = getPokemonList().find { it.id == id }
}
