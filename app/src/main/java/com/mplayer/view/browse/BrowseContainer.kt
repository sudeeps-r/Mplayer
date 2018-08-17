package com.mplayer.view.browse

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.mplayer.R
import com.mplayer.view._core.BaseActivity
import com.mplayer.view._core.inTransaction
import com.mplayer.view.browse.favourite.view.FavouriteList
import com.mplayer.view.browse.search.view.BrowseView
import com.mplayer.view.browse.search.view.SearchAlbum

/**
 * Created by Sudeep SR on 13/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class BrowseContainer : BaseActivity(), BrowseView {

    override fun navigateToFavourite() {
        supportFragmentManager?.inTransaction { replace(R.id.fl_main_container2, FavouriteList.newFragment()) }
    }

    companion object {
        const val BROWSE_INDEX: String = "browse_index"
        const val BROWSE_SONGS: Int = 0
        const val BROSE_FAV: Int = 1
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, BrowseContainer::class.java)
            return intent
        }

        fun newIntentClearTop(context: Context): Intent {
            val intent = Intent(context, BrowseContainer::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            return intent
        }
        fun newIntent(context: Context, index: Int): Intent {
            val intent = Intent(context, BrowseContainer::class.java)
            intent.putExtra(BROWSE_INDEX, index)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)
        init()

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        var index: Int? = intent?.getIntExtra(BROWSE_INDEX, 0)
        if (index == 0) {
            supportFragmentManager?.inTransaction { replace(R.id.fl_main_container, SearchAlbum.newFragment()) }
        } else {
            supportFragmentManager?.inTransaction { replace(R.id.fl_main_container, FavouriteList.newFragment()) }
        }

    }
}