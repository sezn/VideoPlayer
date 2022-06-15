package com.szn.videoplayer

data class Video(val uri: String,
                 val title: String)

val fakeVideo = Video("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", "Elephant")