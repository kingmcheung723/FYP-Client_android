package com.example.tszwingyim.comp4521;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


interface QueryCallBack {
    void queryResult(String result);
}

public class DBManager {
    public QueryCallBack queryCallBack;
    public static final String SUCCESS = "Success";

    private static final String url = "jdbc:mysql://comp4521.ddns.net:8889/comp4521_database?useUnicode=yes&characterEncoding=UTF-8";
    private static final String user = "fyp_admin";
    private static final String password = null;
    private static final String JDBCDriverName = "com.mysql.jdbc.Driver";

    private boolean isQueryingDB = false;

    public DBManager() {
        try {
            Class.forName(JDBCDriverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            queryCallBack.queryResult(null);
        }
    }

    public void querySql(String sql) {
        if (!this.isQueryingDB) {
            this.isQueryingDB = true;
            try {
                new queryOperation().execute(sql);
            } catch (Exception e) {

                isQueryingDB = false;

                e.printStackTrace();
                queryCallBack.queryResult(null);
            }
        }
    }

    public void updateSql(String sql) {
        if (!this.isQueryingDB) {
            try {
                this.isQueryingDB = true;
                new updateIperation().execute(sql);
            } catch (Exception e) {

                isQueryingDB = false;

                e.printStackTrace();
                queryCallBack.queryResult(null);
            }
        }
    }

    private class updateIperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Connection con = DriverManager.getConnection(url, user, password);
                String sql = params[0];
                Statement st = con.createStatement();
                int result = st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                isQueryingDB = false;

                if (result == 1) {
                    return DBManager.SUCCESS;
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();

                isQueryingDB = false;

                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            queryCallBack.queryResult(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
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
                while (rs.next()) {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        result += rs.getString(i) + "|";
                    }
                }
                if (result.equalsIgnoreCase("")) {
                    result = "";
                }

                isQueryingDB = false;

                return result;
            } catch (Exception e) {
                e.printStackTrace();

                isQueryingDB = false;

                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            queryCallBack.queryResult(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
