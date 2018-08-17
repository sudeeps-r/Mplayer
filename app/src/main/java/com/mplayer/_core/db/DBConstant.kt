package com.mplayer._core.db

import android.net.Uri
import com.mplayer.BuildConfig

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class DBConstant {
    companion object {
        const val dbversion: Int = 1
        const val dbName: String = "craftedBeer"
        const val dbprovider: String = BuildConfig.APPLICATION_ID + ".dbprovider"
        const val base_uri = "content://$dbprovider/"

        const val track_code = 100
        const val TABLE_TRACK_SEARCH_RESULT = "track_search_result"
        const val TRACK_ID = "_id"
        const val TRACK_NAME = "track_name"
        const val COLLECTION_NAME = "collection_name"
        const val ARTIST_NAME = "artist_name"
        const val THUMB_NAIL = "thumb_nail"
        const val PREVIEW_URL = "preview_url"
        const val STREAMING_URL = "streaming_url"
        const val IS_STREAMING_ALLOWED = "streaming_allowed"
        const val CREATE_TABLE_TRACK: String = "CREATE TABLE $TABLE_TRACK_SEARCH_RESULT ( $TRACK_ID INTEGER PRIMARY KEY , $TRACK_NAME VARCHAR, $COLLECTION_NAME VARCHAR , $ARTIST_NAME VARCHAR , $THUMB_NAIL VARCHAR , $PREVIEW_URL VARCHAR , $STREAMING_URL VARCHAR , $IS_STREAMING_ALLOWED INTEGER )"
        val TRACK_URI: Uri = Uri.parse(base_uri + TABLE_TRACK_SEARCH_RESULT)
        //
        const val track_favourite = 101
        const val TABLE_FAVOURITE = "track_favourite"
        const val CREATE_TABLE_FAVOURITE: String = "CREATE TABLE $TABLE_FAVOURITE ( $TRACK_ID INTEGER PRIMARY KEY , $TRACK_NAME VARCHAR,  $ARTIST_NAME VARCHAR ,$COLLECTION_NAME VARCHAR , $THUMB_NAIL VARCHAR , $PREVIEW_URL VARCHAR , $STREAMING_URL VARCHAR , $IS_STREAMING_ALLOWED INTEGER )"
        val FAVOURITE_URI: Uri = Uri.parse(base_uri + TABLE_FAVOURITE)

        const val search_keyword = 102
        const val TABLE_SEARCH_KEYWORD = "search_keyword"
        const val SEARCH_KEYWORD = "_id"
        const val CREATE_TABLE_SEARCH_KEYWORD = "CREATE TABLE $TABLE_SEARCH_KEYWORD ( $SEARCH_KEYWORD TEXT PRIMARY KEY )"
        val SEARCH_URI: Uri = Uri.parse(base_uri + TABLE_SEARCH_KEYWORD)

    }
}