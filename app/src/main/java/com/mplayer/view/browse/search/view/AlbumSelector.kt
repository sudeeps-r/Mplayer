package com.mplayer.view.browse.search.view

import com.mplayer._core.model.search_album.Album

/**
 * Created by Sudeep SR on 15/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
interface AlbumSelector {
    fun onItemSelected( album:Album)
}