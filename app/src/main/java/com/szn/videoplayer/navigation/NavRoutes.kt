package com.szn.videoplayer.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Player : NavRoutes("player")
    object Detail : NavRoutes("detail/{articleId}")
}