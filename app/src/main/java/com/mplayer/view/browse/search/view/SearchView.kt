package com.mplayer.view.browse.search.view

import com.mplayer._core.model.search_album.AlbumResponse
import com.mplayer.view._core.View

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
interface SearchView:View {

    fun showSearchResult(result:AlbumResponse?)
}