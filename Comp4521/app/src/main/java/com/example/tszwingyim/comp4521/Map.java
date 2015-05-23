package com.example.tszwingyim.comp4521;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Map extends ActionBarActivity {
    private GoogleMap googleMap = null;
    private long MIN_TIME_BW_UPDATES = 5000;
    private float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_map);
        Button info = (Button) findViewById(R.id.button_info);
        Button comment = (Button) findViewById(R.id.button_comment);
        Button login = (Button) findViewById(R.id.button_login);
        Button map = (Button) findViewById(R.id.button_map);
        Button promo = (Button) findViewById(R.id.button_promo);
        Button logout = (Button) findViewById(R.id.button_logout);
        Button register = (Button) findViewById(R.id.button4);

        final String facility = getIntent().getExtras().getString("Facilities");

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Map.this, Map.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);
            }
        });
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Map.this,Comment.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map.this, Information.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);

            }
        });
        promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Map.this, Commentform.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String member = MySharedPreference.getMemberName(Map.this);
                if (member != null && member.length() > 0) {
                    MySharedPreference.displayDialog("You have already logged in", Map.this);
                } else {
                    Intent intent =new Intent(Map.this, Login.class);
                    intent.putExtra("Facilities", facility);
                    startActivity(intent);
                }

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String member = MySharedPreference.getMemberName(Map.this);
                if (member != null && member.length() > 0) {
                    MySharedPreference.clearMemberName(Map.this);
                    MySharedPreference.displayDialog("Logout success", Map.this);
                } else {
                    MySharedPreference.displayDialog("You haven't log in", Map.this);
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Map.this, Register.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);

            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        googleMap=mapFragment.getMap();
        googleMap.setMyLocationEnabled(true);
        String locationProvider = LocationManager.NETWORK_PROVIDER;

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(bestProvider);

//            locationManager.requestLocationUpdates(locationProvider,0,0,locationListener);
        boolean isNetworkEnabled = true;
        if (isNetworkEnabled) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES,
                    new android.location.LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            // Currnet location
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            LatLng latLng = new LatLng(latitude, longitude);
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

                            setGoogleMapLocation(facility);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    });
        }
    }

    private void setGoogleMapLocation(final String name) {
        DBManager dbManager = new DBManager();
        dbManager.queryCallBack = new QueryCallBack() {
            @Override
            public void queryResult(String result) {
                if (result != null) {
                    MyStringTokenizer token = new MyStringTokenizer(result, "|");
                    if (token != null) {
                        String lat = token.nextToken();
                        String longi = token.nextToken();
                        if (lat != null ||
                                longi != null) {

                            Double latDouble = Double.parseDouble(lat);
                            Double longiDouble = Double.parseDouble(longi);
                            googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(latDouble, longiDouble))
                                    .title(name));
                        }
                    }
                }

            }
        };

        String sql = "SELECT LAT, LONGI FROM Facilities WHERE NAME = '" + name + "'";
        dbManager.querySql(sql);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

//    @Override
//    public void onMapReady(GoogleMap map) {
//        LatLng sydney = new LatLng(-33.867, 151.206);
//
//        map.setMyLocationEnabled(true);
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
//
//        map.addMarker(new MarkerOptions()
//                .title("Sydney")
//                .snippet("The most populous city in Australia.")
//                .position(sydney));
//    }

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
