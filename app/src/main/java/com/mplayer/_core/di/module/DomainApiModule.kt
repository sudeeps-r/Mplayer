package com.mplayer._core.di.module

import com.mplayer._core._AppConstant
import com.mplayer._core.api.AlbumSearchAPI
import com.mplayer._core.di.network.NetworkModule
import com.mplayer._core.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@ApplicationScope
@Module(includes = arrayOf(NetworkModule::class))
class DomainApiModule {

    @Provides
    fun provideAlbumSearchApi(@Named(_AppConstant.BASIC_HTTP) retrofit: Retrofit):AlbumSearchAPI{
        return retrofit.create(AlbumSearchAPI::class.java)
    }
}