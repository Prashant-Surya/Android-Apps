package com.prashant.android.flipv2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Store extends Fragment{
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.store,container,false);
        ListView list = (ListView) v.findViewById(R.id.listView);
        ArrayList<rowHolder> rh = new ArrayList<rowHolder>();
        rowHolder r1,r2;
        r1 = new rowHolder("10","20","http://i.imgur.com/DvpvklR.png");
        r2 = new rowHolder("100","200","http://i.imgur.com/DvpvklR.png");
        rh.add(r1);
        rh.add(r2);
        listAdapter ls = new listAdapter(getActivity().getApplicationContext(),R.layout.list_item,rh);
        list.setAdapter(ls);
        return v;
    }
}