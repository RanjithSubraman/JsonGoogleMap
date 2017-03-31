package com.goovis.jsongooglemap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.goovis.jsongooglemap.bean.Util;

/**
 * Created by Ranjith Subramaniam on 03/28/2017
 */

public class SingleLocationActivity extends FragmentActivity implements OnMapReadyCallback {
    private static String TAG = SingleLocationActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent in = getIntent();
        LatLng locationCoordinate = new LatLng(in.getDoubleExtra(Util.TAG_LATITUDE, 0.0), in.getDoubleExtra(Util.TAG_LONGITUDE, 0.0));

        googleMap.addMarker(new MarkerOptions().position(locationCoordinate).title("point"));
        // Move the camera instantly to hamburg with a zoom of 15.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationCoordinate, 15));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }
}
