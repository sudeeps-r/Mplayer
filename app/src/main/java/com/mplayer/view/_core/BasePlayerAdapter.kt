package com.mplayer.view._core

import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mplayer.R
import com.mplayer._core.db.DBConstant
import com.mplayer._core.model.search_album.Album
import com.mplayer.view.browse.search.view.AlbumHolder
import com.mplayer.view.browse.search.view.AlbumSelector

/**
 * Created by Sudeep SR on 17/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
abstract class BasePlayerAdapter(var clickListener: AlbumSelector) : RecyclerView.Adapter<AlbumHolder>(), View.OnClickListener {
    override fun onClick(p0: View?) {
        var position: Int = p0?.getTag() as Int
        cursor!!.moveToPosition(position)
        var album = Album(CursorUtil.getString(cursor!!, DBConstant.TRACK_ID)
                , CursorUtil.getString(cursor!!, DBConstant.ARTIST_NAME)
                , CursorUtil.getString(cursor!!, DBConstant.COLLECTION_NAME)
                , CursorUtil.getString(cursor!!, DBConstant.TRACK_NAME)
                , CursorUtil.getString(cursor!!, DBConstant.THUMB_NAIL)
                , CursorUtil.getString(cursor!!, DBConstant.PREVIEW_URL)
                , CursorUtil.getString(cursor!!, DBConstant.STREAMING_URL)
        )

        this.clickListener.onItemSelected(album)
    }

    protected var pageSize: Int = 0
    protected var cursor: Cursor? = null
    protected var startIndex: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.track_view, parent, false)
        return AlbumHolder(view)
    }

    override fun getItemCount(): Int {
        return pageSize
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {

        var tempPos: Int = this.startIndex + position
        Log.e("PLAdater","Bind"+this.cursor?.count+">"+tempPos)
        cursor!!.moveToPosition(tempPos)
        holder.title.text = CursorUtil.getString(cursor!!, DBConstant.TRACK_NAME)
        holder.subTitle.text = CursorUtil.getString(cursor!!, DBConstant.ARTIST_NAME) + " | " + CursorUtil.getString(cursor!!, DBConstant.COLLECTION_NAME)
        ImageLoader.loadImage(holder.thumbNail, CursorUtil.getString(cursor!!, DBConstant.THUMB_NAIL))
        holder.view.tag = tempPos
        holder.view.setOnClickListener(this)

    }


    abstract fun setAdapterData(pageSize: Int, offset: Int, cursor: Cursor?)
}