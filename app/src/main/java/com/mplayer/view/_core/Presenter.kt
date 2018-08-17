package com.mplayer.view._core

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
interface Presenter<T:View> {

    fun attachView(view: T)
    fun detachView()
}