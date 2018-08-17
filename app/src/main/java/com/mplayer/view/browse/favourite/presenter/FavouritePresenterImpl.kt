package com.mplayer.view.browse.favourite.presenter

import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import com.mplayer._core.db.DBConstant
import com.mplayer._core.db.album_search.AlbumAdapter
import com.mplayer.view._core.ViewConstant
import com.mplayer.view.browse.favourite.view.FavouriteView
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import retrofit2.CallAdapter

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class FavouritePresenterImpl(val dbAdapter: AlbumAdapter) : FavouritePresenter, LoaderManager.LoaderCallbacks<Cursor> {
    override fun getLoaderInstance(): LoaderManager.LoaderCallbacks<Cursor> {
        return this
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        if (id == ViewConstant.FAVOURITE_LOADER) {
            return CursorLoader(view.getViewContext()!!, DBConstant.FAVOURITE_URI, null, null, null, null)
        } else {
            return CursorLoader(view.getViewContext()!!)
        }
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        if (loader.id == ViewConstant.FAVOURITE_LOADER) {
            this.view?.swapCursor(data!!)
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
            if(loader.id==ViewConstant.FAVOURITE_LOADER){
                view?.swapCursor(null)
            }
    }

    private lateinit var view: FavouriteView

    override fun removeItem(trackId: String) {
        launch(CommonPool) {
            dbAdapter.deleteFav(trackId)
        }
    }

    override fun attachView(view: FavouriteView) {
        this.view = view
    }

    override fun detachView() {

    }
}