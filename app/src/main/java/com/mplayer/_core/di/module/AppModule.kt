package com.mplayer._core.di.module

import android.content.Context
import com.mplayer._core.di.scope.AppContext
import com.mplayer._core.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@ApplicationScope
@AppContext
@Module
class AppModule(val context:Context) {


    @Provides
    fun provideContext():Context{
        return this.context
    }
}