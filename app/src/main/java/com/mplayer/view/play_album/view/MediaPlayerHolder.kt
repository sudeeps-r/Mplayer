package com.mplayer.view.play_album.view

import android.util.Log
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class MediaPlayerHolder(var playerView: PlayerView) : ExoPlayer.EventListener {

    private var TAG:String="Player"
    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {

    }

    override fun onSeekProcessed() {

    }

    override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {

    }

    override fun onPlayerError(error: ExoPlaybackException?) {
        playerView.showPlayerMessage("Error $error?.message")
    }

    override fun onLoadingChanged(isLoading: Boolean) {

    }

    override fun onPositionDiscontinuity(reason: Int) {

    }

    override fun onRepeatModeChanged(repeatMode: Int) {

    }

    override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {

    }

    override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {

    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {

        when (playbackState) {
            Player.STATE_ENDED -> {

                //Stop playback and return to start position
                playerView.setPlayPause(false)
                playerView.playerSeekTo(0)
            }
            Player.STATE_READY -> {

                playerView.setProgress()

            }
            /*Player.STATE_BUFFERING -> playerView.showPlayerMessage("Buffering")
            Player.STATE_IDLE -> Log.i(TAG, "ExoPlayer idle!")*/
        }
    }
}