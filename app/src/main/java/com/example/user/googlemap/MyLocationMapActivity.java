package com.example.user.googlemap;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyLocationMapActivity extends AppCompatActivity {

    SupportMapFragment mapFragment;

    MarkerOptions marker;

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location_map);


        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
            }
        });

        MapsInitializer.initialize(this);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMyLocation();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(map != null) {
            map.setMyLocationEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(map != null) {
            map.setMyLocationEnabled(true);
        }
    }

    public void requestMyLocation() {
        long minTime = 10000;   // 위치정보를 갱신할 최소한의 시간
        float minDistance = 0;  // 이전 위치에서 일정 거리만큼 이동하면 위치정보 갱신

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime,
                minDistance,
                new LocationListener() {    // 위치정보가 갱신되는 시점에 Listener안에 있는
                    @Override               // method가 호출됨
                    public void onLocationChanged(Location location) {
                        showCurrentLocation(location);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                }
        );
    }

    public void showCurrentLocation(Location location) {
        LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15)); // 15는 zoom 레벨

        showMarker(location);
    }

    public void showMarker(Location location) {
        if(marker == null) {
            marker = new MarkerOptions();

            marker.position(new LatLng(location.getLatitude(), location.getLongitude()));
            marker.title("내 위치");
            marker.snippet("현재 위치입니다.");
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker));

            map.addMarker(marker).showInfoWindow();
        }
        else {
            marker.position(new LatLng(location.getLatitude(), location.getLongitude()));
        }
    }
}