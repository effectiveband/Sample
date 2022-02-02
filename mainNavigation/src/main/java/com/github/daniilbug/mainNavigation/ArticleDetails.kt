package com.github.daniilbug.mainNavigation

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class ArticleDetails(
    val title: String,
    val content: String,
    val imageUrl: String?,
    val url: String,
    val source: String,
    val date: Date
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        Date(parcel.readLong())
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(imageUrl)
        parcel.writeString(url)
        parcel.writeString(source)
        parcel.writeLong(date.time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticleDetails> {
        override fun createFromParcel(parcel: Parcel): ArticleDetails {
            return ArticleDetails(parcel)
        }

        override fun newArray(size: Int): Array<ArticleDetails?> {
            return arrayOfNulls(size)
        }
    }
}