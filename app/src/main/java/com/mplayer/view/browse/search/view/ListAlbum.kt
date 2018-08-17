package com.mplayer.view.browse.search.view

import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.util.Log
import android.view.View
import com.mplayer.R
import com.mplayer._core.db.DBConstant
import com.mplayer._core.model.search_album.Album
import com.mplayer.view._core.BaseFragment
import com.mplayer.view._core.ViewConstant
import com.mplayer.view._core.startAnimatedActivity
import com.mplayer.view.play_album.view.PlayAlbum
import kotlinx.android.synthetic.main.listview.*
import kotlinx.android.synthetic.main.listview.view.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class ListAlbum : BaseFragment(), AlbumSelector, LoaderManager.LoaderCallbacks<Cursor> {
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        if (id == ViewConstant.LIST_ALBUM) {
            return CursorLoader(context!!, DBConstant.TRACK_URI, null, null, null, null)
        } else {
            return CursorLoader(context!!)
        }
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        if (loader.id == ViewConstant.LIST_ALBUM) {
            Log.e("Cursor","Size"+data?.count)
            playerListAdapter.setAdapterData(pageSize, offset, data!!)
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (loader.id == ViewConstant.LIST_ALBUM) {
            playerListAdapter.setAdapterData(pageSize, offset, null)
        }
    }

    override fun onItemSelected(album: Album) {
        startAnimatedActivity(PlayAlbum.newIntent(context!!, album))
    }

    private var offset: Int = 0
    private var pageSize: Int = 0
    private lateinit var playerListAdapter: PlayerListAdapter

    companion object {
        val OFFSET_INDEX: String = "offset_index"
        val PAGE_SIZE: String = "page_size"

        fun newInstance(offset: Int, pageSize: Int): ListAlbum {
            var bundle: Bundle = Bundle()
            bundle.putInt(OFFSET_INDEX, offset)
            bundle.putInt(PAGE_SIZE, pageSize)
            var listAlbum: ListAlbum = ListAlbum()
            listAlbum.arguments = bundle
            return listAlbum
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundle: Bundle? = null
        with(arguments) { if (this != null) bundle = arguments else bundle = savedInstanceState }

        if (bundle != null) {
            offset = bundle!!.getInt(OFFSET_INDEX)
            pageSize = bundle!!.getInt(PAGE_SIZE)
            init()
        } else {
            activity?.finish()
        }
    }

    fun init() {
        playerListAdapter = PlayerListAdapter(this)
        listView.adapter = playerListAdapter
        fetchCursor()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(OFFSET_INDEX, offset)
        super.onSaveInstanceState(outState)
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun getLayoutId(): Int {
        return R.layout.listview
    }



    fun fetchCursor() {
        this.loaderManager.initLoader(ViewConstant.LIST_ALBUM, null, this)
    }
}