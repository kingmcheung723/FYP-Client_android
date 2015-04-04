package com.example.tszwingyim.pricesharingapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


interface QueryCallBack {
    void queryResult(String result);
}

public class DBManager{
    private static final String url = "jdbc:mysql://10.0.2.2:3306/fyp_database?useUnicode=yes&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = null;
    private static final String JDBCDriverName = "com.mysql.jdbc.Driver";
    public QueryCallBack queryCallBack;

    public DBManager() {
        try {
            Class.forName(JDBCDriverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            queryCallBack.queryResult(null);
        }
    }

    public void querySql(String sql) {
        try {
            new queryOperation().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            queryCallBack.queryResult(null);
        }
    }

    public void insertSql(String sql) {
        try {
            new insertOperation().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            queryCallBack.queryResult(null);
        }
    }

    private class insertOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Connection con = DriverManager.getConnection(url, user, password);
                String sql = params[0];
                Statement st = con.createStatement();
                st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                return null;
            }
            catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("", result);
            queryCallBack.queryResult(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    private class queryOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Connection con = DriverManager.getConnection(url, user, password);

                String sql = params[0];
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                String result = "";
                while(rs.next()) {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        result +=  rs.getString(i) + "|";
                    }
                }
                if (result.equalsIgnoreCase("")) {
                    result = "";
                }
                return result;
            }
            catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("", result);
            queryCallBack.queryResult(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
