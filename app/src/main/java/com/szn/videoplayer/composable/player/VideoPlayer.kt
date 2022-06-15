package com.szn.videoplayer.composable.player

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.metadata.Metadata
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.szn.videoplayer.fakeVideo


@Composable
fun VideoPlayer(modifier: Modifier = Modifier) {

    val FORWARD_MS = 10000L
    val context = LocalContext.current
    val videoTitle = remember {
        mutableStateOf(fakeVideo.title)
    }
    val mediaItem = MediaItem.Builder()
        .setUri(fakeVideo.uri)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setDisplayTitle(fakeVideo.title)
                .build()
    ).build()

    // create our player
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setSeekBackIncrementMs(FORWARD_MS)
            .setSeekForwardIncrementMs(FORWARD_MS)
            .build().apply {
            this.setMediaItem(mediaItem)
            this.prepare()
            this.playWhenReady = true

        }
    }

    ConstraintLayout(modifier = modifier) {
        val (videoPlayer) = createRefs()

        // player view
        DisposableEffect(
            AndroidView(
                modifier =
                    Modifier.constrainAs(videoPlayer) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                factory = {

                    // exo player view for our video player
                    StyledPlayerView(context).apply {
                        player = exoPlayer
                        layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                        (player as ExoPlayer).addListener(object: Player.Listener{
                            override fun onEvents(
                                player: Player,
                                events: Player.Events
                            ) {

                            }

                            override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {
                                super.onMediaMetadataChanged(mediaMetadata)
                                videoTitle.value = mediaMetadata.title.toString()
                            }

                            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                                super.onMediaItemTransition(mediaItem, reason)
                                videoTitle.value = mediaItem?.mediaMetadata?.title.toString()
                            }

                            override fun onPlaybackStateChanged(playbackState: Int) {
                                super.onPlaybackStateChanged(playbackState)
                            }

                            override fun onMetadata(metadata: Metadata) {
                                super.onMetadata(metadata)
                            }

                            override fun onPlayerError(error: PlaybackException) {
                                super.onPlayerError(error)
                            }

                        })
                    }
                }
            )
        ) {
            onDispose {
                // relase player when no longer needed
                exoPlayer.release()
            }
        }
    }
}