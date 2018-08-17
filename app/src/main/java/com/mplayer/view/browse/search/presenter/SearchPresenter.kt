package com.mplayer.view.browse.search.presenter

import com.mplayer._core.db.album_search.AlbumAdapter
import com.mplayer.view._core.Presenter
import com.mplayer.view.browse.search.view.SearchView

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
interface SearchPresenter:Presenter<SearchView> {

    fun searchAlbum(keyword:String)

    fun getDBAdapter():AlbumAdapter

}