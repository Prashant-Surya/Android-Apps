package com.prashant.android.flipv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class Test extends ActionBarActivity {
    static int logIn = 0;
    static Context con;
    static TextView tv;
    static SharedPreferences pref;
    EditText name,pass,mail;
    Button btn;
//    String res;

//    JSONObject json;
    InUpProcessing ip;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        con = getApplicationContext();
        tv = (TextView) findViewById(R.id.InOrUp);
        name = (EditText)findViewById(R.id.upName);
        btn = (Button) findViewById(R.id.btnSignUp);
        pass = (EditText) findViewById(R.id.upPass);
        mail = (EditText) findViewById(R.id.upEmail);
        pref = getSharedPreferences("MyApp",MODE_PRIVATE);
        //SharedPreferences.Editor editor = pref.edit();
        String m = pref.getString("mail","NoVal");
        System.out.println("Testing pref"+m);
        if(!m.equalsIgnoreCase("NoVal")){
            System.out.println("Testing pref"+m);
            userDetails.name=pref.getString("name","NoName");
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void clicked(View v){
        switch(v.getId()){
            case R.id.InOrUp: String op = tv.getText().toString();
                //Toast.makeText(Test.this,op,Toast.LENGTH_LONG).show();
                              if(op.equalsIgnoreCase("Or Sign In")) {
                                  name.setVisibility(name.GONE);
                                  pass.setVisibility(pass.VISIBLE);
                                  btn.setText("Sign In");
                                  tv.setText("Or Sign Up");
                              }
                              else if(op == "Or Sign Up"){
                                 name.setVisibility(name.VISIBLE);
                                  pass.setVisibility(pass.VISIBLE);
                                  btn.setText("Sign Up");
                                  tv.setText("Or Sign In");
                              }
                              break;

            case R.id.forgot: name.setVisibility(name.GONE);
                              pass.setVisibility(pass.GONE);
                              btn.setText("Get Password");
                              break;
            case R.id.btnSignUp:
                                // Toast.makeText(con,btn.getText(),Toast.LENGTH_SHORT).show();
                                    System.out.println(userDetails.mail+"Test");
                                 ip = new InUpProcessing(name.getText().toString(),mail.getText().toString(),pass.getText().toString(),btn.getText().toString());
                                    ip.execute();
                                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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
    public class InUpProcessing extends AsyncTask<String,Void,String>{
        String name,mail,password,method,res;
            HttpPost httppost;
        int flag = 0;
            HttpClient httpclient;
            JSONObject json;
        EditText passText;
        String restSuc="";
        public InUpProcessing(String name, String mail, String password, String method){
            this.name = name;
            this.mail = mail;
            this.password = password;
            this.method = method;

//            Toast.makeText(Test.con,"Test",Toast.LENGTH_LONG);
        }
        protected void onPreExecute(){
            passText = (EditText) findViewById(R.id.upPass);flag =0;
        }
        @Override
        protected String doInBackground(String... params) {
            httpclient = new DefaultHttpClient();
            //Toast.makeText(Test.con,"Testing Phase",Toast.LENGTH_LONG).show();
//            httppost = new HttpPost("localhost/forflippaisa/login_check.php");
            httppost = new HttpPost("http://www.flippaisa.com/iprashant/login_check.php");
            HttpResponse response;
            List<NameValuePair> post = new ArrayList<NameValuePair>();
            post.add(new BasicNameValuePair("mail",mail));
            post.add(new BasicNameValuePair("pass",password));
            post.add(new BasicNameValuePair("name",name));
            post.add(new BasicNameValuePair("op",method));
            //TextView ts = (TextView) findViewById(R.id.text);

            if(method.equalsIgnoreCase("Sign In")){
                try {
                    httppost.setEntity(new UrlEncodedFormEntity(post));
                    response = httpclient.execute(httppost);
                    res = EntityUtils.toString(response.getEntity());
                    System.out.println(res);
                    json = new JSONObject(res);
                    restSuc = json.getString("success");
                    userDetails.name=json.getString("name");
                    SharedPreferences.Editor edits= pref.edit();
                    edits.putString("name",json.getString("name"));
                    edits.commit();
                    System.out.println("At 159 " + pref.getString("name", "Test"));
                    runOnUiThread(new Runnable() {
                        public void run() {

                            Toast.makeText(Test.this, "Can't find,Use forgot Password instead", Toast.LENGTH_SHORT).show();
                        }
                    });

                    System.out.println("Success "+restSuc);
//                    Test.tv.setText("Success");
                    //ts.setText("Success");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error");
                    flag=1;
                    //Toast.makeText(Test.con,"Exception",Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("Sign Up")){
                try {
                    httppost.setEntity(new UrlEncodedFormEntity(post));
                    response = httpclient.execute(httppost);
                    res = EntityUtils.toString(response.getEntity());
                    System.out.println(res);
                    json = new JSONObject(res);
                    String restSuc = json.getString("success");

                }catch (UnknownHostException u){

                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error");
                    flag=1;
                    //Toast.makeText(Test.con,"Exception",Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("Get Password")){

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            passText.setText("");
            if(flag==1){
                Toast.makeText(Test.con,"Check your internet Connection",Toast.LENGTH_LONG).show();
                flag=0;
            }
            if(restSuc.equalsIgnoreCase("1") && method.equalsIgnoreCase("Sign In")){
                userDetails.mail=mail;
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("mail",mail);
                edit.commit();
                Intent intent = new Intent(Test.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

}
