package com.mplayer.view.browse.search.presenter

import android.util.Log
import com.mplayer._core.db.album_search.AlbumAdapter
import com.mplayer._core.model.search_album.AlbumResponse
import com.mplayer._core.use_case.album_search.AlbumSearchUseCase
import com.mplayer.view.browse.search.view.SearchView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class SearchPresenterImpl(val albumSearchUseCase: AlbumSearchUseCase, val dbAdapter: AlbumAdapter) : SearchPresenter {
    override fun getDBAdapter(): AlbumAdapter {
        return this.dbAdapter
    }

    val media: String = "music"
    lateinit var view: SearchView
    var disposable: CompositeDisposable = CompositeDisposable()

    override fun searchAlbum(keyword: String) {

        this.view?.let {
            //is init is required
            this.view.showLoader()
            var view = this.view  //For better error handling rx error handler needs be added in the retrofit instance
            var disposable: Disposable = this.albumSearchUseCase.searchAlbum(keyword, media)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(this.view.getScheduler())
                    .subscribeWith(object : DisposableObserver<AlbumResponse>() {
                        override fun onComplete() {
                            Log.e("album", "Oncomplete")
                            view?.hideLoader()
                        }

                        override fun onNext(value: AlbumResponse) {
                            Log.e("album", ">>" + value?.result.size)
                            async(CommonPool) {
                                if (value?.count > 0)
                                    dbAdapter.updateOrAddSearchKeyword(keyword)
                                dbAdapter.updateAlbumList(value)
                                launch(UI) {
                                    view?.hideLoader()
                                    view?.showSearchResult(value)
                                }
                            }

                        }

                        override fun onError(e: Throwable?) {
                            e?.printStackTrace()
                            view?.hideLoader()
                            view?.showMessage(e!!.message)

                        }

                    })

            this.disposable.add(disposable)
        }


    }

    override fun attachView(view: SearchView) {
        this.view = view
    }

    override fun detachView() {
        disposable?.let {
            if (!disposable.isDisposed) {
                disposable.dispose()
            }
        }
    }
}