package com.mplayer.view.browse.favourite.view

import android.database.Cursor
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mplayer.R
import com.mplayer._core.model.search_album.Album
import com.mplayer.view._core.BaseFragment
import com.mplayer.view._core.SwipeToDeleteCallback
import com.mplayer.view._core.ViewConstant
import com.mplayer.view._core.appComponent
import com.mplayer.view.browse.favourite.presenter.FavouritePresenter
import com.mplayer.view.browse.favourite.view.di.FavouriteComponent
import com.mplayer.view.browse.favourite.view.di.FavouriteModule
import com.mplayer.view.browse.search.view.AlbumSelector
import com.mplayer.view.browse.search.view.PlayerListAdapter
import com.mplayer.view.play_album.view.PlayAlbum
import kotlinx.android.synthetic.main.favourite_list.*
import kotlinx.android.synthetic.main.listview.*
import kotlinx.android.synthetic.main.progress_indicator.*
import kotlinx.android.synthetic.main.search_album.*
import javax.inject.Inject

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class FavouriteList : BaseFragment(), FavouriteView, AlbumSelector {
    private val playerListAdapter: FavouriteAdapter = FavouriteAdapter(this,this)
    private var favouriteComponent: FavouriteComponent? = null
    @Inject
    lateinit var presenter: FavouritePresenter

    override fun onItemSelected(album: Album) {
        startActivity(PlayAlbum.newIntent(context!!, album))
    }

    override fun itemRemoved(tracId:String) {
            presenter.removeItem(tracId)
    }

    fun setSwipeListener(){
        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = listView.adapter as FavouriteAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(listView)
    }
    override fun swapCursor(cursor: Cursor?) {

        playerListAdapter.setAdapterData(0,0,cursor!!)
        var view:View?=getView()?.findViewById(R.id.layout_progress_indicator)
        progressBar?.visibility=View.GONE
        cursor?.let {
            if(cursor.count==0){
                view?.visibility=View.VISIBLE
                tv_error?.text=getText(R.string.no_fav)
                tvCount.visibility=View.INVISIBLE
            }else{
                view?.visibility=View.GONE
                tvCount.visibility=View.VISIBLE
            }
        }
        tvCount.text = getText(if(cursor?.count>1)R.string.favourites_cnt else R.string.favourite_cnt).toString() + " " + cursor?.count
    }

    companion object {

        fun newFragment(): FavouriteList {
            return FavouriteList()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.favouriteComponent = appComponent().plus(FavouriteModule())
        this.favouriteComponent?.inject(this)
        if (this.favouriteComponent == null)
            activity?.finish()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeUp(R.drawable.arrow_back, true)
        listView.adapter = playerListAdapter
        presenter.attachView(this)
        setSwipeListener()
        loaderManager.initLoader(ViewConstant.FAVOURITE_LOADER, null, presenter.getLoaderInstance())

    }

    override fun getLayoutId(): Int {
        return R.layout.favourite_list
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun onDestroy() {
        super.onDestroy()
        loaderManager.destroyLoader(ViewConstant.FAVOURITE_LOADER)
        presenter.detachView()
        this.favouriteComponent = null
    }
}