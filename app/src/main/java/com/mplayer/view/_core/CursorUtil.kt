package com.mplayer.view._core

import android.database.Cursor
import android.util.Log

/**
 * Created by Sudeep SR on 15/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class CursorUtil {

    companion object {
        fun getString(cursor: Cursor, colName: String): String {
            cursor?.let {

                try {
                    var text:String= cursor.getString(cursor.getColumnIndex(colName))
                    return if(text==null)"" else text
                }catch (e:IllegalStateException){
                    return ""
                }

            }

            return ""
        }
    }
}