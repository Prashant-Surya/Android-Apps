package com.prashant.android.flipv2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;

    CharSequence Titles[]={"Home","Stores","FAQ","Support"};
    int Numboftabs = 4;

    public static Context mainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Flip Paisa");
        mainContext = getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

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
            //Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*public void clicked(View v) {
        TextView inUp = (TextView)findViewById(R.id.InOrUp);
        String tex = inUp.getText().toString();
        EditText name = (EditText)findViewById(R.id.upName) ;
        Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
        Button btn = (Button)findViewById(R.id.btnSignUp);
        if(tex == "Or Sign In"){
            inUp.setText("Or Sign Up");
            btn.setText("Sign In");
            name.setVisibility(name.GONE);
            //
        }
        else if(tex == "Or Sign Up"){
            inUp.setText("Or Sign In");
            btn.setText("Sign Up");
            name.setVisibility(name.VISIBLE);

         }
    }*/
    public void listClick(View li){
        
    }
}
