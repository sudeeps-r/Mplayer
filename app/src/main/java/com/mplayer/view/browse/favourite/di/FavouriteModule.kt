package com.mplayer.view.browse.favourite.view.di

import com.mplayer._core.db.album_search.AlbumAdapter
import com.mplayer._core.di.scope.ViewScope
import com.mplayer.view.browse.favourite.presenter.FavouritePresenter
import com.mplayer.view.browse.favourite.presenter.FavouritePresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@ViewScope
@Module
class FavouriteModule {


    @Provides
    fun provideFavouritePresenter(dbAdapter: AlbumAdapter): FavouritePresenter {
        return FavouritePresenterImpl(dbAdapter)
    }
}