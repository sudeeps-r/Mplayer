package com.mplayer._core.use_case.album_search

import com.mplayer._core.api.AlbumSearchAPI
import com.mplayer._core.db.album_search.AlbumAdapter
import com.mplayer._core.di.scope.ApplicationScope
import com.mplayer._core.model.search_album.AlbumResponse
import io.reactivex.Observable
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import java.net.URLEncoder

import javax.inject.Inject

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */

class AlbumSearchUseCase @Inject constructor(val searchAPI: AlbumSearchAPI, val dbAdapter: AlbumAdapter) {


    fun searchAlbum(keyword: String, media: String): Observable<AlbumResponse> {

        keyword.replace(" ", "%20");
        URLEncoder.encode(keyword, "UTF-8");
        return searchAPI.searchAlbum(keyword, media);
    }
}