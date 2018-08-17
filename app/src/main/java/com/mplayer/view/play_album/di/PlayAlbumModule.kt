package com.mplayer.view.play_album.di

import com.mplayer._core.db.album_search.AlbumAdapter
import com.mplayer._core.di.scope.ViewScope
import com.mplayer.view.play_album.presenter.PlayAlbumPresenter
import com.mplayer.view.play_album.presenter.PlayAlbumPresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */

@ViewScope
@Module
class PlayAlbumModule {

    @Provides
    fun providePlayerAlbumPresenter(dbAdapter: AlbumAdapter): PlayAlbumPresenter {
        return PlayAlbumPresenterImpl(dbAdapter)
    }
}