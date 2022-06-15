package com.szn.videoplayer.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.szn.app.ui.compose.AppTheme
import com.szn.videoplayer.composable.player.VideoPlayer

@Composable
fun HomeView(navController: NavController) = AppTheme  {
    Column(modifier = Modifier.fillMaxSize()) {
        VideoPlayer(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Black)
        )
    }
}
