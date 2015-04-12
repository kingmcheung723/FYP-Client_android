package com.example.tszwingyim.facebookshare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;


public class MainActivity extends ActionBarActivity {
    CallbackManager callbackManager;
//    ShareDialog shareDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_button);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle("Cheapest!!")
                .setContentDescription(
                        "I found a cheap goods through Price Sharing Application")
                .setContentUrl(Uri.parse("http://www.tvb.com"))
                .build();
        shareButton.setShareContent(content);
//        ShareDialog.show(MainActivity.this, content);
        callbackManager = CallbackManager.Factory.create();
//        shareDialog = new ShareDialog(this);
//        shareOnFacebook("This is test...");
//        this part is optional

    }
//    private void shareOnFacebook(String content)
//    {
//        Log.e("Test", "inside shareOnFacebook()");
//    if (shareDialog.canShow(ShareLinkContent.class)) {
//        ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                .setContentTitle("Hello Facebook")
//                .setContentDescription(
//                        "The 'Hello Facebook' sample  showcases simple Facebook integration")
////                .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
//                .build();
//
//        shareDialog.show(linkContent);
//    }
//    else
//    {
//        Toast.makeText(this, "NOT CALLED", Toast.LENGTH_SHORT).show();
//    }
//}

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.e("Test", "Yes, Uuu bitch!!!");
    }
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
