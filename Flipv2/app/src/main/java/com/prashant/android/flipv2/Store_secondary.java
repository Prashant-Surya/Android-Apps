package com.prashant.android.flipv2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class Store_secondary extends ActionBarActivity {
    ImageView img;
    String store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_secondary);
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        store = intent.getStringExtra("store");
        byte[] decoded = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodeBit = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
        img = (ImageView) findViewById(R.id.storeLarge);
        img.setImageBitmap(decodeBit);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_store_secondary, menu);
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
    public class getCashBack extends AsyncTask<String,Void,String>{
        HttpClient httpClient;
        HttpGet httpGet;
        HttpResponse httpResponse;
        String res;
        JSONObject json,jsonResponse;
        JSONArray jarray;
        @Override
        protected String doInBackground(String... params) {
            String store = params[0];
            String url = "http://flippaisa.com/iprashant/getcashback.php?store="+store;
            httpClient = new DefaultHttpClient();
            httpGet = new HttpGet(url);
            try {
                httpResponse = httpClient.execute(httpGet);
                res = EntityUtils.toString(httpResponse.getEntity());
                jsonResponse = new JSONObject(res);
                jarray = jsonResponse.getJSONArray(store);
                for(int i=0;i<jarray.length();i++){
                    json = jarray.getJSONObject(i);
                    if(store.equalsIgnoreCase("flipkart")){

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
