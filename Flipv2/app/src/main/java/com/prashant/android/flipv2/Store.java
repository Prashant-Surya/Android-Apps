package com.prashant.android.flipv2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Store extends Fragment{
    static ArrayList<rowHolder> temp;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.store,container,false);
        ListView list = (ListView) v.findViewById(R.id.listView);
        ArrayList<rowHolder> rh = new ArrayList<rowHolder>();
        rowHolder r1,r2;
        r1 = new rowHolder("Coupons 10","Cashback 20","http://flippaisa.com/static/images/merchants/flipkart.jpg","http://www.flippaisa.com/store/flipkart/");
        r2 = new rowHolder("Coupons 100","Cashback 200","http://flippaisa.com/static/images/merchants/snapdeal.jpg","http://www.flippaisa.com/store/flipkart/");
        rh.add(r1);
        rh.add(r2);
        temp = rh;
        listAdapter ls = new listAdapter(getActivity().getApplicationContext(),R.layout.list_item,rh);
        list.setAdapter(ls);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rowHolder temp = (rowHolder) parent.getItemAtPosition(position);
                System.out.println("Test Store" + position + temp.getCashback() + " " + temp.getImg());
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(temp.getUrl()));
                startActivity(browserIntent);
            }
        });
        return v;
    }
}