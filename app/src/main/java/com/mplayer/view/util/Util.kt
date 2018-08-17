package com.mplayer.view.util

import java.util.*

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class Util {

    companion object {
         fun stringForTime(timeMs: Int): String {
            val mFormatBuilder: StringBuilder
            val mFormatter: Formatter
            mFormatBuilder = StringBuilder()
            mFormatter = Formatter(mFormatBuilder, Locale.getDefault())
            val totalSeconds = timeMs / 1000

            val seconds = totalSeconds % 60
            val minutes = totalSeconds / 60 % 60
            val hours = totalSeconds / 3600

            mFormatBuilder.setLength(0)
            return if (hours > 0) {
                mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString()
            } else {
                mFormatter.format("%02d:%02d", minutes, seconds).toString()
            }
        }
    }
}