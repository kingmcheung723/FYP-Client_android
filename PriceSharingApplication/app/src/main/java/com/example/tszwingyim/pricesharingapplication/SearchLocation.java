package com.example.tszwingyim.pricesharingapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchLocation extends ActionBarActivity implements LocationListener {
    private String[] shopStr = {"選擇商店", "惠康", "百佳", "MarketPlace", "永旺", "大昌", "所有商店"};
    private String[] districtStr = {"選擇地區", "港島區", "銅鑼灣", "炮台山", "北角", "鰂魚涌", "筲箕灣", "金鐘", "中環", "西環", "太平山", "薄扶林", "灣仔", "柴灣", "香港仔", "鴨脷洲", "淺水灣", "赤柱",
            "九龍區", "九龍城", "九龍塘", "觀塘", "荔枝角", "美孚", "深水埗", "石硤尾", "大角咀", "油塘", "黃大仙", "油尖旺", "油塘", "鑽石山", "新界區", "北區", "將軍澳", "西貢", "大埔", "沙田", "西貢", "荃灣", "屯門", "元朗", "葵青", "離島"};
    private int selectedShopId = -1;
    private int selectedDistrictPosition = -1;
    private GoogleMap googleMap = null;

    // GoogleMap googleMap;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }

        setContentView(R.layout.activity_search_location);
        Spinner spinnerOsversions;
        TextView selVersion;

            MapFragment mapFragment = (MapFragment) getFragmentManager()
                    .findFragmentById(R.id.map);
            googleMap=mapFragment.getMap();
//        mapFragment.getMapAsync(this);
            googleMap.setMyLocationEnabled(true);
            String locationProvider = LocationManager.NETWORK_PROVIDER;

            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String bestProvider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(bestProvider);
            if(location!=null)

            {
                onLocationChanged(location);
            }

            locationManager.requestLocationUpdates(locationProvider,0,0,locationListener);



        //declare buttons
        Button recommendation = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button goods = (Button) findViewById(R.id.button_goods);
        Button search = (Button)findViewById(R.id.button_itempage);

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



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (googleMap != null) {
                    DBManager dbManager = new DBManager();
                    dbManager.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null) {
                                MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                while (token.hasMoreTokens()) {
                                    String shopNameZH = token.nextToken();
                                    String latitude = token.nextToken();
                                    String longtitude = token.nextToken();
                                    double lat = Double.parseDouble(latitude);
                                    double longt = Double.parseDouble(longtitude);

                                    googleMap.addMarker(new MarkerOptions()
                                            .position(new LatLng(lat, longt))
                                            .title(shopNameZH));
                                }
                            }
                        }
                    };
                    String sql = null;
                    if (selectedDistrictPosition != -1 && selectedShopId != -1) {
                        String district = districtStr[selectedDistrictPosition];
                        if (selectedShopId == 1000) {
                            sql = "SELECT latitude,longitude FROM locations WHERE district_zh like '%" + district + "%'";
                        } else if (selectedShopId >= 1 && selectedShopId <= 5) {
                            sql = "SELECT shop_name_zh, latitude,longitude FROM locations WHERE shop_id = " + Integer.toString(selectedShopId) + " AND district_zh like '%" + district + "%'";
                        }
                        if (sql != null) {
                            dbManager.querySql(sql);
                        }
                    }
                }
            }
        });

        //declare google map fragment
//        MapFragment mapFragment = (MapFragment) getFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        Spinner shop = (Spinner) findViewById(R.id.spinner_district);
        Spinner district = (Spinner) findViewById(R.id.spinner_region);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> shopAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, shopStr); //selected item will look like a spinner set from XML
        shopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districtStr); //selected item will look like a spinner set from XML
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner

        district.setAdapter(districtAdapter);
        shop.setAdapter(shopAdapter);

        shop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedShopId = -1;
                switch (position) {
                    case 0:
                        break;
                    case 6:
                        selectedShopId = 1000;
                        break;
                    default:
                        selectedShopId = position;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDistrictPosition = -1;
                switch (position) {
                    case 0:
                        break;
                    default:
                        selectedDistrictPosition = position;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // Adds a marker to the map



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_location, menu);
        return true;
    }

//    public void onMapReady(GoogleMap map) {
//
//googleMap = map;
//    }


    public void onLocationChanged(Location location) {
        TextView locationTv = (TextView) findViewById(R.id.textView3);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

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

}




