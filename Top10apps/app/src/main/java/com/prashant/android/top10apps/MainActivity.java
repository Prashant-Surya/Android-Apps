package com.prashant.android.top10apps;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    Button btnParse;
    ListView listApps;
    String xmlData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnParse=(Button)findViewById(R.id.btnParse);
        listApps=(ListView)findViewById(R.id.listApps);


        btnParse.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //To be updated later
                ParseApplications parse = new ParseApplications(xmlData);
                boolean operationStatus = parse.process();
                if(operationStatus){
                    ArrayList<Application> apps = parse.getApplications();
                    ArrayAdapter<Application> adapter = new ArrayAdapter<Application>(MainActivity.this,R.layout.list_item,apps);
                    listApps.setVisibility(listApps.VISIBLE);
                    listApps.setAdapter(adapter);
                }else{
                    Log.d("Main Activity","Erro opstatus");
                }
            }
        });
        new downloads().execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml");
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


    private class downloads extends AsyncTask<String,Void,String>{
        String xmldata="";
        @Override
        protected String doInBackground(String... urls) {
                try {
                    xmldata=downloadXML(urls[0]);
                } catch (IOException e) {
                    return "Failed to download";
                }
            return "";
        }
        protected void onPostExecute(String result){
            Log.d("On post Execute",xmldata);
            xmlData=xmldata;
        }
        protected String downloadXML(String theurl) throws IOException{
            int BUF_SIZE=2000;
            InputStream is=null;
            String xmlContents="";
            try{
                URL url = new URL(theurl);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setReadTimeout(1000);
                con.setConnectTimeout(1000);
                con.setRequestMethod("GET");
                int response=con.getResponseCode();
                Log.d("DownloadXML","The response received is: "+response);
                is=con.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                int charread;
                char[] inputBuffer = new char[BUF_SIZE];
                try{
                    while((charread=isr.read(inputBuffer))>0){
                        String readString = String.copyValueOf(inputBuffer,0,charread);
                        xmlContents+=readString;
                        inputBuffer=new char[BUF_SIZE];
                    }
                    return xmlContents;
                }
                catch (IOException e){
                    e.printStackTrace();
                    return null;
                }
            }
            finally {
                if(is != null)
                    is.close();
            }

        }
    }
}
