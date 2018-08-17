package com.mplayer._core.model.search_album

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */

@Parcelize
data class Album (
        @SerializedName("trackId") var trackId:String,
        @SerializedName("artistName") var artistName:String,
        @SerializedName("collectionName") var collectionName:String,
        @SerializedName("trackName")var trackName:String,
        @SerializedName("artworkUrl60") var thumbNail:String,
        @SerializedName("artworkUrl100") var artMainUrl:String,
        @SerializedName("previewUrl")var previewUrl:String

): Parcelable {
}