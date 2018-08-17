package com.mplayer.view._core

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.mplayer.MPlayerApplication
import com.mplayer._core.di.component.AppComponent
import com.squareup.picasso.Picasso
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import android.app.Activity
import android.view.inputmethod.InputMethodManager


/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */


inline fun Fragment.showToast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
}

inline fun Fragment.appComponent(): AppComponent {
    var ap: MPlayerApplication = activity?.application as MPlayerApplication
    return ap.appComponent


}

 fun RecyclerView.Adapter<RecyclerView.ViewHolder>.loadImage(imageView:ImageView, url:String){
    Picasso.get().load(url).into(imageView)
}

inline fun Fragment.hideKeyboard() {
    val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity?.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm!!.hideSoftInputFromWindow(view.windowToken, 0)
}

