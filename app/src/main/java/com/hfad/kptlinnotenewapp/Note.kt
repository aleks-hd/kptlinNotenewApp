package com.hfad.kptlinnotenewapp


import android.os.Parcel
import android.os.Parcelable

class Note(title: String, description: String):Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("title"),
        TODO("description")
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }

}