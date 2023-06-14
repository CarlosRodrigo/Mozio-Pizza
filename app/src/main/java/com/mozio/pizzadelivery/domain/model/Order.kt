package com.mozio.pizzadelivery.domain.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Model that represents an Order with a list of Flavors and final price
 */
class Order(val flavors: List<PizzaFlavor>?, val price: Double) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(PizzaFlavor)?.toList(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(flavors)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }

}