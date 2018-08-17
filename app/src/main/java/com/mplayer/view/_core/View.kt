package com.mplayer.view._core

import android.content.Context
import io.reactivex.Scheduler

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
interface View {

    fun getScheduler(): Scheduler

    fun showLoader()

    fun hideLoader()

    fun showMessage(message:String?)

    fun getViewContext():Context?
}