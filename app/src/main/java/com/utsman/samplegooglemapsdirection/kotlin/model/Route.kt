/*
 * Created by Muhammad Utsman on 31/12/2018
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 12/31/18 11:21 PM
 */

package com.utsman.samplegooglemapsdirection.kotlin.model

import com.google.gson.annotations.SerializedName

data class Route(
        @SerializedName("bounds")
        var bounds: Bounds?,
        @SerializedName("copyrights")
        var copyrights: String?,
        @SerializedName("legs")
        var legs: List<Leg?>?,
        @SerializedName("overview_polyline")
        var overviewPolyline: OverviewPolyline?,
        @SerializedName("summary")
        var summary: String?,
        @SerializedName("warnings")
        var warnings: List<Any?>?,
        @SerializedName("waypoint_order")
        var waypointOrder: List<Any?>?
)