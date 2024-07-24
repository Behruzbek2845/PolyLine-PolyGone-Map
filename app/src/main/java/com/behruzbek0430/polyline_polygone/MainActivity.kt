package com.behruzbek0430.polyline_polygone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.behruzbek0430.polyline_polygone.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.SphericalUtil

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    private fun calculatePolygonArea(polygonPoints: List<LatLng>): Double {
        return SphericalUtil.computeArea(polygonPoints)
    }

    val list = ArrayList<LatLng>()
    var polyLine:Polyline? = null

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
            if (polyLine==null){
                polyLine = mMap.addPolyline(PolylineOptions().addAll(list))
            }else{
                polyLine?.points = list
            }
        }

    }
}