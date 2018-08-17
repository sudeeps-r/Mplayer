package com.mplayer.view.browse.favourite.view.di

import com.mplayer._core.di.scope.ViewScope
import com.mplayer.view.browse.favourite.view.FavouriteList
import dagger.Subcomponent

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@ViewScope
@Subcomponent(modules = arrayOf(FavouriteModule::class))
interface FavouriteComponent {

    fun inject(favouriteList: FavouriteList)
}