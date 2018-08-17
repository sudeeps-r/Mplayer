package com.mplayer.view._core

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.mplayer.MPlayerApplication
import com.mplayer.R
import com.mplayer._core.di.component.AppComponent

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commitAllowingStateLoss()
}

inline fun BaseActivity.appComponent(): AppComponent {
    var ap: MPlayerApplication = application as MPlayerApplication
    return ap.appComponent


}

inline fun BaseActivity.startAnimatedActivity(intent: Intent) {
    startActivity(intent)
    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
}

inline fun BaseFragment.startAnimatedActivity(intent: Intent) {
    startActivity(intent)
    activity?.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
}