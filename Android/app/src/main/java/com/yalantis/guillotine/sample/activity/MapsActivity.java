package com.yalantis.guillotine.sample.activity;

import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yalantis.guillotine.sample.R;
import com.yalantis.guillotine.sample.utls.History;
import com.yalantis.guillotine.sample.utls.IRepository;
import com.yalantis.guillotine.sample.utls.Repository;
import com.yalantis.guillotine.sample.utls.SessionManger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private String addresse;
    private MarkerOptions markerOptions;
    private Repository repository;
    Double latitude;
    Double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
        mMap = googleMap;

        SessionManger s = new SessionManger(this);
        // Add a marker in Sydney and move the camera

        latitude = Double.parseDouble(s.getUserDetails().get(SessionManger.KEY_LAT));
        longitude = Double.parseDouble(s.getUserDetails().get(SessionManger.KEY_LON));
        markerOptions = new MarkerOptions();
        getAdresseFromLocation(latitude, longitude);
        markerOptions.position(new LatLng(latitude, longitude));
        mMap.clear();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 20));
        mMap.addMarker(markerOptions);


    }

    public void getAdresseFromLocation(double lat, double longi) {
        Geocoder geo = new Geocoder(getApplicationContext());

        Log.w("wael ", lat + " " + longi);
        try {
            //Ici on récupère la premiere adresse trouvé gràce à la position que l'on a récupéré


            List<Address> adresses = geo.getFromLocation(lat,
                    longi, 1);

            if (adresses != null && adresses.size() == 1) {
                Address adresse = adresses.get(0);
                //Si le geocoder a trouver une adresse, alors on l'affiche
                addresse = String.format("%s, %s",
                        adresse.getAddressLine(0),
                        adresse.getCountryName());
                markerOptions.title(String.format("%s, %s",
                        adresse.getAddressLine(0),
                        adresse.getCountryName()));

            } else {
                //sinon on affiche un message d'erreur
                Toast.makeText(getApplicationContext(), "L'adresse n'a pu être déterminée", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "L'adresse n'a pu être déterminée", Toast.LENGTH_LONG).show();
        }
        //on stop le cercle de chargement
        setProgressBarIndeterminateVisibility(false);
    }

    public void addToHistory(View view) {
        repository = new Repository();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        if (addresse!=null) {
            History history = new History(currentDateandTime, String.valueOf(longitude), String.valueOf(latitude), addresse);
            repository.AddHistory(history);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
