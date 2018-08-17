package com.mplayer._core.model.search_album

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@Parcelize data class AlbumResponse (@SerializedName("resultCount") val count:Int, @SerializedName("results") val result:List<Album>): Parcelable {
}