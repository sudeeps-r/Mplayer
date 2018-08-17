package com.mplayer.view._core

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by Sudeep SR on 15/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class ImageLoader {
    companion object {
        fun loadImage(imageView: ImageView,url:String ){
            Picasso.get().load(url).into(imageView)
        }
    }
}