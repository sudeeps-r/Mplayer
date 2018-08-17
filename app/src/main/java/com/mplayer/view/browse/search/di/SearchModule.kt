package com.mplayer.view.browse.search.di

import com.mplayer._core.db.album_search.AlbumAdapter
import com.mplayer._core.di.scope.ViewScope
import com.mplayer._core.use_case.album_search.AlbumSearchUseCase
import com.mplayer.view.browse.search.presenter.SearchPresenter
import com.mplayer.view.browse.search.presenter.SearchPresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@ViewScope
@Module
class SearchModule {

    @Provides
    fun provideSearchPresenterImpl(searchUseCase: AlbumSearchUseCase,dbAdapter: AlbumAdapter):SearchPresenter{
        return SearchPresenterImpl(searchUseCase,dbAdapter)
    }
}