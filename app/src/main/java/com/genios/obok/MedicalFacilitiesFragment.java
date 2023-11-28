package com.genios.obok;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.Manifest;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MedicalFacilitiesFragment extends Fragment implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private LinearLayout linearLayout;
    private TextView txtn; //대피소 이름
    private TextView txta; //대피소 주소
    private TextView txtp; //대피소 수용인원
    private Boolean flag=true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medical_facilities, container, false);

        // Initialize and display the map
        initMap();
        txtn =view.findViewById(R.id.map_info_name);
        txtp = view.findViewById(R.id.map_info_people);
        linearLayout = view.findViewById(R.id.map_info_layout2);


        return view;
    }
    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        setupMap();
        addMarkersFromTextFile();
        startLocationUpdates();
    }

    private void setupMap() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    private void startLocationUpdates() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);


        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location location = locationResult.getLastLocation();
                if (location != null && flag) {
                    LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                    flag=false;
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,15));
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (fusedLocationProviderClient != null && locationCallback != null) {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }

    private void addMarkersFromTextFile() {
        try {
            // 텍스트 파일을 읽기 위한 InputStream 열기
            InputStream inputStream = getResources().openRawResource(R.raw.h);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = reader.readLine()) != null) {
                // 파일에서 데이터를 읽어와서 마커 추가
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String hospitalName = parts[0];
                    double longitude = Double.parseDouble(parts[1]);
                    double  latitude= Double.parseDouble(parts[2]);
                    String phonenumber = parts[3];
                    LatLng hospitalLatLng = new LatLng(latitude, longitude);
                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(hospitalLatLng)
                            .snippet(phonenumber)
                            .title(hospitalName);
                    googleMap.addMarker(markerOptions);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // 클릭한 마커의 정보를 가져와서 표시
                linearLayout.setVisibility(View.VISIBLE);
                String hospitalName = marker.getTitle();
                String ph = marker.getSnippet();
                txtn.setText(hospitalName);
                txtp.setText(ph);
                return true; // 이벤트 처리 완료
            }
        });

    }

}