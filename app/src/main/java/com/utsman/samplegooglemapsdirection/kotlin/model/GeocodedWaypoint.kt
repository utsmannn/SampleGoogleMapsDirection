/*
 * Created by Muhammad Utsman on 31/12/2018
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 12/31/18 11:21 PM
 */

package com.utsman.samplegooglemapsdirection.kotlin.model

import com.google.gson.annotations.SerializedName

data class GeocodedWaypoint(
        @SerializedName("geocoder_status")
        var geocoderStatus: String?,
        @SerializedName("place_id")
        var placeId: String?,
        @SerializedName("types")
        var types: List<String?>?
)