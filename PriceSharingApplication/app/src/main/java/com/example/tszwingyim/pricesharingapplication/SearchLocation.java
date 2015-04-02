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

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchLocation extends ActionBarActivity implements OnMapReadyCallback {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_search_location);
        //declare buttons
        Button recommendation = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button goods = (Button) findViewById(R.id.button_goods);
      //  addMarker();
        //set onclick listener for tabs
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
        //declare google map fragment
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //configure the google map
        GoogleMapOptions options = new GoogleMapOptions();
        options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
                .compassEnabled(false)
                .rotateGesturesEnabled(false)
                .tiltGesturesEnabled(false);
        // add spinner
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

    /**
     * Adds a marker to the map
     */
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(22.192295, 114.130359))
                .title("Hello world"));
    }

//    GoogleMap googleMap;
//    private void addMarker(){
//
//        /** Make sure that the map has been initialised **/
//        if(null != googleMap){
//            googleMap.addMarker(new MarkerOptions()
//                            .position(new LatLng(22.206321, 114.133534))
//                            .title("Marker")
//                            .draggable(true)
//            );
//        }
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_location, menu);
        return true;
    }



        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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

    }




