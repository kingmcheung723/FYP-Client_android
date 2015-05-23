package com.example.tszwingyim.comp4521;


public class MyStringTokenizer extends java.util.StringTokenizer {
    public MyStringTokenizer(String string, String delimiters) {
        super(string, delimiters);
    }

    @Override
    public String nextToken() {
        if (this.hasMoreTokens()) {
            return super.nextToken();
        } else {
            return null;
        }
    }
}
