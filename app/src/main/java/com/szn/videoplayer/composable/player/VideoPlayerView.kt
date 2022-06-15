package com.szn.videoplayer.composable.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun VideoPlayerView() {

    Column(modifier = Modifier.fillMaxSize()) {
        VideoPlayer(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Black)
        )
    }
}