package com.mplayer.view.browse.search.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import com.mplayer._core.model.search_album.AlbumResponse
import com.mplayer.view._core.BaseFragment

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class SearchResultPager(manager: FragmentManager?) : FragmentStatePagerAdapter(manager) {

    var size: Int = 0
    var pageSize: Int = 0


    override fun getItem(position: Int): Fragment {
        return ListAlbum.newInstance(position, pageSize)
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE;
    }

    override fun getCount(): Int {
        return size
    }

    fun setSearchResultSize(size: Int, pageSize: Int) {
        this.size = size
        this.pageSize = pageSize
        Log.e("Adapter","Size"+size)
        notifyDataSetChanged()
    }
}