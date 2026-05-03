package es.rlujancreations.minesweeper.ui.core

import kotlinx.serialization.Serializable


@Serializable
data class GameRoute(val level: String)

@Serializable
data object HomeRoute

@Serializable
data object HelpRoute
