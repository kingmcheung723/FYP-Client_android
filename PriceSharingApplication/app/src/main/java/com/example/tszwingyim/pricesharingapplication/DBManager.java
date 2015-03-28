package com.example.tszwingyim.pricesharingapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DBManager {
    private static final String url = "jdbc:mysql://10.0.2.2:3306/fyp_db?useUnicode=yes&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = null;

    public void testDB() {

        new LongOperation().execute("");
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);
            /* System.out.println("Databaseection success"); */

                String result = "Database connection success\n";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from shops");
                ResultSetMetaData rsmd = rs.getMetaData();

                while(rs.next()) {
                    result += rsmd.getColumnName(1) + ": " + rs.getInt(1) + "\n";
                    result += rsmd.getColumnName(2) + ": " + rs.getString(2) + "\n";
                    result += rsmd.getColumnName(3) + ": " + rs.getString(3) + "\n";
                }
            }
            catch(Exception e) {
                e.printStackTrace();
                Log.d("MYSQL", e.toString());
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("", result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
