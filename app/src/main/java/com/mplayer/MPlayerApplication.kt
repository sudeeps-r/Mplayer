package com.mplayer

import android.app.Application
import com.mplayer._core.di.component.AppComponent
import com.mplayer._core.di.component.DaggerAppComponent
import com.mplayer._core.di.module.AppModule
import com.mplayer._core.di.module.DomainApiModule

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class MPlayerApplication : Application() {

    // lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)


    }


    val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                 .domainApiModule(DomainApiModule())
                .appModule(AppModule(this))
                .build()
    }
}