package com.mplayer.view.browse.search.di

import android.widget.SearchView
import com.mplayer._core.di.scope.ViewScope
import com.mplayer.view.browse.search.view.SearchAlbum
import dagger.Subcomponent

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */

@ViewScope
@Subcomponent(modules = arrayOf(SearchModule::class))
interface SearchComponent {

    fun inject(searchView: SearchAlbum)
}