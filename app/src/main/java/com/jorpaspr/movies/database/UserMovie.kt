package com.jorpaspr.movies.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity(tableName = "user_movie")
data class UserMovie(
        @PrimaryKey
        @ColumnInfo(name = "imdb_id")
        val imdbId: String,
        val title: String,
        val actors: String,
        val plot: String,
        @ColumnInfo(name = "imdb_rating")
        val imdbRating: String,
        val poster: String,
        val bookmarked: Boolean) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imdbId)
        parcel.writeString(title)
        parcel.writeString(actors)
        parcel.writeString(plot)
        parcel.writeString(imdbRating)
        parcel.writeString(poster)
        parcel.writeByte(if (bookmarked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean =
            if (other == null || other !is UserMovie) {
                false
            } else {
                imdbId == other.imdbId
            }

    override fun hashCode(): Int {
        return imdbId.hashCode()
    }

    companion object CREATOR : Parcelable.Creator<UserMovie> {
        override fun createFromParcel(parcel: Parcel): UserMovie {
            return UserMovie(parcel)
        }

        override fun newArray(size: Int): Array<UserMovie?> {
            return arrayOfNulls(size)
        }
    }
}
