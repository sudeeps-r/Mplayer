package com.mplayer.view.browse.search.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.track_view.view.*

/**
 * Created by Sudeep SR on 15/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class AlbumHolder(var view: View) : RecyclerView.ViewHolder(view) {

    val thumbNail: ImageView = view.ivArtView
    val title: TextView = view.tvHeaderText
    val subTitle: TextView = view.tvSubTitle
}