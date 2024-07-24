package com.behruzbek0430.polyline_polygone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.behruzbek0430.polyline_polygone.databinding.ActivityPalygoneBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.PolygonOptions
import com.google.maps.android.SphericalUtil

class PalygoneActivity : AppCompatActivity(), OnMapReadyCallback {

    private val binding by lazy { ActivityPalygoneBinding.inflate(layoutInflater) }
    lateinit var mMap : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map1) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    var polygone:Polygon? = null
    val list = ArrayList<LatLng>()

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        val camerPosition = CameraPosition.Builder()
            .zoom(18f)
            .tilt(45f)
            .bearing(90f)
            .target(LatLng(41.3110, 69.2403))

        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camerPosition.build()))

        mMap.setOnMapClickListener {
            list.add(it)

            if (polygone == null){
                polygone = mMap.addPolygon(PolygonOptions().addAll(list))
            }else{
                polygone?.points = list
            }

            val area = calculatePolygonArea(list)
            binding.txtSize.text = area.toString()

            binding.cancel.setOnClickListener {
             if (list.size > 0){
                 list.removeAt(list.size - 1)
                 polygone?.points = list
                 val area = calculatePolygonArea(list)
                 binding.txtSize.text = area.toString()
             }
            }

        }

    }

    private fun calculatePolygonArea(polygonPoints: List<LatLng>): Double {
        return SphericalUtil.computeArea(polygonPoints)
    }
}