package com.example.laura.touristapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.paperdb.Paper;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = MapsActivity.class.getSimpleName();
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private boolean mLocationPermissionsGranted = false;
    private FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Bundle extra = getIntent().getExtras();
        //String text = extra.getString(MainActivity.TAG_TEXT);
        // String keyword = extra.getString("key");
        //String placetitle = extra.getString("addr");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*if (Geocoder.isPresent()) {
            try {
                String location = keyword;
                Geocoder gc = new Geocoder(this);
                List<Address> addresses = gc.getFromLocationName(location, 5); // get the found Address Objects

                List<LatLng> ll = new ArrayList<LatLng>(addresses.size()); // A list to save the coordinates if they are available
                for (Address a : addresses) {
                    if (a.hasLatitude() && a.hasLongitude()) {
                        ll.add(new LatLng(a.getLatitude(), a.getLongitude()));
                    }
                }
            } catch (IOException e) {
                // handle the exception
            }
        }*/
        /*
        org.osmdroid.views.MapView mapView = (org.osmdroid.views.MapView) findViewById(R.id.map_view); //resolve the map view by id given in the layout
        mapView.setTileSource(new OnlineTileSourceBase("Google Maps", ResourceProxy.string.unknown, 1, 20, 256, ".png", "http://mt3.google.com/vt/v=w2.97") {
            @Override
            public String getTileURLString(final MapTile aTile) {
                /*
                 * GOOGLE MAPS URL looks like
                 * base url	const	x	y zoom
                 * http://mt3.google.com/vt/v=w2.97&x=74327&y=50500&z=17
                 //
                return getBaseUrl() + "&x=" + aTile.getX() + "&y=" + aTile.getY() + "&z=" + aTile.getZoomLevel();
            }
        });
        mapView.setUseDataConnection(false); //this actually makes the controller use only offline tiles*/

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

        final Bundle extra = getIntent().getExtras();
        //String text = extra.getString(MainActivity.TAG_TEXT);
        final String keyword = extra.getString("key");
        //String keyword = extra.getString("key2");
        String placetitle = extra.getString("key1");
        final String language = Paper.book().read("language");
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        boolean success = googleMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.style_json)));
        if (!success) {
            Log.e(TAG, "Style parsing failed.");
        }
        if (placetitle != null)   //a leírásról megy át ide
        {
            //KESZTHELY
            if(placetitle.equals (getResources().getString(R.string.keszthmuseum1))){
                double lat=46.758834;
                double lng=17.242172;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthmuseum2))){//"Cadillac Múzeum")) {
                double lat=46.764614;
                double lng=17.24184;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthmuseum3))) {
                double lat=46.7707424;
                double lng=17.2418793;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (46.0770945, 18.205235))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthmuseum4))) {
                double lat=46.766845;
                double lng=17.235827;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (46.0770945, 18.205235))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthmuseum5))) {
                double lat=46.769314;
                double lng=17.242457;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthmuseum6))) {
                double lat=46.748101;
                double lng=17.2273207;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthtemplom1))) {
                double lat=46.7645622;
                double lng=17.2402638;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthtemplom2))) {
                double lat=46.7701547;
                double lng=17.2515696;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthtemplom3))) {
                double lat=46.7647133;
                double lng=17.2436778;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthtemplom4))) {
                double lat=46.761552;
                double lng=17.244678;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthmonuments1))) {
                double lat=46.7538965;
                double lng=17.2401481;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthmonuments2))) {
                double lat=46.767877;
                double lng=17.236742;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthmonuments3))) {
                double lat=46.7610521;
                double lng=17.2483782;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthmonuments4))) {
                double lat=46.765719;
                double lng=17.243219;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthother1))) {
                double lat=46.7657888;
                double lng=17.2437286;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthother2))) {
                double lat=46.7670602;
                double lng=17.2426282;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthother3))) {
                double lat=46.757526;
                double lng=17.251271;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            //SOPRON
            else if(placetitle.equals (getResources().getString(R.string.sopronmuseum1))) {
                double lat=47.6859625;
                double lng=16.5877292;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmuseum2))) {
                double lat=47.68602;
                double lng=16.589138;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmuseum3))) {
                double lat=47.6865675;
                double lng=16.5887225;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmuseum4))) {
                double lat=47.6861546;
                double lng=16.5878403;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmuseum5))) {
                double lat=47.6801243;
                double lng=16.5757588;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmuseum6))) {
                double lat=47.6865343;
                double lng=16.5884332;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmuseum7))) {
                double lat=47.6865379;
                double lng=16.5884332;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmuseum8))) {
                double lat=47.6865675;
                double lng=16.5887225;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmuseum9))) {
                double lat=47.6849766;
                double lng=16.5882953;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom1))) {
                double lat=47.676427;
                double lng=16.576738;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom2))) {
                double lat=47.686087;
                double lng=16.590419;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom3))) {
                double lat=47.6822631;
                double lng=16.5900476;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom4))) {
                double lat=47.6842216;
                double lng=16.5875043;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom5))) {
                double lat=47.6860398;
                double lng=16.5791263;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom6))) {
                double lat=47.6889156;
                double lng=16.5903373;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom7))) {
                double lat=47.6827106;
                double lng=16.5930383;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom8))) {
                double lat=47.6820183;
                double lng=16.5697732;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom9))) {
                double lat=47.6852427;
                double lng=16.5719104;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom10))) {
                double lat=47.6820068;
                double lng=16.5883899;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom11))) {
                double lat=47.6841548;
                double lng=16.5897922;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom12))) {
                double lat=47.6928676;
                double lng=16.5743193;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.soprontemplom13))) {
                double lat=47.6801826;
                double lng=16.5943413;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmonuments1))) {
                double lat=47.6857246;
                double lng=16.5875402;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmonuments2))) {
                double lat=47.6865379;
                double lng=16.5884332;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmonuments4))) {
                double lat=47.6824615;
                double lng=16.5872532;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmonuments5))) {
                double lat=47.6837866;
                double lng=16.5860852;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmonuments7))) {
                double lat=47.6865675;
                double lng=16.5887225;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmonuments8))) {
                double lat=47.6863651;
                double lng=16.5887217;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmonuments9))) {
                double lat=47.6865675;
                double lng=16.5887225;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmonuments10))) {
                double lat=47.6864904;
                double lng=16.5895486;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronmonuments11))) {
                double lat=47.6852996;
                double lng=16.5882934;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.sopronother1))) {
                double lat=47.6834616;
                double lng=16.5860603;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronother2))) {
                double lat=47.6867339;
                double lng=16.5875822;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.sopronother3))) {
                double lat=47.6770986;
                double lng=16.5586293;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            //EGER
            else if(placetitle.equals (getResources().getString(R.string.egermuseum1))) {
                double lat=47.9032908;
                double lng=20.3677279;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egermuseum2))) {
                double lat=47.9006942;
                double lng=20.3713843;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egermuseum3))) {
                double lat=47.9040518;
                double lng=20.3774534;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egermuseum4))) {
                double lat=47.8997995;
                double lng=20.3739947;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egermuseum5))) {
                double lat=47.9062166;
                double lng=20.379653;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egermuseum6))) {
                double lat=47.9040518;
                double lng=20.3774534;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egermuseum7))) {
                double lat=47.9001024;
                double lng=20.3740198;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egermuseum8))) {
                double lat=47.901712;
                double lng=20.3693688;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egertemplom1))) {
                double lat=47.8993289;
                double lng=20.3710419;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egertemplom2))) {
                double lat=47.9021424;
                double lng=20.3752444;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egertemplom3))) {
                double lat=47.9079947;
                double lng=20.3681148;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egertemplom4))) {
                double lat=47.9064777;
                double lng=20.3750527;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egermonuments1))) {
                double lat=47.9040518;
                double lng=20.3774534;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egermonuments2))) {
                double lat=47.9040518;
                double lng=20.3774534;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egermonuments3))) {
                double lat=47.9040262;
                double lng=20.3782185;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egermonuments4))) {
                double lat=47.903957;
                double lng=20.3776593;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egermonuments5))) {
                double lat=47.9046631;
                double lng=20.3741884;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egerother2))) {
                double lat=47.9001462;
                double lng=20.3726569;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egerother3))) {
                double lat=47.8984133;
                double lng=20.3801665;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egerother4))) {
                double lat=47.9004949;
                double lng=20.3660368;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            //PÉCS
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum1))) {
                double lat=46.0773908;
                double lng=18.2230137;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum2))) {
                double lat=46.0726386;
                double lng=18.2249645;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum3))) {
                double lat=46.0785646;
                double lng=18.221054;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum4))) {
                double lat=46.072237;
                double lng=18.2221122;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum5))) {
                double lat=46.0788438;
                double lng=18.2267868;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum6))) {
                double lat=46.0766305;
                double lng=18.2309945;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum7))) {
                double lat=46.0762209;
                double lng=18.2354356;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum8))) {
                double lat=46.077271;
                double lng=18.2452212;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom1))) {
                double lat=46.0738589;
                double lng=18.2232321;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom2))) {
                double lat=46.0752522;
                double lng=18.2224311;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom3))) {
                double lat=46.0770345;
                double lng=18.2257623;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom4))) {
                double lat=46.0741435;
                double lng=18.2188625;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom5))) {
                double lat=46.0769493;
                double lng=18.2320399;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom6))) {
                double lat=46.075954;
                double lng=18.203835;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom7))) {
                double lat=46.0786287;
                double lng=18.2236178;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom8))) {
                double lat=46.0766556;
                double lng=18.2234497;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom9))) {
                double lat=46.074892;
                double lng=18.2285562;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments1))) {
                double lat=46.0773776;
                double lng=18.2214592;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments2))) {
                double lat=46.0778378;
                double lng=18.2193047;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments3))) {
                double lat=46.0773762;
                double lng=18.2039496;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments4))) {
                double lat=46.0773735;
                double lng=18.2039496;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments5))) {
                double lat=46.07893;
                double lng=18.2215971;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments6))) {
                double lat=46.0778534;
                double lng=18.2210087;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments7))) {
                double lat=46.0773789;
                double lng=18.2215465;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments9))) {
                double lat=46.0764501;
                double lng=18.2259261;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments10))) {
                double lat=46.0752504;
                double lng=18.2265603;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsother1))) {
                double lat=46.0777617;
                double lng=18.2222228;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsother2))) {
                double lat=46.0753909;
                double lng=18.2423596;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsother3))) {
                double lat=46.0775223;
                double lng=18.2244006;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsother4))) {
                double lat=46.0763242;
                double lng=18.2284875;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsother5))) {
                double lat=46.075818;
                double lng=18.2265088;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            //SZEGED
            else if(placetitle.equals (getResources().getString(R.string.szegedmuseum1))) {
                double lat=46.2501173;
                double lng=20.1458641;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmuseum2))) {
                double lat=46.2522423;
                double lng=20.1489384;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmuseum3))) {
                double lat=46.2516199;
                double lng=20.1432357;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmuseum4))) {
                double lat=46.2518393;
                double lng=20.137555;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmuseum5))) {
                double lat=46.2460051;
                double lng=20.1467269;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmuseum6))) {
                double lat=46.252662;
                double lng=20.149028;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedtemplom1))) {
                double lat=46.2406015;
                double lng=20.1343618;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedtemplom2))) {
                double lat=46.2489098;
                double lng=20.1469379;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedtemplom3))) {
                double lat=46.2565648;
                double lng=20.1322785;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedtemplom4))) {
                double lat=46.2538745;
                double lng=20.141478;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedtemplom5))) {
                double lat=46.2493521;
                double lng=20.1475635;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedtemplom6))) {
                double lat=46.2537523;
                double lng=20.1398553;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmonuments1))) {
                double lat=46.2526651;
                double lng=20.1505455;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmonuments2))) {
                double lat=46.2551466;
                double lng=20.14817;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmonuments3))) {
                double lat=46.2542448;
                double lng=20.1502987;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmonuments4))) {
                double lat=46.2486067;
                double lng=20.1463703;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmonuments5))) {
                double lat=46.255279;
                double lng=20.1425635;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmonuments6))) {
                double lat=46.2563774;
                double lng=20.1490909;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmonuments7))) {
                double lat=46.2460051;
                double lng=20.1467269;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmonuments8))) {
                double lat=46.2576495;
                double lng=20.1483944;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmonuments9))) {
                double lat=46.2476294;
                double lng=20.1438307;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmonuments10))) {
                double lat=46.2562191;
                double lng=20.1490457;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedmonuments11))) {
                double lat=46.2533589;
                double lng=20.146697;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedother1))) {
                double lat=46.2407471;
                double lng=20.1341353;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedother2))) {
                double lat=46.2470482;
                double lng=20.1404639;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedother3))) {
                double lat=46.249616;
                double lng=20.1465198;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedother4))) {
                double lat=46.2667839;
                double lng=20.1067816;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedother5))) {
                double lat=46.2536776;
                double lng=20.1495615;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedother6))) {
                double lat=46.2582467;
                double lng=20.1480737;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedother7))) {
                double lat=46.2508192;
                double lng=20.1213621;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedother8))) {
                double lat=46.2542614;
                double lng=20.1484052;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.szegedother9))) {
                double lat=46.2483304;
                double lng=20.1465588;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            //VESZPRÉM
            else if(placetitle.equals (getResources().getString(R.string.veszpmuseum1))) {
                double lat=47.0968899;
                double lng=17.9004597;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszpmuseum2))) {
                double lat=47.0963527;
                double lng=17.9010344;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszpmuseum3))) {
                double lat=47.0951768;
                double lng=17.9034555;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszptemplom1))) {
                double lat=47.0960185;
                double lng=17.9038667;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszptemplom2))) {
                double lat=47.0969428;
                double lng=17.9006188;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszptemplom3))) {
                double lat=47.0912095;
                double lng=17.9029352;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszptemplom4))) {
                double lat=47.0969959;
                double lng=17.9007181;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszptemplom5))) {
                double lat=47.096161;
                double lng=17.9016633;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszptemplom6))) {
                double lat=47.0986044;
                double lng=17.8855273;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszpmonuments1))) {
                double lat=47.0947685;
                double lng=17.9034529;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszpmonuments2))) {
                double lat=47.0967623;
                double lng=17.9004738;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszpother1))) {
                double lat=47.1315369;
                double lng=17.752226;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszpother2))) {
                double lat=47.0964995;
                double lng=17.9008702;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszpother3))) {
                double lat=47.0973491;
                double lng=17.8940295;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszpother4))) {
                double lat=47.0946597;
                double lng=17.9040699;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszpother5))) {
                double lat=47.0900738;
                double lng=17.8903271;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            //TIHANY
            else if(placetitle.equals (getResources().getString(R.string.tihanymuseum1))) {
                double lat=46.9137329;
                double lng=17.8870437;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.tihanytemplom1))) {
                double lat=46.9139608;
                double lng=17.8873191;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.tihanymonuments1))) {
                double lat=46.9139608;
                double lng=17.8873191;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.tihanymonuments2))) {
                double lat=46.9152689;
                double lng=17.8888649;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.tihanyother2))) {
                double lat=46.9205442;
                double lng=17.8516349;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.tihanyother4))) {
                double lat=46.9228656;
                double lng=17.8790849;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }

        }
        else {
            //final String language = Paper.book().read("language");

            if (keyword.equals("Keszthely")) {
                double v = 46.7654716;
                double v1 = 17.2479554;
                getLatitudeAndLongitudeFromGoogleMapForAddress(keyword, v, v1);

                //if (language.equals("hu") || language.equals("en") || language.equals("de")) {
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.758834, 17.242172))
                        .title(getResources().getString(R.string.keszthmuseum1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.7707424, 17.2418793))
                        .title(getResources().getString(R.string.keszthmuseum3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.748101, 17.2273207))
                        .title(getResources().getString(R.string.keszthmuseum6)));

                if (language.equals("hu")) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(46.764614, 17.24184))
                            .title(getResources().getString(R.string.keszthmuseum2)));
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(46.766845, 17.235827))
                            .title(getResources().getString(R.string.keszthmuseum4)));
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(46.769314, 17.242457))
                            .title(getResources().getString(R.string.keszthmuseum5)));
                }
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7538965, 17.2401481))
                                .title(getResources().getString(R.string.keszthmonuments1))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.767877, 17.236742))
                                .title(getResources().getString(R.string.keszthmonuments2))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7610521, 17.2483782))
                                .title(getResources().getString(R.string.keszthmonuments3))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.765719, 17.243219))
                                .title(getResources().getString(R.string.keszthmonuments4))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7645622, 17.2402638))
                                .title(getResources().getString(R.string.keszthtemplom1))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7701547, 17.2515696))
                                .title(getResources().getString(R.string.keszthtemplom2))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);

                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7647133, 17.2436778))
                                .title(getResources().getString(R.string.keszthtemplom3))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);

                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.761552, 17.244678))
                                .title(getResources().getString(R.string.keszthtemplom4))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7657888, 17.2437286))
                                .title(getResources().getString(R.string.keszthother1))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7670602, 17.2426282))
                                .title(getResources().getString(R.string.keszthother2))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.757526, 17.251271))
                                .title(getResources().getString(R.string.keszthother3))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);


                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        if (marker.getTitle().equals(getResources().getString(R.string.keszthmuseum1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthmus1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthmuseum2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthmus2); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthmuseum4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthmus4); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthmuseum5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthmus5); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthmuseum3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthmus3); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthmuseum6))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthmus6); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthmonuments1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthmon1); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthmonuments2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthmon2); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthmonuments3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthmon3); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthmonuments4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthmon4); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthtemplom1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthtemp1); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthtemplom2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthtemp2); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthtemplom3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthtemp3); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthtemplom4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthtemp4); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthother1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthoth1); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthother2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthoth2); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.keszthother3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.keszthoth3); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        //return false;
                    }
                });
                //  }
            } else if (keyword.equals("Pécs")) {
                //LatLng keyword = new LatLng(46.7654716, 17.2479554);
                double v = 46.0727345;
                double v1 = 18.232266;
                getLatitudeAndLongitudeFromGoogleMapForAddress(keyword, v, v1);

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0773908, 18.2230137))
                        .title(getResources().getString(R.string.pecsmuseum1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0726386, 18.2249645))
                        .title(getResources().getString(R.string.pecsmuseum2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0785646, 18.221054))
                        .title(getResources().getString(R.string.pecsmuseum3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.072237, 18.2221122))
                        .title(getResources().getString(R.string.pecsmuseum4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0788438, 18.2267868))
                        .title(getResources().getString(R.string.pecsmuseum5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0766305, 18.2309945))
                        .title(getResources().getString(R.string.pecsmuseum6)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0762209, 18.2354356))
                        .title(getResources().getString(R.string.pecsmuseum7)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.077271, 18.2452212))
                        .title(getResources().getString(R.string.pecsmuseum8)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0738589, 18.2232321))
                        .title(getResources().getString(R.string.pecstemplom1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0752522, 18.2224311))
                        .title(getResources().getString(R.string.pecstemplom2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0770345, 18.2257623))
                        .title(getResources().getString(R.string.pecstemplom3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0741435, 18.2188625))
                        .title(getResources().getString(R.string.pecstemplom4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0769493, 18.2320399))
                        .title(getResources().getString(R.string.pecstemplom5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.075954, 18.203835))
                        .title(getResources().getString(R.string.pecstemplom6)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0786287, 18.2236178))
                        .title(getResources().getString(R.string.pecstemplom7)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0766556, 18.2234497))
                        .title(getResources().getString(R.string.pecstemplom8)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0773776, 18.2214592))
                        .title(getResources().getString(R.string.pecsmonuments1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0778378, 18.2193047))
                        .title(getResources().getString(R.string.pecsmonuments2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0773762, 18.2039496))
                        .title(getResources().getString(R.string.pecsmonuments3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0773735, 18.2039496))
                        .title(getResources().getString(R.string.pecsmonuments4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.07893, 18.2215971))
                        .title(getResources().getString(R.string.pecsmonuments5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0778534, 18.2210087))
                        .title(getResources().getString(R.string.pecsmonuments6)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0773789, 18.2215465))
                        .title(getResources().getString(R.string.pecsmonuments7)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0764501, 18.2259261))
                        .title(getResources().getString(R.string.pecsmonuments9)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0752504, 18.2265603))
                        .title(getResources().getString(R.string.pecsmonuments10)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0777617, 18.2222228))
                        .title(getResources().getString(R.string.pecsother1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0753909, 18.2423596))
                        .title(getResources().getString(R.string.pecsother2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0775223, 18.2244006))
                        .title(getResources().getString(R.string.pecsother3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0763242, 18.2284875))
                        .title(getResources().getString(R.string.pecsother4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.075818, 18.2265088))
                        .title(getResources().getString(R.string.pecsother5)));

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.getTitle().equals(getResources().getString(R.string.pecsmuseum1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmus1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmuseum2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmus2); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmuseum3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmus3); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmuseum4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmus4); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmuseum5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmus5); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmuseum6))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmus6); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmuseum7))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmus7); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmuseum8))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmus8); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmonuments1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmon1); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmonuments2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmon2); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmonuments3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmon3); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmonuments4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmon4); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmonuments5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmon5); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmonuments6))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmon6); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmonuments7))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmon7); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmonuments9))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmon9); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsmonuments10))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsmon10); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecstemplom1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecstemp1); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecstemplom2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecstemp2); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecstemplom3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecstemp3); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecstemplom4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecstemp4); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecstemplom5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecstemp5); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecstemplom6))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecstemp6); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecstemplom7))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecstemp7); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecstemplom8))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecstemp8); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecstemplom9))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecstemp9); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsother1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsoth1); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsother2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsoth2); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsother3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsoth3); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsother4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsoth4); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.pecsother5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.pecsoth5); //""
                            String placetitle = marker.getTitle();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);

                            //intent.putExtra("key1", lang);
                            startActivity(intent);

                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }

                        return false;
                    }
                });
                //  }
            } else if (keyword.equals("Veszprém")) {
                //LatLng keyword = new LatLng(46.7654716, 17.2479554);
                double v = 47.1028087;
                double v1 = 17.9093019;
                getLatitudeAndLongitudeFromGoogleMapForAddress(keyword, v, v1);

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0968899, 17.9004597))
                        .title(getResources().getString(R.string.veszpmuseum1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0963527, 17.9010344))
                        .title(getResources().getString(R.string.veszpmuseum2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0951768, 17.9034555))
                        .title(getResources().getString(R.string.veszpmuseum3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0960185, 17.9038667))
                        .title(getResources().getString(R.string.veszptemplom1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0969428, 17.9006188))
                        .title(getResources().getString(R.string.veszptemplom2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0912095, 17.9029352))
                        .title(getResources().getString(R.string.veszptemplom3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0969959, 17.9007181))
                        .title(getResources().getString(R.string.veszptemplom4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.096161, 17.9016633))
                        .title(getResources().getString(R.string.veszptemplom5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0986044, 17.8855273))
                        .title(getResources().getString(R.string.veszptemplom6)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0947685, 17.9034529))
                        .title(getResources().getString(R.string.veszpmonuments1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0967623, 17.9004738))
                        .title(getResources().getString(R.string.veszpmonuments2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.1315369, 17.752226))
                        .title(getResources().getString(R.string.veszpother1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0964995, 17.9008702))
                        .title(getResources().getString(R.string.veszpother2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0973491, 17.8940295))
                        .title(getResources().getString(R.string.veszpother3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0946597, 17.9040699))
                        .title(getResources().getString(R.string.veszpother4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0900738, 17.8903271))
                        .title(getResources().getString(R.string.veszpother5)));
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.getTitle().equals(getResources().getString(R.string.veszpmuseum1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszpmus1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszpmuseum2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszpmus2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszpmuseum3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszpmus3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszptemplom1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszptemp1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszptemplom2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszptemp2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszptemplom3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszptemp3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszptemplom4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszptemp4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszptemplom5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszptemp5); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszptemplom6))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszptemp6); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszpmonuments1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszpmon1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszpmonuments2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszpmon2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszpother1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszpoth1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszpother2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszpoth2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszpother3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszpoth3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszpother4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszpoth4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.veszpother5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.veszpoth5); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                //  }
            } else if (keyword.equals("Eger")) {
                //LatLng keyword = new LatLng(46.7654716, 17.2479554);
                double v = 47.9025348;
                double v1 = 20.3772284;
                getLatitudeAndLongitudeFromGoogleMapForAddress(keyword, v, v1);

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9032908, 20.3677279))
                        .title(getResources().getString(R.string.egermuseum1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9006942, 20.3713843))
                        .title(getResources().getString(R.string.egermuseum2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9040518, 20.3774534))
                        .title(getResources().getString(R.string.egermuseum3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.8997995, 20.3739947))
                        .title(getResources().getString(R.string.egermuseum4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9062166, 20.379653))
                        .title(getResources().getString(R.string.egermuseum5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9040518, 20.3774534))
                        .title(getResources().getString(R.string.egermuseum6)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9001024, 20.3740198))
                        .title(getResources().getString(R.string.egermuseum7)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.901712, 20.3693688))
                        .title(getResources().getString(R.string.egermuseum8)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.8993289, 20.3710419))
                        .title(getResources().getString(R.string.egertemplom1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9021424, 20.3752444))
                        .title(getResources().getString(R.string.egertemplom2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9079947, 20.3681148))
                        .title(getResources().getString(R.string.egertemplom3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9064777, 20.3750527))
                        .title(getResources().getString(R.string.egertemplom4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9040518, 20.3774534))
                        .title(getResources().getString(R.string.egermonuments1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9040518, 20.3774534))
                        .title(getResources().getString(R.string.egermonuments2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9040262, 20.3782185))
                        .title(getResources().getString(R.string.egermonuments3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.903957, 20.3776593))
                        .title(getResources().getString(R.string.egermonuments4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9046631, 20.3741884))
                        .title(getResources().getString(R.string.egermonuments5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9001462, 20.3726569))
                        .title(getResources().getString(R.string.egerother2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.8984133, 20.3801665))
                        .title(getResources().getString(R.string.egerother3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9004949, 20.3660368))
                        .title(getResources().getString(R.string.egerother4)));
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.getTitle().equals(getResources().getString(R.string.egermuseum1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermus1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egermuseum2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermus2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egermuseum3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermus3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egermuseum4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermus4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egermuseum5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermus5); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egermuseum6))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermus6); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egermuseum7))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermus7); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egermuseum8))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermus8); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egermonuments1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermon1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egermonuments2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermon2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egermonuments3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermon3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egermonuments4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermon4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egermonuments5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egermon5); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egertemplom1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egertemp1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egertemplom2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egertemp2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egertemplom3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egertemp3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egertemplom4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egertemp4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }  else if (marker.getTitle().equals(getResources().getString(R.string.egerother2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egeroth2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egerother3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egeroth3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.egerother4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.egeroth4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                //  }
            } else if (keyword.equals("Tihany")) {
                //LatLng keyword = new LatLng(46.7654716, 17.2479554);
                double v = 46.914131;
                double v1 = 17.887449;
                getLatitudeAndLongitudeFromGoogleMapForAddress(keyword, v, v1);

                if (language.equals("hu") || language.equals("de")) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(46.9137329, 17.8870437))
                            .title(getResources().getString(R.string.tihanymuseum1)));
                }
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.9139608, 17.8873191))
                        .title(getResources().getString(R.string.tihanytemplom1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.9139608, 17.8873191))
                        .title(getResources().getString(R.string.tihanymonuments1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.9152689, 17.8888649))
                        .title(getResources().getString(R.string.tihanymonuments2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.9205442, 17.8516349))
                        .title(getResources().getString(R.string.tihanyother2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.9228656, 17.8790849))
                        .title(getResources().getString(R.string.tihanyother4)));

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.getTitle().equals(getResources().getString(R.string.tihanymuseum1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.tihanymus1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.tihanytemplom1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.tihanytemp1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.tihanymonuments1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.tihanymon1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.tihanymonuments2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.tihanymon2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.tihanyother2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.tihanyoth2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.tihanyother4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.tihanyoth4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }


                        return false;
                    }
                });
                //  }
            } else if (keyword.equals("Szeged")) {
                //LatLng keyword = new LatLng(46.7654716, 17.2479554);
                double v = 46.2530102;
                double v1 = 20.1414253;
                getLatitudeAndLongitudeFromGoogleMapForAddress(keyword, v, v1);

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2501173, 20.1458641))
                        .title(getResources().getString(R.string.szegedmuseum1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2522423, 20.1489384))
                        .title(getResources().getString(R.string.szegedmuseum2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2516199, 20.1432357))
                        .title(getResources().getString(R.string.szegedmuseum3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2518393, 20.137555))
                        .title(getResources().getString(R.string.szegedmuseum4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2460051, 20.1467269))
                        .title(getResources().getString(R.string.szegedmuseum5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.252662, 20.149028))
                        .title(getResources().getString(R.string.szegedmuseum6)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2406015, 20.1343618))
                        .title(getResources().getString(R.string.szegedtemplom1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2489098, 20.1469379))
                        .title(getResources().getString(R.string.szegedtemplom2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2565648, 20.1322785))
                        .title(getResources().getString(R.string.szegedtemplom3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2538745, 20.141478))
                        .title(getResources().getString(R.string.szegedtemplom4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2493521, 20.1475635))
                        .title(getResources().getString(R.string.szegedtemplom5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2537523, 20.1398553))
                        .title(getResources().getString(R.string.szegedtemplom6)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2526651, 20.1505455))
                        .title(getResources().getString(R.string.szegedmonuments1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2551466, 20.14817))
                        .title(getResources().getString(R.string.szegedmonuments2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2542448, 20.1502987))
                        .title(getResources().getString(R.string.szegedmonuments3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2486067, 20.1463703))
                        .title(getResources().getString(R.string.szegedmonuments4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.255279, 20.1425635))
                        .title(getResources().getString(R.string.szegedmonuments5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2563774, 20.1490909))
                        .title(getResources().getString(R.string.szegedmonuments6)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2460051, 20.1467269))
                        .title(getResources().getString(R.string.szegedmonuments7)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2576495, 20.1483944))
                        .title(getResources().getString(R.string.szegedmonuments8)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2476294, 20.1438307))
                        .title(getResources().getString(R.string.szegedmonuments9)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2562191, 20.1490457))
                        .title(getResources().getString(R.string.szegedmonuments10)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2533589, 20.146697))
                        .title(getResources().getString(R.string.szegedmonuments11)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2407471, 20.1341353))
                        .title(getResources().getString(R.string.szegedother1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2470482, 20.1404639))
                        .title(getResources().getString(R.string.szegedother2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.249616, 20.1465198))
                        .title(getResources().getString(R.string.szegedother3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2667839, 20.1067816))
                        .title(getResources().getString(R.string.szegedother4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2536776, 20.1495615))
                        .title(getResources().getString(R.string.szegedother5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2582467, 20.1480737))
                        .title(getResources().getString(R.string.szegedother6)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2508192, 20.1213621))
                        .title(getResources().getString(R.string.szegedother7)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2542614, 20.1484052))
                        .title(getResources().getString(R.string.szegedother8)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2483304, 20.1465588))
                        .title(getResources().getString(R.string.szegedother9)));

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.getTitle().equals(getResources().getString(R.string.szegedmuseum1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmus1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmuseum2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmus2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmuseum3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmus3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmuseum4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmus4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmuseum5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmus5); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmuseum6))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmus6); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedtemplom1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegtemp1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedtemplom2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegtemp2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedtemplom3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegtemp3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedtemplom4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegtemp4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedtemplom5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegtemp5); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedtemplom6))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegtemp6); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmonuments1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmon1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmonuments2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmon2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmonuments3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmon3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmonuments4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmon4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmonuments5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmon5); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmonuments6))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmon6); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmonuments7))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmon7); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmonuments8))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmon8); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmonuments9))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmon9); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmonuments10))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmon10); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedmonuments11))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegmon11); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedother1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegoth1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedother2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegoth2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedother3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegoth3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedother4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegoth4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedother5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegoth5); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedother6))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegoth6); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedother7))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegoth7); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedother8))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegoth8); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.szegedother9))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.szegoth9); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }

                        return false;
                    }
                });
                //  }
            } else if (keyword.equals("Sopron")) {
                //LatLng keyword = new LatLng(46.7654716, 17.2479554);
                double v = 47.6816619;
                double v1 = 16.5844795;
                getLatitudeAndLongitudeFromGoogleMapForAddress(keyword, v, v1);

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6859625, 16.5877292))
                        .title(getResources().getString(R.string.sopronmuseum1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.68602, 16.589138))
                        .title(getResources().getString(R.string.sopronmuseum2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865675, 16.5887225))
                        .title(getResources().getString(R.string.sopronmuseum3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6861546, 16.5878403))
                        .title(getResources().getString(R.string.sopronmuseum4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6801243, 16.5757588))
                        .title(getResources().getString(R.string.sopronmuseum5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865343, 16.5884332))
                        .title(getResources().getString(R.string.sopronmuseum6)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865379, 16.5884332))
                        .title(getResources().getString(R.string.sopronmuseum7)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865675, 16.5887225))
                        .title(getResources().getString(R.string.sopronmuseum8)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6849766, 16.5882953))
                        .title(getResources().getString(R.string.sopronmuseum9)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6767011, 16.5726721))
                        .title(getResources().getString(R.string.soprontemplom1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6860906, 16.5882303))
                        .title(getResources().getString(R.string.soprontemplom2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6823196, 16.5877663))
                        .title(getResources().getString(R.string.soprontemplom3)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6842216, 16.5875043))
                        .title(getResources().getString(R.string.soprontemplom4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6860398, 16.5791263))
                        .title(getResources().getString(R.string.soprontemplom5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6889156, 16.5903373))
                        .title(getResources().getString(R.string.soprontemplom6)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6827106, 16.5930383))
                        .title(getResources().getString(R.string.soprontemplom7)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6817506, 16.5800903))
                        .title(getResources().getString(R.string.soprontemplom8)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6853287, 16.5894621))
                        .title(getResources().getString(R.string.soprontemplom9)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6888445, 16.5950182))
                        .title(getResources().getString(R.string.soprontemplom10)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6837786, 16.5886713))
                        .title(getResources().getString(R.string.soprontemplom11)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6928676, 16.5743193))
                        .title(getResources().getString(R.string.soprontemplom12)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6801826, 16.5943413))
                        .title(getResources().getString(R.string.soprontemplom13)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6857246, 16.5875402))
                        .title(getResources().getString(R.string.sopronmonuments1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865675, 16.5887225))
                        .title(getResources().getString(R.string.sopronmonuments2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6826123, 16.5801612))
                        .title(getResources().getString(R.string.sopronmonuments4)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6837866, 16.5860852))
                        .title(getResources().getString(R.string.sopronmonuments5)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865276, 16.5889952))
                        .title(getResources().getString(R.string.sopronmonuments7)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6863651, 16.5887217))
                        .title(getResources().getString(R.string.sopronmonuments8)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865439, 16.5888589))
                        .title(getResources().getString(R.string.sopronmonuments9)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865296, 16.589204))
                        .title(getResources().getString(R.string.sopronmonuments10)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6852996, 16.5882934))
                        .title(getResources().getString(R.string.sopronmonuments11)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6843078,16.5857144))
                        .title(getResources().getString(R.string.sopronother1)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6867339, 16.5875822))
                        .title(getResources().getString(R.string.sopronother2)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6771022, 16.5564406))
                        .title(getResources().getString(R.string.sopronother3)));

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.getTitle().equals(getResources().getString(R.string.sopronmuseum1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmus1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.sopronmuseum2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmus2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.sopronmuseum3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmus3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.sopronmuseum4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmus4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.sopronmuseum5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmus5); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.sopronmuseum6))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmus6); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.sopronmuseum7))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmus7); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.sopronmuseum8))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmus8); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.getTitle().equals(getResources().getString(R.string.sopronmuseum9))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmus9); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp5); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom6))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp6); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom7))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp7); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom8))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp8); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom9))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp9); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom10))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp10); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom11))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp11); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom12))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp12); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.soprontemplom13))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprtemp13); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.sopronmonuments1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmon1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.sopronmonuments2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmon2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.sopronmonuments4))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmon4); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.sopronmonuments5))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmon5); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.sopronmonuments7))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmon7); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.sopronmonuments8))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmon8); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.sopronmonuments9))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmon9); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.sopronmonuments10))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmon10); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.sopronmonuments11))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soprmon11); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.sopronother1))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soproth1); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.sopronother2))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soproth2); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals(getResources().getString(R.string.sopronother3))) {
                            Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                            //String keyword = extra.getString("key");
                            String keyword1 = getResources().getString(R.string.soproth3); //""
                            String placetitle = marker.getTitle();
                            // Toast.makeText(MapsActivity.this, "Clicked" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            // String placetitle = museum3.getText().toString();
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", placetitle);
                            intent.putExtra("key2", keyword1);
                            //intent.putExtra("key1", lang);
                            startActivity(intent);
                            // Toast.makeText(MapsActivity.this, "Clicked"+marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }





                        return false;
                    }
                });
                //  }

            }
        }
        //setPoiClick(mMap);
        //if(keyword.equals("Keszthely")) {




        /*mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener () {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(MapsActivity.this,PDescriptionActivity.class);
                // startActivity(intent);
                //Bundle extra = getIntent().getExtras();
                //String keyword = extra.getString("key");
                String keyword1="muzeumok/balatoni_muzeum";
                //Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
                // String placetitle = title.getText().toString();
                // String placetitle = museum3.getText().toString();
                //String keyword1= "muzeumok/helikon_kastelymuzeum_festetics_kastely";
                //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                intent.putExtra("key", keyword);
                //intent.putExtra("key1", placetitle);
                intent.putExtra("key2", keyword1);

                //intent.putExtra("key1", lang);
                startActivity(intent);

            }
        });*/

        //Enable the my-location layer over the map if location permissions are granted
       /* getLocationPermission();
        if (mLocationPermissionsGranted)
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
            {
                return;
            }

            googleMap.setMyLocationEnabled(true);
        }*

        //Move camera to the current device location
        getDeviceLocation(googleMap);*/

//keszthely

        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

//stílus
        /*try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }*/

       /* JsonTask getRequest = new JsonTask();
        try {
            String JSONString = getRequest.execute("https://maps.googleapis.com/maps/api/geocode/json?address="+keyword+"&sensor=false&key=AIzaSyCwh204CuSfjDv3thdb0oEpnUPS3zrlLG4").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       /* JSONObject jsonResponse1;
        String jsonMap1 = null;
        try {
            jsonResponse1 = new JSONObject(jsonMap1);
            JSONArray cast = jsonResponse1.getJSONArray("results");
            for (int i = 0; i < cast.length(); i++) {
                JSONObject actor = cast.getJSONObject(i);
                JSONObject name = actor.getJSONObject("geometry");
                JSONObject location = name.getJSONObject("location");
                String lat1 = location.getString("lat");
                String lng1 = location.getString("lng");
            }
        } catch (JSONException e) {
            //Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
        }*/

    }
    //Check if the location permission has been granted, if not, ask for it
    private void getLocationPermission()
    {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            mLocationPermissionsGranted = true;
        }
        else
        {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            getLocationPermission();
        }
    }
    //Get the current location & move the camera there
    private void getDeviceLocation(final GoogleMap googleMap)
    {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        final String language = Paper.book().read("language");

        try
        {
            if(mLocationPermissionsGranted)
            {
                final Task location = fusedLocationProviderClient.getLastLocation();

                location.addOnCompleteListener(new OnCompleteListener () {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            //Set current location
                            Location currentLocation = (Location) task.getResult();

                            //Move map camera to the current location w/ the default zoom level
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(),
                                    currentLocation.getLongitude()), 17));
                        } else {
                            //nyelvnek megfelelő hibaüzenet
                            if(language.equals ("hu"))
                            {
                                Toast.makeText(MapsActivity.this, "Sajnáljuk, nem tudtuk bemérni a jelenlegi tartózkodási helyét!",
                                        Toast.LENGTH_SHORT).show();}
                            else if(language.equals ("en"))
                            {
                                Toast.makeText(MapsActivity.this, "Unfortunately, we were unable to determine your current location.",
                                        Toast.LENGTH_SHORT).show();}
                            else if(language.equals ("de"))
                            {
                                Toast.makeText(MapsActivity.this, "Unfortunately, we were unable to determine your current location./de",
                                        Toast.LENGTH_SHORT).show();}
                        }
                    }
                });
            }
        }catch (SecurityException e)
        {
            Log.e("MapsActivity", "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }
    public boolean getLatitudeAndLongitudeFromGoogleMapForAddress(String searchedAddress, double v, double v1){

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        try {

            address = coder.getFromLocationName(searchedAddress,5);
            if (address == null) {
                Log.d(TAG, "############Address not correct #########");
            }
            Address location = address.get(0);
            LatLng keyword = new LatLng(v, v1);

            Log.d(TAG, "Address Latitude : "+ location.getLatitude()+ "Address Longitude : "+ location.getLongitude());
            //mMap.addMarker(new MarkerOptions().position(keyword).title("Marker in Keszthely"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(keyword));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            return true;

        }catch(Exception e){
            Log.d(TAG, "MY_ERROR : ############Address Not Found");
            return false;
        }
    }

/*
    private void setPoiClick(final GoogleMap map) {
        map.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest poi) {
                /*Marker poiMarker = mMap.addMarker(new MarkerOptions()
                        .position(poi.latLng)
                        .title(poi.name));
                Double latitude = poiMarker.getPosition().latitude;
                Double longitude = poiMarker.getPosition().longitude;
                Log.d("marker", String.valueOf (latitude));
                Log.d("marker", String.valueOf (longitude));

                //leírás activityre átírányít
                poiMarker.showInfoWindow();
                Bundle extra = getIntent().getExtras();
                String keyword = extra.getString("key");

                Intent intent = new Intent(MapsActivity.this, PDescriptionActivity.class);
               // String placetitle = title.getText().toString();
                // String placetitle = museum3.getText().toString();
                //String keyword1= "muzeumok/helikon_kastelymuzeum_festetics_kastely";
                //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                intent.putExtra("key", keyword);
                //intent.putExtra("key1", placetitle);
                intent.putExtra("key2", keyword1);

                //intent.putExtra("key1", lang);
                startActivity(intent);
            }
        });
    }*/
    /*
    public void onInfoWindowClick(Marker marker) {
        //Get memo ID via the converter in the Communications class using marker.getId() TODO: Make convertMarkerToMemo function in Communications

        fragmentManager.beginTransaction().show(viewMemoFragment).addToBackStack("viewMemo").commit();
        findViewById(R.id.FrameLayoutFragment).setClickable(true);

        //TODO: Provide the MemoID to ViewMemoFragment class, so it can ask for the details of the selected memo from the Server via Communications

        //Disable the currently active menu button
        activeMenuButton.setActivated(false);
        activeMenuButton = null;
    }*/

    /*

private class JsonTask extends AsyncTask<String, String, String> {

    protected void onPreExecute() {
        super.onPreExecute();
        // u can use a dialog here
    }

    protected String doInBackground(String... params) {


        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

            }

            return buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // here "result" is json as stting
    }
}*/
}

