package com.example.tszwingyim.comp4521;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    LocationManager locationManager;
    String LAT = null;
    String LONGI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        Button others = (Button) findViewById(R.id.button_others);
        Button sports = (Button) findViewById(R.id.button_sports);
        Button canteens = (Button) findViewById(R.id.button_canteens);
        others.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(MainActivity.this, Everylistview.class);
                intent.putExtra("category", "others");
                intent.putExtra("LAT",LAT);
                intent.putExtra("LONGI",LONGI);
;                startActivity(intent);
            }
        });
        canteens.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(MainActivity.this, Everylistview.class);
                intent.putExtra("category", "dinning");
                intent.putExtra("LAT",LAT);
                intent.putExtra("LONGI",LONGI);
                startActivity(intent);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(MainActivity.this, Everylistview.class);
                intent.putExtra("category", "sports");
                intent.putExtra("LAT",LAT);
                intent.putExtra("LONGI",LONGI);
                startActivity(intent);

            }
        });

        // Set Service
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String commdStr = null;
        commdStr = LocationManager.NETWORK_PROVIDER;
        locationManager.requestLocationUpdates(commdStr, 0, 0, locationListener);

    }
    private LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
   /*
    * Called when a new location is found by the network location
    * provider.
    */
            LONGI = String.valueOf(location.getLongitude());
            LAT = String.valueOf(location.getLatitude());

            System.out.println("經度:" + LONGI + ", 緯度:"
                    + LAT);


//            DBManager dbManager = new DBManager();
//            dbManager.queryCallBack = new QueryCallBack() {
//                @Override
//                public void queryResult(String result) {
//                    if (result != null) {
//                        System.out.println(result);
//                    }
//                }
//            };

//            String sql = "SELECT NAME,(6378100 * acos( cos( radians(22.337435  ) ) * cos( radians( lat ) )* cos( radians(";
//            String temp = String.valueOf(location.getLongitude());
//            sql =sql.concat(temp);
//            sql = sql.concat(") - radians(114.263764 ) ) + sin( radians(22.337435 ) ) * sin(radians(");
//            temp = String.valueOf(location.getLatitude());
//            sql =sql.concat(temp);
//            sql = sql.concat(")) ) ) AS distance FROM Facilities HAVING distance ORDER BY distance ASC");
//            dbManager.querySql(sql);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
