package com.mplayer.view.browse.favourite.view

import android.database.Cursor
import com.mplayer.view._core.View

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
interface FavouriteView:View {

    fun itemRemoved(trackId:String)

    fun swapCursor(cursor: Cursor?)
}