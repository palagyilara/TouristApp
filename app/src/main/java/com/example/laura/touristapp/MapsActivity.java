package com.example.laura.touristapp;

import android.Manifest;
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

        Bundle extra = getIntent().getExtras();
        //String text = extra.getString(MainActivity.TAG_TEXT);
        String keyword = extra.getString("key");
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

        Bundle extra = getIntent().getExtras();
        //String text = extra.getString(MainActivity.TAG_TEXT);
        String keyword = extra.getString("key");
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        if (keyword.equals("Keszthely")) {
            double v=46.7654716;
            double v1=17.2479554;
            getLatitudeAndLongitudeFromGoogleMapForAddress(keyword,v,v1);
        }
        else if (keyword.equals("Pécs")){
            //LatLng keyword = new LatLng(46.7654716, 17.2479554);
            double v=46.0799687;
            double v1=18.2298533;
            getLatitudeAndLongitudeFromGoogleMapForAddress(keyword,v,v1);
    }
        setPoiClick(mMap);

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


    private void setPoiClick(final GoogleMap map) {
        map.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest poi) {
                Marker poiMarker = mMap.addMarker(new MarkerOptions()
                        .position(poi.latLng)
                        .title(poi.name));
                Double latitude = poiMarker.getPosition().latitude;
                Double longitude = poiMarker.getPosition().longitude;
                Log.d("marker", String.valueOf (latitude));
                Log.d("marker", String.valueOf (longitude));

                //leírás activityre átírányít
                poiMarker.showInfoWindow();
            }
        });
    }
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
