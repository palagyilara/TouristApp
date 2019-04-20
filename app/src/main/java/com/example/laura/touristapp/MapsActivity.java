package com.example.laura.touristapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
   // private List<Marker> markers = new ArrayList<Marker>();
    //private double currentLatitude;
   // private double currentLongitude;
    public static final String CHANNEL_ID = "tourist_notifications";
    public final int NOTIFICATION_ID=001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        final Bundle extra = getIntent().getExtras();
        //String text = extra.getString(MainActivity.TAG_TEXT);
        final String keyword = extra.getString("key");
        //String keyword = extra.getString("key2");
        String placetitle = extra.getString("key1");
        final String language = Paper.book().read("language");


        //mMap.setOnMyLocationChangeListener(this);

        int height = 71;
        int width = 50;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.museumicon);
        BitmapDrawable bitmapdraw2=(BitmapDrawable)getResources().getDrawable(R.drawable.monumentsicon);
        BitmapDrawable bitmapdraw3=(BitmapDrawable)getResources().getDrawable(R.drawable.churchicon);
        BitmapDrawable bitmapdraw4=(BitmapDrawable)getResources().getDrawable(R.drawable.othericon);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap b2=bitmapdraw2.getBitmap();
        Bitmap b3=bitmapdraw3.getBitmap();
        Bitmap b4=bitmapdraw4.getBitmap();
        Bitmap museumicon = Bitmap.createScaledBitmap(b, width, height, false);
        Bitmap monumenticon = Bitmap.createScaledBitmap(b2, width, height, false);
        Bitmap churchicon = Bitmap.createScaledBitmap(b3, width, height, false);
        Bitmap othericon = Bitmap.createScaledBitmap(b4, width, height, false);




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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            }
            else if(placetitle.equals (getResources().getString(R.string.keszthmuseum2))){//"Cadillac Múzeum")) {
                double lat=46.7628359;
                double lng=17.2420221;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
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
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
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
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.egerother2))) {
                double lat=47.8983915;
                double lng=20.382371053698108;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            //PÉCS
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum1))) {
                double lat=46.0774363;
                double lng=18.2251688;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum2))) {
                double lat=46.0728161;
                double lng=18.224205;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum3))) {
                double lat=46.0780827;
                double lng=18.2229969;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum4))) {
                double lat=46.072237;
                double lng=18.2243009;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum5))) {
                double lat=46.0786386;
                double lng=18.2268778;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum6))) {
                double lat=46.0768488;
                double lng=18.2318707;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum7))) {
                double lat=46.0761733;
                double lng=18.2356353;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmuseum8))) {
                double lat=46.0777739;
                double lng=18.2456665;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom1))) {
                double lat=46.073992;
                double lng=18.225395;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom3))) {
                double lat=46.077047;
                double lng=18.227954;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom4))) {
                double lat=46.074151;
                double lng=18.221052;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom6))) {
                double lat=46.076399;
                double lng=18.207258;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom8))) {
                double lat=46.0765762;
                double lng=18.2238443;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecstemplom9))) {
                double lat=46.074157;
                double lng=18.231098;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments1))) {
                double lat=46.077386;
                double lng=18.221459;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments2))) {
                double lat=46.077847;
                double lng=18.221493;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments3))) {
                double lat=46.076281;
                double lng=18.228343;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments4))) {
                double lat=46.074060;
                double lng=18.229488;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments5))) {
                double lat=46.078938;
                double lng=18.223787;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments6))) {
                double lat=46.0778534;
                double lng=18.2231974;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments7))) {
                double lat=46.0773789;
                double lng=18.2237352;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments9))) {
                double lat=46.0764501;
                double lng=18.2281148;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsmonuments10))) {
                double lat=46.0752504;
                double lng=18.228749;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsother1))) {
                double lat=46.0777617;
                double lng=18.2244115;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsother2))) {
                double lat=46.0753909;
                double lng=18.2445483;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsother3))) {
                double lat=46.0775223;
                double lng=18.2265893;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsother4))) {
                double lat=46.0763242;
                double lng=18.2306762;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsother5))) {
                double lat=46.0936665;
                double lng=18.2257789;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsother6))) {
                double lat=46.0993215;
                double lng=18.2201211;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.pecsother7))) {
                double lat=46.0758308;
                double lng=18.2288294;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addr));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            }
            else if(placetitle.equals (getResources().getString(R.string.veszpother6))) {
                double lat=47.0919435;
                double lng=17.9046887;
                LatLng addr = new LatLng(lat, lng);
                googleMap.addMarker (new MarkerOptions ( )
                                .position (new LatLng (lat, lng))
                                .title (placetitle)
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(museumicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                        .title(getResources().getString(R.string.keszthmuseum1))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.7707424, 17.2418793))
                        .title(getResources().getString(R.string.keszthmuseum3))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.748101, 17.2273207))
                        .title(getResources().getString(R.string.keszthmuseum6))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));

                if (language.equals("hu")) {
                   googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(46.7628359,17.2420221))
                            .title(getResources().getString(R.string.keszthmuseum2))
                            .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(46.766845, 17.235827))
                            .title(getResources().getString(R.string.keszthmuseum4))
                            .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                  googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(46.769314, 17.242457))
                            .title(getResources().getString(R.string.keszthmuseum5))
                            .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                }
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7538965, 17.2401481))
                                .title(getResources().getString(R.string.keszthmonuments1))
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.767877, 17.236742))
                                .title(getResources().getString(R.string.keszthmonuments2))
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7610521, 17.2483782))
                                .title(getResources().getString(R.string.keszthmonuments3))
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.765719, 17.243219))
                                .title(getResources().getString(R.string.keszthmonuments4))
                                .icon(BitmapDescriptorFactory.fromBitmap(monumenticon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7645622, 17.2402638))
                                .title(getResources().getString(R.string.keszthtemplom1))
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7701547, 17.2515696))
                                .title(getResources().getString(R.string.keszthtemplom2))
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);

                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7647133, 17.2436778))
                                .title(getResources().getString(R.string.keszthtemplom3))
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);

                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.761552, 17.244678))
                                .title(getResources().getString(R.string.keszthtemplom4))
                                .icon(BitmapDescriptorFactory.fromBitmap(churchicon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7657888, 17.2437286))
                                .title(getResources().getString(R.string.keszthother1))
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.7670602, 17.2426282))
                                .title(getResources().getString(R.string.keszthother2))
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
                    /*.snippet ("RandomUSer @RandUsi")
                    .icon (bitmapDescriptorFromVector (this, R.drawable.mk_starred))*/);
                googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(46.757526, 17.251271))
                                .title(getResources().getString(R.string.keszthother3))
                                .icon(BitmapDescriptorFactory.fromBitmap(othericon))
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
                        .position(new LatLng(46.0774363, 18.2251688))
                        .title(getResources().getString(R.string.pecsmuseum1))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0728161, 18.224205))
                        .title(getResources().getString(R.string.pecsmuseum2))
                    .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0780827, 18.2229969))
                        .title(getResources().getString(R.string.pecsmuseum3))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.072237, 18.2243009))
                        .title(getResources().getString(R.string.pecsmuseum4))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0786386, 18.2268778))
                        .title(getResources().getString(R.string.pecsmuseum5))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0768488, 18.2318707))
                        .title(getResources().getString(R.string.pecsmuseum6))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0761733, 18.2356353))
                        .title(getResources().getString(R.string.pecsmuseum7))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0777739, 18.2456665))
                        .title(getResources().getString(R.string.pecsmuseum8))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.073992, 18.225395))
                        .title(getResources().getString(R.string.pecstemplom1))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0752522, 18.2224311))
                        .title(getResources().getString(R.string.pecstemplom2))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.077047, 18.227954))
                        .title(getResources().getString(R.string.pecstemplom3))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.074151,18.221052))
                        .title(getResources().getString(R.string.pecstemplom4))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0769493, 18.2320399))
                        .title(getResources().getString(R.string.pecstemplom5))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.076399, 18.207258))
                        .title(getResources().getString(R.string.pecstemplom6))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0786287, 18.2236178))
                        .title(getResources().getString(R.string.pecstemplom7))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.076788, 18.223839))
                        .title(getResources().getString(R.string.pecstemplom8))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.074157, 18.231098))
                        .title(getResources().getString(R.string.pecstemplom9))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.077386, 18.221459))
                        .title(getResources().getString(R.string.pecsmonuments1))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.077847, 18.221493))
                        .title(getResources().getString(R.string.pecsmonuments2))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.076281, 18.228343))
                        .title(getResources().getString(R.string.pecsmonuments3))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.074060, 18.229488))
                        .title(getResources().getString(R.string.pecsmonuments4))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.078938, 18.223787))
                        .title(getResources().getString(R.string.pecsmonuments5))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0778534, 18.2231974))
                        .title(getResources().getString(R.string.pecsmonuments6))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0773789, 18.2237352))
                        .title(getResources().getString(R.string.pecsmonuments7))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0764501, 18.2281148))
                        .title(getResources().getString(R.string.pecsmonuments9))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0752504, 18.228749))
                        .title(getResources().getString(R.string.pecsmonuments10))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0777617, 18.2244115))
                        .title(getResources().getString(R.string.pecsother1))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0753909, 18.2445483))
                        .title(getResources().getString(R.string.pecsother2))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0775223, 18.2265893))
                        .title(getResources().getString(R.string.pecsother3))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0763242, 18.2306762))
                        .title(getResources().getString(R.string.pecsother4))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0936665,18.2257789))
                        .title(getResources().getString(R.string.pecsother5))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0993215,18.2201211))
                        .title(getResources().getString(R.string.pecsother6))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.0758308,18.2288294))
                        .title(getResources().getString(R.string.pecsother7))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));


                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
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

                        //return false;
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
                        .title(getResources().getString(R.string.veszpmuseum1))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0963527, 17.9010344))
                        .title(getResources().getString(R.string.veszpmuseum2))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0951768, 17.9034555))
                        .title(getResources().getString(R.string.veszpmuseum3))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0960185, 17.9038667))
                        .title(getResources().getString(R.string.veszptemplom1))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0969428, 17.9006188))
                        .title(getResources().getString(R.string.veszptemplom2))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0912095, 17.9029352))
                        .title(getResources().getString(R.string.veszptemplom3))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0969959, 17.9007181))
                        .title(getResources().getString(R.string.veszptemplom4))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.096161, 17.9016633))
                        .title(getResources().getString(R.string.veszptemplom5))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0986044, 17.8855273))
                        .title(getResources().getString(R.string.veszptemplom6))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0947685, 17.9034529))
                        .title(getResources().getString(R.string.veszpmonuments1))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0967623, 17.9004738))
                        .title(getResources().getString(R.string.veszpmonuments2))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.1315369, 17.752226))
                        .title(getResources().getString(R.string.veszpother1))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0964995, 17.9008702))
                        .title(getResources().getString(R.string.veszpother2))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0973491, 17.8940295))
                        .title(getResources().getString(R.string.veszpother3))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0946597, 17.9040699))
                        .title(getResources().getString(R.string.veszpother4))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0900738, 17.8903271))
                        .title(getResources().getString(R.string.veszpother5))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.0919435,17.9046887))
                        .title(getResources().getString(R.string.veszpother6))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));

               mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
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
                        //return false;
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
                        .title(getResources().getString(R.string.egermuseum1))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9006942, 20.3713843))
                        .title(getResources().getString(R.string.egermuseum2))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9040518, 20.3774534))
                        .title(getResources().getString(R.string.egermuseum3))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.8997995, 20.3739947))
                        .title(getResources().getString(R.string.egermuseum4))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9062166, 20.379653))
                        .title(getResources().getString(R.string.egermuseum5))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9040518, 20.3774534))
                        .title(getResources().getString(R.string.egermuseum6))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9001024, 20.3740198))
                        .title(getResources().getString(R.string.egermuseum7))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.901712, 20.3693688))
                        .title(getResources().getString(R.string.egermuseum8))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.8993289, 20.3710419))
                        .title(getResources().getString(R.string.egertemplom1))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9021424, 20.3752444))
                        .title(getResources().getString(R.string.egertemplom2))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9079947, 20.3681148))
                        .title(getResources().getString(R.string.egertemplom3))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9064777, 20.3750527))
                        .title(getResources().getString(R.string.egertemplom4))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9040518, 20.3774534))
                        .title(getResources().getString(R.string.egermonuments1))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9040518, 20.3774534))
                        .title(getResources().getString(R.string.egermonuments2))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9040262, 20.3782185))
                        .title(getResources().getString(R.string.egermonuments3))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.903957, 20.3776593))
                        .title(getResources().getString(R.string.egermonuments4))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9046631, 20.3741884))
                        .title(getResources().getString(R.string.egermonuments5))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.8983915, 20.382371053698108))
                        .title(getResources().getString(R.string.egerother2))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.8984133, 20.3801665))
                        .title(getResources().getString(R.string.egerother3))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.9004949, 20.3660368))
                        .title(getResources().getString(R.string.egerother4))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
               mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
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
                        //return false;
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
                            .title(getResources().getString(R.string.tihanymuseum1))
                            .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                }
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.9139608, 17.8873191))
                        .title(getResources().getString(R.string.tihanytemplom1))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.9139608, 17.8873191))
                        .title(getResources().getString(R.string.tihanymonuments1))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.9152689, 17.8888649))
                        .title(getResources().getString(R.string.tihanymonuments2))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.9205442, 17.8516349))
                        .title(getResources().getString(R.string.tihanyother2))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.9228656, 17.8790849))
                        .title(getResources().getString(R.string.tihanyother4))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));

               mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
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


                        //return false;
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
                        .title(getResources().getString(R.string.szegedmuseum1))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2522423, 20.1489384))
                        .title(getResources().getString(R.string.szegedmuseum2))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2516199, 20.1432357))
                        .title(getResources().getString(R.string.szegedmuseum3))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2518393, 20.137555))
                        .title(getResources().getString(R.string.szegedmuseum4))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2460051, 20.1467269))
                        .title(getResources().getString(R.string.szegedmuseum5))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.252662, 20.149028))
                        .title(getResources().getString(R.string.szegedmuseum6))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2406015, 20.1343618))
                        .title(getResources().getString(R.string.szegedtemplom1))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2489098, 20.1469379))
                        .title(getResources().getString(R.string.szegedtemplom2))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2565648, 20.1322785))
                        .title(getResources().getString(R.string.szegedtemplom3))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2538745, 20.141478))
                        .title(getResources().getString(R.string.szegedtemplom4))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2493521, 20.1475635))
                        .title(getResources().getString(R.string.szegedtemplom5))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2537523, 20.1398553))
                        .title(getResources().getString(R.string.szegedtemplom6))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2526651, 20.1505455))
                        .title(getResources().getString(R.string.szegedmonuments1))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2551466, 20.14817))
                        .title(getResources().getString(R.string.szegedmonuments2))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2542448, 20.1502987))
                        .title(getResources().getString(R.string.szegedmonuments3))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2486067, 20.1463703))
                        .title(getResources().getString(R.string.szegedmonuments4))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.255279, 20.1425635))
                        .title(getResources().getString(R.string.szegedmonuments5))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2563774, 20.1490909))
                        .title(getResources().getString(R.string.szegedmonuments6))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2460051, 20.1467269))
                        .title(getResources().getString(R.string.szegedmonuments7))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2576495, 20.1483944))
                        .title(getResources().getString(R.string.szegedmonuments8))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2476294, 20.1438307))
                        .title(getResources().getString(R.string.szegedmonuments9))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2562191, 20.1490457))
                        .title(getResources().getString(R.string.szegedmonuments10))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2533589, 20.146697))
                        .title(getResources().getString(R.string.szegedmonuments11))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2407471, 20.1341353))
                        .title(getResources().getString(R.string.szegedother1))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2470482, 20.1404639))
                        .title(getResources().getString(R.string.szegedother2))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.249616, 20.1465198))
                        .title(getResources().getString(R.string.szegedother3))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2667839, 20.1067816))
                        .title(getResources().getString(R.string.szegedother4))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2536776, 20.1495615))
                        .title(getResources().getString(R.string.szegedother5))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2582467, 20.1480737))
                        .title(getResources().getString(R.string.szegedother6))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2508192, 20.1213621))
                        .title(getResources().getString(R.string.szegedother7))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2542614, 20.1484052))
                        .title(getResources().getString(R.string.szegedother8))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(46.2483304, 20.1465588))
                        .title(getResources().getString(R.string.szegedother9))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));

               mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
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

                        //return false;
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
                        .title(getResources().getString(R.string.sopronmuseum1))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.68602, 16.589138))
                        .title(getResources().getString(R.string.sopronmuseum2))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865675, 16.5887225))
                        .title(getResources().getString(R.string.sopronmuseum3))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6861546, 16.5878403))
                        .title(getResources().getString(R.string.sopronmuseum4))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6801243, 16.5757588))
                        .title(getResources().getString(R.string.sopronmuseum5))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865343, 16.5884332))
                        .title(getResources().getString(R.string.sopronmuseum6))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865379, 16.5884332))
                        .title(getResources().getString(R.string.sopronmuseum7))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865675, 16.5887225))
                        .title(getResources().getString(R.string.sopronmuseum8))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6849766, 16.5882953))
                        .title(getResources().getString(R.string.sopronmuseum9))
                        .icon(BitmapDescriptorFactory.fromBitmap(museumicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6767011, 16.5726721))
                        .title(getResources().getString(R.string.soprontemplom1))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6860906, 16.5882303))
                        .title(getResources().getString(R.string.soprontemplom2))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6823196, 16.5877663))
                        .title(getResources().getString(R.string.soprontemplom3))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6842216, 16.5875043))
                        .title(getResources().getString(R.string.soprontemplom4))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6860398, 16.5791263))
                        .title(getResources().getString(R.string.soprontemplom5))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6889156, 16.5903373))
                        .title(getResources().getString(R.string.soprontemplom6))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6827106, 16.5930383))
                        .title(getResources().getString(R.string.soprontemplom7))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6817506, 16.5800903))
                        .title(getResources().getString(R.string.soprontemplom8))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6853287, 16.5894621))
                        .title(getResources().getString(R.string.soprontemplom9))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6888445, 16.5950182))
                        .title(getResources().getString(R.string.soprontemplom10))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6837786, 16.5886713))
                        .title(getResources().getString(R.string.soprontemplom11))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6928676, 16.5743193))
                        .title(getResources().getString(R.string.soprontemplom12))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6801826, 16.5943413))
                        .title(getResources().getString(R.string.soprontemplom13))
                        .icon(BitmapDescriptorFactory.fromBitmap(churchicon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6857246, 16.5875402))
                        .title(getResources().getString(R.string.sopronmonuments1))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865675, 16.5887225))
                        .title(getResources().getString(R.string.sopronmonuments2))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6826123, 16.5801612))
                        .title(getResources().getString(R.string.sopronmonuments4))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6837866, 16.5860852))
                        .title(getResources().getString(R.string.sopronmonuments5))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865276, 16.5889952))
                        .title(getResources().getString(R.string.sopronmonuments7))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6863651, 16.5887217))
                        .title(getResources().getString(R.string.sopronmonuments8))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865439, 16.5888589))
                        .title(getResources().getString(R.string.sopronmonuments9))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6865296, 16.589204))
                        .title(getResources().getString(R.string.sopronmonuments10))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6852996, 16.5882934))
                        .title(getResources().getString(R.string.sopronmonuments11))
                        .icon(BitmapDescriptorFactory.fromBitmap(monumenticon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6843078,16.5857144))
                        .title(getResources().getString(R.string.sopronother1))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6867339, 16.5875822))
                        .title(getResources().getString(R.string.sopronother2))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(47.6771022, 16.5564406))
                        .title(getResources().getString(R.string.sopronother3))
                        .icon(BitmapDescriptorFactory.fromBitmap(othericon)));

               mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
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

                        //return false;
                    }
                });
                //  }

            }
        }

        //Enable the my-location layer over the map if location permissions are granted
        getLocationPermission();
        if (mLocationPermissionsGranted)
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
            {
                return;
            }

            googleMap.setMyLocationEnabled(true);
        }

        //Move camera to the current device location
        getDeviceLocation(googleMap);

        //add location button click listener
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener(){
            @Override
            public boolean onMyLocationButtonClick()
            {
                getDeviceLocation(googleMap);
                return true;
            }
        });

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
                            String cityName = "Not Found";
                            //Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                            Geocoder coder = new Geocoder(MapsActivity.this);
                            List<Address> address;
                            //Set current location
                            Location currentLocation = (Location) task.getResult();

                            //Move map camera to the current location w/ the default zoom level
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(),
                                    currentLocation.getLongitude()), 17));
                            try {
                                /*List<Address> addresses = gcd.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(),
                                        10);*/
                                address = coder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(),5);

                                Log.d(TAG, "Address Latitude : " + currentLocation.getLatitude() + "Address Longitude : " + currentLocation.getLongitude());

                                for (Address adrs : address) {
                                    if (adrs != null) {
                                        String city = adrs.getLocality();
                                        if (city != null && !city.equals("")) {
                                            cityName = city;
                                            System.out.println("city :  " + cityName);
                                        } else {
                                            System.out.println("hiba!  ");
                                        }

                                    }

                                }
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            createNotificationChannel();
                            if(cityName.equals("Keszthely"))
                            {
                                /*Location A=new Location("LocationA");
                                A.setLatitude(currentLocation.getLatitude());
                                A.setLongitude(currentLocation.getLongitude());

                                Location B=new Location("LocationB");//hévíz koordinátái
                                B.setLatitude(46.79200);
                                B.setLongitude(17.18767);*/

                                NumberFormat formatter = new DecimalFormat("##.00");
                                float[] result=new float[1];
                                Location.distanceBetween (currentLocation.getLatitude(),currentLocation.getLongitude(),46.79200, 17.18767,result);
                                String distance=formatter.format(result[0]/1000);
                                //double distance=A.distanceTo(B);
                                //System.out.println(distance);

                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.kesztnotifurl)));
                                PendingIntent pendingIntent = PendingIntent.getActivity(MapsActivity.this, 0, intent, 0);
                                //mBuilder.setContentIntent(pendingIntent);
                                //értesítés küldése
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(MapsActivity.this,CHANNEL_ID)
                                        .setSmallIcon(R.drawable.sign)
                                        .setColor(getResources().getColor(R.color.colorAccent))
                                        .setContentTitle(getResources().getString(R.string.notiftitle))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftitle)))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km"))
                                        .setContentText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km")
                                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                                        .setDefaults(Notification.DEFAULT_ALL)
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true);
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MapsActivity.this);

                                // notificationId is a unique int for each notification that you must define
                                notificationManager.notify(NOTIFICATION_ID, builder.build());

                            }
                            else if(cityName.equals("Eger"))
                            {
                                Location A=new Location("LocationA");
                                A.setLatitude(currentLocation.getLatitude());
                                A.setLongitude(currentLocation.getLongitude());

                                Location B=new Location("LocationB");//Egerszalók koordinátái
                                B.setLatitude(47.8772783);
                                B.setLongitude(20.2546512);

                                NumberFormat formatter = new DecimalFormat("##.00");
                                float[] result=new float[1];
                                Location.distanceBetween (currentLocation.getLatitude(),currentLocation.getLongitude(),47.8772783, 20.2546512,  result);
                                String distance=formatter.format(result[0]/1000);
                                //double distance=A.distanceTo(B);
                                //System.out.println(distance);
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.egernotifurl)));
                                PendingIntent pendingIntent = PendingIntent.getActivity(MapsActivity.this, 0, intent, 0);
                                //mBuilder.setContentIntent(pendingIntent);
                                //értesítés küldése
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(MapsActivity.this,CHANNEL_ID)
                                        .setSmallIcon(R.drawable.sign)
                                        .setColor(getResources().getColor(R.color.colorAccent))
                                        .setContentTitle(getResources().getString(R.string.notiftitle))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftitle)))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km"))
                                        .setContentText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km")
                                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                                        .setDefaults(Notification.DEFAULT_ALL)
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true);
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MapsActivity.this);

                                // notificationId is a unique int for each notification that you must define
                                notificationManager.notify(NOTIFICATION_ID, builder.build());

                            }
                            else if(cityName.equals("Pécs"))
                            {
                                Location A=new Location("LocationA");
                                A.setLatitude(currentLocation.getLatitude());
                                A.setLongitude(currentLocation.getLongitude());

                                Location B=new Location("LocationB");//Harkány koordinátái
                                B.setLatitude(45.8384895);
                                B.setLongitude(18.1070513);

                                NumberFormat formatter = new DecimalFormat("##.00");
                                float[] result=new float[1];
                                Location.distanceBetween (currentLocation.getLatitude(),currentLocation.getLongitude(),45.8384895, 18.1070513,  result);
                                String distance=formatter.format(result[0]/1000);
                                //double distance=A.distanceTo(B);
                                //System.out.println(distance);
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.pecsnotifurl)));
                                PendingIntent pendingIntent = PendingIntent.getActivity(MapsActivity.this, 0, intent, 0);
                                //mBuilder.setContentIntent(pendingIntent);
                                //értesítés küldése
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(MapsActivity.this,CHANNEL_ID)
                                        .setSmallIcon(R.drawable.sign)
                                        .setColor(getResources().getColor(R.color.colorAccent))
                                        .setContentTitle(getResources().getString(R.string.notiftitle))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftitle)))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km"))
                                        .setContentText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km")
                                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                                        .setDefaults(Notification.DEFAULT_ALL)
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true);
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MapsActivity.this);

                                // notificationId is a unique int for each notification that you must define
                                notificationManager.notify(NOTIFICATION_ID, builder.build());
                            }
                            else if(cityName.equals("Sopron"))
                            {
                                Location A=new Location("LocationA");
                                A.setLatitude(currentLocation.getLatitude());
                                A.setLongitude(currentLocation.getLongitude());

                                Location B=new Location("LocationB");//Fertőrákos koordinátái
                                B.setLatitude(47.7254102);
                                B.setLongitude(16.5643462);

                                NumberFormat formatter = new DecimalFormat("##.00");
                                float[] result=new float[1];
                                Location.distanceBetween (currentLocation.getLatitude(),currentLocation.getLongitude(),47.7254102, 16.5643462,  result);
                                String distance=formatter.format(result[0]/1000);
                                //double distance=A.distanceTo(B);
                                //System.out.println(distance);
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.sopronnotifurl)));
                                PendingIntent pendingIntent = PendingIntent.getActivity(MapsActivity.this, 0, intent, 0);
                                //mBuilder.setContentIntent(pendingIntent);
                                //értesítés küldése
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(MapsActivity.this,CHANNEL_ID)
                                        .setSmallIcon(R.drawable.sign)
                                        .setColor(getResources().getColor(R.color.colorAccent))
                                        .setContentTitle(getResources().getString(R.string.notiftitle))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftitle)))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km"))
                                        .setContentText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km")
                                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                                        .setDefaults(Notification.DEFAULT_ALL)
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true);
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MapsActivity.this);

                                // notificationId is a unique int for each notification that you must define
                                notificationManager.notify(NOTIFICATION_ID, builder.build());
                            }
                            else if(cityName.equals("Tihany"))
                            {
                                Location A=new Location("LocationA");
                                A.setLatitude(currentLocation.getLatitude());
                                A.setLongitude(currentLocation.getLongitude());

                                Location B=new Location("LocationB");//Balatonfüred koordinátái
                                B.setLatitude(46.9615058);
                                B.setLongitude(17.8185803);

                                NumberFormat formatter = new DecimalFormat("##.00");
                                float[] result=new float[1];
                                Location.distanceBetween (currentLocation.getLatitude(),currentLocation.getLongitude(),46.9615058, 17.8185803,  result);
                                String distance=formatter.format(result[0]/1000);
                                //double distance=A.distanceTo(B);
                                //System.out.println(distance);
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.tihanynotifurl)));
                                PendingIntent pendingIntent = PendingIntent.getActivity(MapsActivity.this, 0, intent, 0);
                                //mBuilder.setContentIntent(pendingIntent);
                                //értesítés küldése
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(MapsActivity.this,CHANNEL_ID)
                                        .setSmallIcon(R.drawable.sign)
                                        .setColor(getResources().getColor(R.color.colorAccent))
                                        .setContentTitle(getResources().getString(R.string.notiftitle))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftitle)))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km"))
                                        .setContentText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km")
                                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                                        .setDefaults(Notification.DEFAULT_ALL)
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true);
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MapsActivity.this);

                                // notificationId is a unique int for each notification that you must define
                                notificationManager.notify(NOTIFICATION_ID, builder.build());
                            }
                            else if(cityName.equals("Szeged"))
                            {
                                Location A=new Location("LocationA");
                                A.setLatitude(currentLocation.getLatitude());
                                A.setLongitude(currentLocation.getLongitude());

                                Location B=new Location("LocationB");//Mórahalom koordinátái
                                B.setLatitude(46.2132871);
                                B.setLongitude(19.9639141);

                                NumberFormat formatter = new DecimalFormat("##.00");
                                float[] result=new float[1];
                                Location.distanceBetween (currentLocation.getLatitude(),currentLocation.getLongitude(),46.2132871, 19.9639141,  result);
                                String distance=formatter.format(result[0]/1000);
                                //double distance=A.distanceTo(B);
                                //System.out.println(distance);
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.szegednotifurl)));
                                PendingIntent pendingIntent = PendingIntent.getActivity(MapsActivity.this, 0, intent, 0);
                                //mBuilder.setContentIntent(pendingIntent);
                                //értesítés küldése
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(MapsActivity.this,CHANNEL_ID)
                                        .setSmallIcon(R.drawable.sign)
                                        .setColor(getResources().getColor(R.color.colorAccent))
                                        .setContentTitle(getResources().getString(R.string.notiftitle))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftitle)))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km"))
                                        .setContentText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km")
                                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                                        .setDefaults(Notification.DEFAULT_ALL)
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true);
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MapsActivity.this);

                                // notificationId is a unique int for each notification that you must define
                                notificationManager.notify(NOTIFICATION_ID, builder.build());
                            }
                            else if(cityName.equals("Veszprém"))
                            {
                                Location A=new Location("LocationA");
                                A.setLatitude(currentLocation.getLatitude());
                                A.setLongitude(currentLocation.getLongitude());

                                Location B=new Location("LocationB");//Balatonalmádi koordinátái
                                B.setLatitude(47.0853602);
                                B.setLongitude(17.9109363);

                                NumberFormat formatter = new DecimalFormat("##.00");
                                float[] result=new float[1];
                                Location.distanceBetween (currentLocation.getLatitude(),currentLocation.getLongitude(),47.0853602, 17.9109363,  result);
                                String distance=formatter.format(result[0]/1000);
                                //double distance=A.distanceTo(B);
                                //System.out.println(distance);
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.veszpnotifurl)));
                                PendingIntent pendingIntent = PendingIntent.getActivity(MapsActivity.this, 0, intent, 0);
                                //mBuilder.setContentIntent(pendingIntent);
                                //értesítés küldése
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(MapsActivity.this,CHANNEL_ID)
                                        .setSmallIcon(R.drawable.sign)
                                        .setColor(getResources().getColor(R.color.colorAccent))
                                        .setContentTitle(getResources().getString(R.string.notiftitle))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftitle)))
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km"))
                                        .setContentText(getResources().getString(R.string.notiftext)+"\n"+(getResources().getString(R.string.distance))+distance+"km")
                                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                                        .setDefaults(Notification.DEFAULT_ALL)
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true);
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MapsActivity.this);

                                // notificationId is a unique int for each notification that you must define
                                notificationManager.notify(NOTIFICATION_ID, builder.build());
                            }
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
                                Toast.makeText(MapsActivity.this, "Leider konnten wir Ihren aktuellen Standort nicht ermitteln.",
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

    /*private void displayNotification(View view) {
        createNotificationChannel();

        // Create an explicit intent for an Activity in your app


        //Create the intent that’ll fire when the user taps the notification//

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.heviz.hu/hu"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //mBuilder.setContentIntent(pendingIntent);
        //értesítés küldése
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.museumicon)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("jasbckjsacachlj javjskvjsalifjsl hvkahvjashva lahvjlasvhashv"))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }*/
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Tourist Notifications";
            String description = "Include all the tourist notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public boolean getLatitudeAndLongitudeFromGoogleMapForAddress(String searchedAddress, double v, double v1){

        //String cityName = "Not Found";
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
}

