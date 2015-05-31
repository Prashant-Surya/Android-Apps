package com.prashant.android.flipv2;

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
        r1 = new rowHolder("10","20","http://i.imgur.com/DvpvklR.png");
        r2 = new rowHolder("100","200","http://i.imgur.com/DvpvklR.png");
        rh.add(r1);
        rh.add(r2);
        temp = rh;
        listAdapter ls = new listAdapter(getActivity().getApplicationContext(),R.layout.list_item,rh);
        list.setAdapter(ls);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rowHolder temp = (rowHolder) parent.getItemAtPosition(position);
                System.out.println("Test Store"+position+temp.getCashback()+" "+temp.getImg());
            }
        });
        return v;
    }
}