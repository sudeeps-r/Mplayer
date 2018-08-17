package com.mplayer._core.api

import com.mplayer._core.model.search_album.AlbumResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
interface AlbumSearchAPI {

    @GET("/search?")
    fun searchAlbum(@Query("term") query: String, @Query("media") media: String): Observable<AlbumResponse>
}