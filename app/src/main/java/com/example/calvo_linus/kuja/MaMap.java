package com.example.calvo_linus.kuja;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MaMap extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    public static FirebaseDatabase database;

    public static Double lat=0.0;
    public static Double lng=0.0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ma_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng you = new LatLng(MaMap.lat,MaMap.lng);
        mMap.addMarker(new MarkerOptions().position(you).title("Your position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(you , 15.0f));


        for(int i=0; i<DonorListF.donorInfo.size(); i++){
            Log.d("Donor", String.valueOf(i));

            Donor donor = DonorListF.donorInfo.get(i);
            Double lat = new Double(donor.lat);
            Double lng = new Double(donor.lan);

            Log.d("Donor", donor.lat);
            Log.d("Donor", donor.lan);


            LatLng donar = new LatLng(lat, lng);
            String donorName = donor.name+ " " + donor.contuctNumber;
            mMap.addMarker(new MarkerOptions().position(donar).title(donorName));

        }


    }
}




