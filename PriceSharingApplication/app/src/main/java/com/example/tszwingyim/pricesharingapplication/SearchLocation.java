package com.example.tszwingyim.pricesharingapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.SupportMapFragment;

public class SearchLocation extends ActionBarActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_search_location);
        Button recommendation = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button goods = (Button) findViewById(R.id.button_goods);

        createMapView();
        createStreetView();
        addMarker();

//        GoogleMap googleMap; // Might be null if Google Play services APK is not available.
//            setUpMapIfNeeded();

    googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                /**
                 * Ensure the street view has been initialised correctly and
                 * pass it through the selected lat/long co-ordinates.
                 */
                if (m_StreetView != null) {

                    /**
                     * Hide the map view to expose the street view.
                     */
                    Fragment mapView = getFragmentManager().findFragmentById(R.id.mapView);
                    getFragmentManager().beginTransaction().hide(mapView).commit();

                    /** Passed the tapped location through to the Street View **/
                    m_StreetView.setPosition(latLng);
                }
            }
        });
//        if (googleMap != null) {
//            googleMap.setMyLocationEnabled(true);
//            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//        }
        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(SearchLocation.this, Member.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(SearchLocation.this, MainActivity.class);
                startActivity(intent);

            }
        });
        recommendation.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(SearchLocation.this, Recommendation.class);
                startActivity(intent);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(SearchLocation.this, Barcode.class);
                startActivity(intent);

            }
        });
        goods.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(SearchLocation.this, Search.class);
                startActivity(intent);

            }
        });


        Spinner district = (Spinner) findViewById(R.id.spinner_searchdistrict);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.searchdistrict_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        district.setAdapter(adapter);
        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoComplete_searchlocation);
// Get the string array
        String[] shoplocation = getResources().getStringArray(R.array.searcharea_array);
// Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter3 =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shoplocation);
        textView.setAdapter(adapter3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_location, menu);
        return true;
    }

    /**
     * Initialises the mapview
     */
    GoogleMap googleMap;

    private void createMapView() {
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */

        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if (null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }
    }
//    UiSettings mapSettings;
//    mapSettings = googleMap.getUiSettings();
    /**
     * Adds a marker to the map
     */
    private void addMarker() {

        /** Make sure that the map has been initialised **/
        if (null != googleMap) {
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(0, 0))
                            .title("Marker")
                            .draggable(true)
            );
        }
    }

    StreetViewPanorama m_StreetView;

    private void createStreetView() {
        m_StreetView = ((StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.streetView)).getStreetViewPanorama();
    }

    // Set up the onClickListener that will pass the selected lat/long
    //co-ordinates through to the Street View fragment for loading
//    public class MapsActivity extends FragmentActivity implements
//            GoogleApiClient.ConnectionCallbacks,
//            GoogleApiClient.OnConnectionFailedListener {
//        @Override
//        public void onConnected(Bundle bundle) {
//            Log.i(TAG, "Location services connected.");
//        }
//
//        @Override
//        public void onConnectionSuspended(int i) {
//            Log.i(TAG, "Location services suspended. Please reconnect.");
//        }
//
//        @Override
//        protected void onPause() {
//            super.onPause();
//            if (mGoogleApiClient.isConnected()) {
//                mGoogleApiClient.disconnect();
//            }
//        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

//        @Override
//        public void onConnectionFailed(ConnectionResult connectionResult) {
//
//        }
    }



