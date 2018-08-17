package com.mplayer.view.browse.search.view

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.Filterable
import android.widget.TextView
import com.mplayer.R
import com.mplayer._core.db.DBConstant
import com.mplayer._core.db.album_search.AlbumAdapter
import com.mplayer.view._core.CursorUtil

/**
 * Created by Sudeep SR on 15/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class KeywordAdapter(context: Context?, retry: Boolean, cursor: Cursor?,val dbAdapter:AlbumAdapter) : CursorAdapter(context, cursor, retry), Filterable {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.item_keyword, parent, false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val textView: TextView? = view?.findViewById(R.id.tvKeyword) as TextView
        textView?.text = CursorUtil.getString(cursor!!, DBConstant.SEARCH_KEYWORD)
    }

    override fun runQueryOnBackgroundThread(constraint: CharSequence?): Cursor {
        return dbAdapter.searchKeyword(constraint.toString())
    }

    override fun convertToString(cursor: Cursor?): CharSequence {
        return CursorUtil.getString(cursor!!, DBConstant.SEARCH_KEYWORD)
    }
}