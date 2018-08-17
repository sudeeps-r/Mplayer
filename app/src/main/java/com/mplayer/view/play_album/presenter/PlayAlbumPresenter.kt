package com.mplayer.view.play_album.presenter

import com.mplayer._core.model.search_album.Album
import com.mplayer.view._core.Presenter
import com.mplayer.view.play_album.view.PlayerView

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
interface PlayAlbumPresenter:Presenter<PlayerView> {

    fun updateFavouritedStatus(isFav:Boolean,album:Album)
    fun init(trackId:String)

}