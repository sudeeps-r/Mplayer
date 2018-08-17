package com.mplayer.view.play_album.presenter

import com.mplayer._core.db.album_search.AlbumAdapter
import com.mplayer._core.model.search_album.Album
import com.mplayer.view.play_album.view.PlayerView
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class PlayAlbumPresenterImpl(var dbAdapter: AlbumAdapter) : PlayAlbumPresenter {

    private var view: PlayerView? = null

    override fun updateFavouritedStatus(isFav: Boolean, album: Album) {

        launch(CommonPool) {
            if (!isFav) {
                //delete fa
                dbAdapter.deleteFav(album.trackId)
            } else {
                //favourite
                dbAdapter.addToFavourite(album)
            }
            launch(UI) {
                view?.upadteFavStatus(isFav,true)
            }
        }

    }

    override fun init(trackId: String) {

        launch(CommonPool) {
            var isFav = dbAdapter.checkIsFavourited(trackId)
            launch(UI) {
                view?.upadteFavStatus(isFav,false)
            }
        }
    }

    override fun attachView(view: PlayerView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}