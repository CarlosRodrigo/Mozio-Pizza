package com.mozio.pizzadelivery.domain.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Model that represents a pizza flavor
 */
data class PizzaFlavor(val name: String, val price: Double) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PizzaFlavor> {
        override fun createFromParcel(parcel: Parcel): PizzaFlavor {
            return PizzaFlavor(parcel)
        }

        override fun newArray(size: Int): Array<PizzaFlavor?> {
            return arrayOfNulls(size)
        }
    }
}