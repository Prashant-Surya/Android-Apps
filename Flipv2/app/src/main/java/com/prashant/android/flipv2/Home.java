package com.prashant.android.flipv2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment{

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_layout,container,false);
        //setHasOptionsMenu(true);
        account acc = new account();
        acc.execute();
        return v ;
    }

    public class account extends AsyncTask<String,Void,String>{
        HttpClient httpClient;
        HttpPost httpPost;
        HttpResponse httpResponse;
        String result;
        JSONObject json;
        TextView pending,bonus,cfp,paid,welcome;
        List<NameValuePair> data = new ArrayList<NameValuePair>(1);
        Context con = MainActivity.mainContext;
        @Override
        protected String doInBackground(String... params) {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost("http://flippaisa.com/iprashant/send_rewards.php");
            data.add(new BasicNameValuePair("mail",userDetails.mail));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(data));
                httpResponse = httpClient.execute(httpPost);
                result = EntityUtils.toString(httpResponse.getEntity());
                System.out.println(result);
                json = new JSONObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            pending = (TextView) getView().findViewById(R.id.pending);
            bonus = (TextView) getView().findViewById(R.id.bonus);
            cfp = (TextView) getView().findViewById(R.id.confirmed);
            paid = (TextView) getView().findViewById(R.id.paid);
            welcome = (TextView) getView().findViewById(R.id.welcome);
            try{
                welcome.setText(con.getString(R.string.welcome)+" "+userDetails.name.toUpperCase());
                pending.setText(con.getString(R.string.pending) + " " + json.getString("pfp"));
                bonus.setText(con.getString(R.string.bonus) + " " + json.getString("bonusfp"));
                cfp.setText(con.getString(R.string.confirmed) + " " + json.getString("cfp"));
                paid.setText(con.getString(R.string.paid) + " " + json.getString("paid"));
            }catch(Exception e){
                System.out.println("Error at 1");
            }
        }
    }

}