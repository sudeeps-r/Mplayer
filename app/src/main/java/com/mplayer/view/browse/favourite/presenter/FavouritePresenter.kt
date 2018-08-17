package com.mplayer.view.browse.favourite.presenter


import android.database.Cursor
import android.support.v4.app.LoaderManager
import com.mplayer.view._core.Presenter
import com.mplayer.view.browse.favourite.view.FavouriteView

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
interface FavouritePresenter:Presenter<FavouriteView> {

    fun removeItem(trackId:String)

    fun getLoaderInstance(): LoaderManager.LoaderCallbacks<Cursor>
}