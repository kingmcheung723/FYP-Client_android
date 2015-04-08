package com.example.tszwingyim.pricesharingapplication;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Dialog;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;

import java.net.MalformedURLException;
import java.net.URL;

public class SearchLocation extends ActionBarActivity implements OnMapReadyCallback, Spinner.OnItemSelectedListener {

   // GoogleMap googleMap;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_search_location);
        Spinner spinnerOsversions;
        TextView selVersion;

        //declare buttons
        Button recommendation = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button goods = (Button) findViewById(R.id.button_goods);
        //set onclick listener for tabs
        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String memberEmail = MySharedPreference.getMemberName(SearchLocation.this);
                if (memberEmail != null && memberEmail.length() > 0) {
                    Intent intent = TabManager.getInstance().getIntent(SearchLocation.this, Memberpage.class);
                    startActivity(intent);
                } else {
                    Intent intent = TabManager.getInstance().getIntent(SearchLocation.this, Member.class);
                    startActivity(intent);
                }
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
                Intent intent = new Intent(SearchLocation.this, Search.class);
                startActivity(intent);

            }
        });
        //declare google map fragment
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


//        //configure the google map
//        GoogleMapOptions options = new GoogleMapOptions();
//        options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
//                .zoomControlsEnabled(true)
//                .zoomGesturesEnabled(true)
//                .compassEnabled(true)
//                .rotateGesturesEnabled(true)
//                .scrollGesturesEnabled(true)
//                .tiltGesturesEnabled(true);

        //set center of the map
      //  googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(22.294698, 114.200783), 5));

        Spinner districthk = (Spinner) findViewById(R.id.spinner_district);
        Spinner place = (Spinner) findViewById(R.id.spinner_region);
        districthk.setOnItemSelectedListener(this);
        place.setOnItemSelectedListener(this);


        String[] shopStr = { "選擇商店","所有商店","百佳","惠康","MarketPlace","永旺","大昌" };
        String[] districtStr = {"選擇地區","港島區","銅鑼灣","炮台山","北角","鰂魚涌","筲箕灣","金鐘","中環","西環","太平山","薄扶林","灣仔","柴灣","香港仔","鴨脷洲","淺水灣","赤柱",
                "九龍區","九龍城","九龍塘","觀塘","荔枝角","美孚","深水埗","石硤尾", "大角咀","油塘", "黃大仙","油尖旺", "油塘","鑽石山","新界區","北區","將軍澳","西貢","大埔","沙田","西貢","荃灣","屯門","元朗","葵青","離島"};

        // add spinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, shopStr); //selected item will look like a spinner set from XML
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districtStr); //selected item will look like a spinner set from XML
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner

        place.setAdapter(adapter);
        districthk.setAdapter(adapter2);

//        // Get a reference to the AutoCompleteTextView in the layout
//        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoComplete_searchlocation);

//        // Get the string array
//        String[] shoplocation = getResources().getStringArray(R.array.searcharea_array);
//        // Create the adapter and set it to the AutoCompleteTextView
//        ArrayAdapter<String> adapter3 =
//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shoplocation);
//        textView.setAdapter(adapter3);
//bound the map to hong kong

    }

//    GoogleMap mMap;
//        //  addMarker();
//        // Getting Google Play availability status
//        private void setUpMapIfNeeded() {
//            // Do a null check to confirm that we have not already instantiated the map.
//            if (mMap == null) {
//                // Try to obtain the map from the SupportMapFragment.
//                mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
//                        .getMap();
//                mMap.setMyLocationEnabled(true);
//                // Check if we were successful in obtaining the map.
//                if (mMap != null) {
//                    mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//                        @Override
//                        public void onMyLocationChange(Location arg0) {
//                            // TODO Auto-generated method stub
//                            mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!"));
//                        }
//                    });
//
//                }
//            }
//        }

    // Adds a marker to the map
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(22.387844,114.19492))
                .title("Lantau")
                .snippet("Population: 4,137,400"));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(22.26821,114.1515451))
                .title("Stanley")
                .snippet("Population: 4,137,400"));
    }

    @Override
    public void onItemSelected(AdapterView <?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Log.d("","");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        Log.d("", "");
    }

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




