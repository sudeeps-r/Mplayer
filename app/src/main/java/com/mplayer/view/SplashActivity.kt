package com.mplayer.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.mplayer.R
import com.mplayer.view._core.BaseActivity
import com.mplayer.view._core.startAnimatedActivity
import com.mplayer.view.browse.BrowseContainer
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.TimeUnit

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : BaseActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        handleSearchView()

        etSearchBox.setOnClickListener{
            finish()
            startAnimatedActivity(BrowseContainer.newIntent(this))
        }

    }

    fun handleSearchView(){
        launch(UI) {
            etSearchBox.visibility=View.GONE
            delay(getString(R.string.splash_timing).toLong(),TimeUnit.SECONDS)
            etSearchBox.visibility=View.VISIBLE
        }
    }






}
