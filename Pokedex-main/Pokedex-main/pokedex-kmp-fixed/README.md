# Pokédex Multiplatform 🎮

Trabalho M1 — Disciplina: Programação para Dispositivos Móveis II  
Professor: Welington Gadelha  
Universidade do Vale do Itajaí — UNIVALI

## Integrantes
- [Aline Laura da Cruz]
- [Aline Alves Borges]
- [Leticia Caroline Erat]

## Sobre o Projeto

Aplicação Pokédex desenvolvida com **Kotlin Multiplatform (KMP)** e **Compose Multiplatform**, suportando Android e iOS a partir de uma base de código compartilhada.

## Funcionalidades

- **Home Screen** — Tela de entrada com visual impactante e botões de acesso rápido
- **Pokédex List** — Grade de Pokémons com busca em tempo real (Material3 SearchBar + LazyVerticalGrid)
- **Pokémon Details** — Detalhes completos com stats (LinearProgressIndicator), tipos com gradiente e botão "Adicionar ao Time"
- **Team Builder** — Tela para gerenciar o time (diferente por plataforma via `expect/actual`)

## Arquitetura

```
composeApp/
├── commonMain/       # Código compartilhado (UI, dados, navegação, ViewModel)
│   ├── data/
│   │   ├── model/   # Data classes (Pokemon, PokemonStats, PokemonType)
│   │   └── repository/ # PokemonRepository com 12 Pokémons mockados
│   ├── navigation/  # Rotas tipadas com @Serializable
│   ├── ui/
│   │   ├── components/ # PokemonCard, TypeChip, StatBar, etc.
│   │   ├── screens/ # Home, PokedexList, PokemonDetail, TeamBuilder (expect)
│   │   └── theme/   # MaterialTheme customizado
│   └── viewmodel/   # TeamViewModel com StateFlow
├── androidMain/     # TeamBuilderScreen.android.kt — Material Design 3
└── iosMain/         # TeamBuilderScreen.ios.kt — Apple HIG style
```

## Tecnologias

- Kotlin Multiplatform 2.0.21
- Compose Multiplatform 1.7.0
- Jetpack Navigation (rotas tipadas com `@Serializable`)
- Material Design 3
- ViewModel + StateFlow (gerenciamento de estado)
- Mecanismo `expect/actual` para diferenciação de plataformas

## Como Rodar

### Android
1. Abra o projeto no Android Studio (Koala ou superior)
2. Selecione o target `composeApp`
3. Execute em um emulador ou dispositivo Android (API 24+)

### iOS
1. Execute `./gradlew :composeApp:generateDummyFramework` (ou build KMP)
2. Abra `iosApp/iosApp.xcodeproj` no Xcode
3. Execute no simulador iOS

## Diferenciação Android x iOS (expect/actual)

| Aspecto | Android | iOS |
|---|---|---|
| Estilo | Material Design 3 (ElevatedCard, primaryContainer) | Apple HIG (fundo cinza F2F2F7, lista branca) |
| Remoção | Swipe-to-dismiss (SwipeToDismissBox) | Botão de delete vermelho (iOS red #FF3B30) |
| Header | Card com indicador de progresso animado | Large Title + segmented control |
| Tipografia | Material Typography | Pesos SemiBold/Bold estilo SF Pro |
