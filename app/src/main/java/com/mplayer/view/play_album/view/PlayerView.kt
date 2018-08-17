package com.mplayer.view.play_album.view

import com.mplayer.view._core.View

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
interface PlayerView :View {


    fun setProgress()
    fun setPlayPause(play:Boolean)
    fun playerSeekTo(pos:Long)

    fun showPlayerMessage(message:String)

    fun upadteFavStatus(isFav:Boolean,isClicked:Boolean)
}