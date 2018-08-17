package com.mplayer.view._core

import android.content.Context
import android.os.Bundle
import android.support.transition.Slide
import android.support.transition.TransitionInflater
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import com.mplayer.R
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * Created by Sudeep SR on 13/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
open class BaseActivity : AppCompatActivity(), View {
    override fun getViewContext(): Context? {
        return this
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.elevation = 0f
    }

    protected fun init() {
        getToolbar()?.let {
            setSupportActionBar(getToolbar())
        }
    }

    protected fun init(res: Int) {
        init()
        setActionBar(res)
    }

    fun getToolbar(): android.support.v7.widget.Toolbar {
        return mToolbar
    }

    protected fun setActionBar(res: Int) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(res)
        supportActionBar?.setHomeButtonEnabled(true)


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            this.finish()
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        } else {
            super.onBackPressed()
        }

    }

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun showMessage(message: String?) {

    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}