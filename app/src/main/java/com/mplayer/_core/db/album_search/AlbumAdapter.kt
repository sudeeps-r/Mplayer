package com.mplayer._core.db.album_search

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.mplayer._core.db.DBConstant
import com.mplayer._core.model.search_album.Album
import com.mplayer._core.model.search_album.AlbumResponse
import javax.inject.Inject

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class AlbumAdapter @Inject constructor(val context: Context) {

    fun updateAlbumList(albumResponse: AlbumResponse?) {
        albumResponse?.let {
            this.wipeAllCachedResponse()
            var contentValue = arrayOfNulls<ContentValues>(albumResponse.count)
            var i: Int = 0
            var cv: ContentValues
            albumResponse.result.forEach { albumResponse ->
                cv = ContentValues();cv.put(DBConstant.ARTIST_NAME, albumResponse.artistName);
                cv.put(DBConstant.COLLECTION_NAME, albumResponse.collectionName);
                cv.put(DBConstant.TRACK_NAME, albumResponse.trackName);
                cv.put(DBConstant.THUMB_NAIL, albumResponse.thumbNail);
                cv.put(DBConstant.PREVIEW_URL, albumResponse.artMainUrl);
                cv.put(DBConstant.STREAMING_URL, albumResponse.previewUrl);
                contentValue[i] = cv;
                i++
            }
            var cnt:Int=this.context?.contentResolver.bulkInsert(DBConstant.TRACK_URI, contentValue)
            Log.e("DB Inser","Total"+cnt)
        }
    }

    fun updateOrAddSearchKeyword(keyword: String) {
        var cursor: Cursor = this.context?.contentResolver.query(DBConstant.SEARCH_URI, null, DBConstant.SEARCH_KEYWORD + " = ?", arrayOf(keyword), null)
        if (cursor.count > 0) {
            return
        }

        var cv = ContentValues()
        cv.put(DBConstant.SEARCH_KEYWORD, keyword)
        this.context?.contentResolver.insert(DBConstant.SEARCH_URI, cv)
    }

    fun wipeAllCachedResponse() {
        this.context?.contentResolver.delete(DBConstant.TRACK_URI, null, null)
    }

    fun searchKeyword(filter: String): Cursor {
        return this.context.contentResolver.query(DBConstant.SEARCH_URI, null, DBConstant.SEARCH_KEYWORD + " LIKE ? ", arrayOf("%$filter%"), null)
    }

    fun checkIsFavourited(trackId: String): Boolean {
        val cursor: Cursor = this.context.contentResolver.query(DBConstant.FAVOURITE_URI, null, DBConstant.TRACK_ID + "  =   ? ", arrayOf(trackId), null)
        return if (cursor.count > 0) true else false
    }

    fun deleteFav(trackId: String): Int {
        return this.context?.contentResolver.delete(DBConstant.FAVOURITE_URI, DBConstant.TRACK_ID + " = ?", arrayOf(trackId))
    }

    fun addToFavourite(albumResponse: Album) {
        var cv: ContentValues = ContentValues()
        cv.put(DBConstant.TRACK_ID, albumResponse.trackId)
        cv.put(DBConstant.COLLECTION_NAME, albumResponse.collectionName);
        cv.put(DBConstant.TRACK_NAME, albumResponse.trackName);
        cv.put(DBConstant.THUMB_NAIL, albumResponse.thumbNail);
        cv.put(DBConstant.PREVIEW_URL, albumResponse.artMainUrl);
        cv.put(DBConstant.STREAMING_URL, albumResponse.previewUrl)
        cv.put(DBConstant.ARTIST_NAME, albumResponse.artistName);
        this.context?.contentResolver.insert(DBConstant.FAVOURITE_URI, cv)
    }
}