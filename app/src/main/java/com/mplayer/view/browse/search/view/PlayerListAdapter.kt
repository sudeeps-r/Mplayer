package com.mplayer.view.browse.search.view

import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mplayer.R
import com.mplayer._core.model.search_album.AlbumResponse
import com.mplayer.view._core.BasePlayerAdapter


/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class PlayerListAdapter(clickListener: AlbumSelector) : BasePlayerAdapter(clickListener) {


    override fun setAdapterData(pageSize: Int, offset: Int, cursor: Cursor?) {
        if (cursor == null) {
            this.pageSize = 0
            this.startIndex = 0
            this.cursor = null
            notifyDataSetChanged()
            return
        }
        cursor?.let {
            this.pageSize = pageSize
            this.cursor = cursor
            val cnt = cursor.count
            this.startIndex = offset * pageSize
            val remin = cnt - startIndex
            if (remin < pageSize) {
                this.pageSize = remin
            }
            notifyDataSetChanged()
        }

    }

}