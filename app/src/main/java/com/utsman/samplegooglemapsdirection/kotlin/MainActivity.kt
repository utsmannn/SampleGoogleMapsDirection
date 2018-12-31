/*
 * Created by Muhammad Utsman on 31/12/2018
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 12/31/18 10:15 PM
 */

package com.utsman.samplegooglemapsdirection.kotlin

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import com.utsman.samplegooglemapsdirection.R
import com.utsman.samplegooglemapsdirection.java.model.DirectionResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var fkip: LatLng
    private lateinit var monas: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fkip = LatLng(-6.3037978, 106.8693713)
        monas = LatLng(-6.1890511, 106.8251573)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.maps_view) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val markerFkip = MarkerOptions()
                .position(fkip)
                .title("FKIP")
        val markerMonas = MarkerOptions()
                .position(monas)
                .title("Monas")

        map.addMarker(markerFkip)
        map.addMarker(markerMonas)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(monas, 11.6f))

        val fromFKIP = fkip.latitude.toString() + "," + fkip.longitude.toString()
        val toMonas = monas.latitude.toString() + "," + monas.longitude.toString()

        val apiServices = RetrofitClient.apiServices(this)
        apiServices.getDirection(fromFKIP, toMonas, getString(R.string.api_key))
                .enqueue(object : Callback<DirectionResponses> {
                    override fun onResponse(call: Call<DirectionResponses>, response: Response<DirectionResponses>) {
                        drawPolyline(response)
                        Log.d("bisa dong oke", response.message())
                    }

                    override fun onFailure(call: Call<DirectionResponses>, t: Throwable) {
                        Log.e("anjir error", t.localizedMessage)
                    }
                })

    }

    private fun drawPolyline(response: Response<DirectionResponses>) {
        val shape = response.body()?.routes?.get(0)?.overviewPolyline?.points
        val polyline = PolylineOptions()
                .addAll(PolyUtil.decode(shape))
                .width(8f)
                .color(Color.RED)
        map.addPolyline(polyline)
    }

    private interface ApiServices {
        @GET("maps/api/directions/json")
        fun getDirection(@Query("origin") origin: String,
                         @Query("destination") destination: String,
                         @Query("key") apiKey: String): Call<DirectionResponses>
    }

    private object RetrofitClient {
        fun apiServices(context: Context): ApiServices {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(context.resources.getString(R.string.base_url))
                    .build()

            return retrofit.create<ApiServices>(ApiServices::class.java)
        }
    }
}
