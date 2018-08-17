package com.mplayer.view.play_album.di

import com.mplayer._core.di.scope.ViewScope
import com.mplayer.view.play_album.view.PlayAlbum
import dagger.Subcomponent

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@ViewScope
@Subcomponent(modules = arrayOf(PlayAlbumModule::class))
interface PlayAlbumComponent {
    fun inject(playAlbum: PlayAlbum)
}