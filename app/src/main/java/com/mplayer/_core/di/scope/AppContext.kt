package com.mplayer._core.di.scope

import android.content.Context
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
@Scope
annotation class AppContext {


}