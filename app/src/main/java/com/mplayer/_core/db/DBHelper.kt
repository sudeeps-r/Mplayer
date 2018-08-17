package com.mplayer._core.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class DBHelper(var context: Context) : SQLiteOpenHelper(context, DBConstant.dbName, null, DBConstant.dbversion) {

    override fun onCreate(p0: SQLiteDatabase?) {

        p0?.execSQL(DBConstant.CREATE_TABLE_TRACK)
        p0?.execSQL(DBConstant.CREATE_TABLE_FAVOURITE)
        p0?.execSQL(DBConstant.CREATE_TABLE_SEARCH_KEYWORD)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

        p0?.execSQL("DROP TABLE $DBConstant.TABLE_TRACK_SEARCH_RESULT")
        p0?.execSQL("DROP TABLE $DBConstant.TABLE_FAVOURITE")
        p0?.execSQL("DROP TABLE $DBConstant.TABLE_SEARCH_KEYWORD")
        onCreate(p0)
    }
}