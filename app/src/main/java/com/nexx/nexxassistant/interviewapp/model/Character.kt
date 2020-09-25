package com.nexx.nexxassistant.interviewapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


//This represents the character class and all the parcelable implementation

data class Character(

    //we get an info object
    @SerializedName("info")
    val info: CharacterInfo,

    //We get a list of results
    @SerializedName("results")
    val results: List<CharacterResults>
)

//Info
data class CharacterInfo(

    @SerializedName("count")
    val count: Int,

    @SerializedName("pages")
    val pages: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(count)
        parcel.writeInt(pages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterInfo> {
        override fun createFromParcel(parcel: Parcel): CharacterInfo {
            return CharacterInfo(parcel)
        }

        override fun newArray(size: Int): Array<CharacterInfo?> {
            return arrayOfNulls(size)
        }
    }
}

//Results
data class CharacterResults(

    @SerializedName("id")
    val charactedID: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("Status")
    val type: String?,

    @SerializedName("species")
    val species: String?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("image")
    val imageUrl: String?,

    @SerializedName("location")
    val location: CharacterLocation
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(CharacterLocation::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(charactedID)
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(species)
        parcel.writeString(status)
        parcel.writeString(imageUrl)
        parcel.writeParcelable(location, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterResults> {
        override fun createFromParcel(parcel: Parcel): CharacterResults {
            return CharacterResults(parcel)
        }

        override fun newArray(size: Int): Array<CharacterResults?> {
            return arrayOfNulls(size)
        }
    }
}

data class CharacterLocation(

    @SerializedName("name")
    val locationName: String?
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(locationName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterLocation> {
        override fun createFromParcel(parcel: Parcel): CharacterLocation {
            return CharacterLocation(parcel)
        }

        override fun newArray(size: Int): Array<CharacterLocation?> {
            return arrayOfNulls(size)
        }
    }
}