package com.mplayer.view._core

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mplayer.R
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.progress_indicator.*
import kotlinx.android.synthetic.main.progress_indicator.view.*
import kotlinx.android.synthetic.main.search_album.*

/**
 * Created by Sudeep SR on 13/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
abstract class BaseFragment : Fragment(), com.mplayer.view._core.View {

    abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val res = getLayoutId()
        if (res != -1) {
            return inflater.inflate(res, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    protected fun setHomeUp(res: Int, homeUpEnabled: Boolean) {
        var spActvity: AppCompatActivity = activity as AppCompatActivity
        spActvity.supportActionBar?.setHomeAsUpIndicator(res)
        spActvity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        spActvity.supportActionBar?.setHomeButtonEnabled(homeUpEnabled)
    }

    override fun showMessage(message: String?) {
        var view = view;
        view?.let {

        }
    }

    override fun showLoader() {
        initProgress()
        layout_progress_indicator.visibility=View.VISIBLE
        progressBar.visibility = View.VISIBLE
        tv_error.text = getText(R.string.loading)

    }

    override fun hideLoader() {
        layout_progress_indicator.visibility=View.GONE
    }

    override fun getViewContext(): Context? {
        return context
    }

    protected fun initProgress(){
        if (progressBar.getIndeterminateDrawable() != null) {
            progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }
}