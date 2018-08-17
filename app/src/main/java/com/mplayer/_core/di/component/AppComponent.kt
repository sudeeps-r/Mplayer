package com.mplayer._core.di.component

import android.app.Application
import android.content.Context
import com.mplayer._core.di.module.AppModule
import com.mplayer._core.di.module.DomainApiModule
import com.mplayer._core.di.scope.AppContext
import com.mplayer._core.di.scope.ApplicationScope
import com.mplayer.view.browse.favourite.view.di.FavouriteComponent
import com.mplayer.view.browse.favourite.view.di.FavouriteModule
import com.mplayer.view.browse.search.di.SearchComponent
import com.mplayer.view.browse.search.di.SearchModule
import com.mplayer.view.play_album.di.PlayAlbumComponent
import com.mplayer.view.play_album.di.PlayAlbumModule
import dagger.Component
import dagger.Provides

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@ApplicationScope
@Component(modules = arrayOf(AppModule::class,DomainApiModule::class))
interface AppComponent {

    fun inject(context:Application)

    fun plus(searchModule: SearchModule):SearchComponent

    fun plus(playAlbumModule: PlayAlbumModule):PlayAlbumComponent

    fun plus(favouriteModule: FavouriteModule): FavouriteComponent
}