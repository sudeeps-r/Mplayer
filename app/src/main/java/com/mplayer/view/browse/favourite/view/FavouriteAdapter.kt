package com.mplayer.view.browse.favourite.view

import android.content.Context
import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mplayer.R
import com.mplayer._core.db.DBConstant
import com.mplayer._core.model.search_album.Album
import com.mplayer.view._core.BasePlayerAdapter
import com.mplayer.view._core.CursorUtil
import com.mplayer.view._core.ImageLoader
import com.mplayer.view.browse.search.view.AlbumHolder
import com.mplayer.view.browse.search.view.AlbumSelector

/**
 * Created by Sudeep SR on 16/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class FavouriteAdapter(var favouriteView: FavouriteView, clickListener: AlbumSelector) : BasePlayerAdapter(clickListener) {

    fun removeAt(position: Int) {
        cursor!!.moveToPosition(position)
        favouriteView.itemRemoved(CursorUtil.getString(cursor!!, DBConstant.TRACK_ID))
        notifyItemRemoved(position)
    }

    override fun setAdapterData(pageSize: Int, offset: Int, cursor: Cursor?) {
        if (cursor == null) {
            this.pageSize = 0
        } else {
            this.pageSize = cursor.count
        }
        this.cursor = cursor
        notifyDataSetChanged()
    }


}