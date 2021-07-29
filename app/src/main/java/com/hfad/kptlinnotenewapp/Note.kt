package com.hfad.kptlinnotenewapp


import android.os.Parcel
import android.os.Parcelable


class Note():Parcelable {
    var id:String? = null
     var title1:String = "pop"
    var description1:String = "opis"

    constructor(title: String, description:String) : this()
    {
        title1 = title
        description1 = description}

    constructor(id:String, title: String, description:String) : this()
    {   this.id = id
        title1 = title
    description1 = description}

    constructor(parcel: Parcel) : this(
        TODO("id"),
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