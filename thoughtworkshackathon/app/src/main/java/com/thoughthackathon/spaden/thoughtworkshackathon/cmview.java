package com.thoughthackathon.spaden.thoughtworkshackathon;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class cmview extends AppCompatActivity {
    public List<beerobj> beers=new ArrayList<>();
    Data2 dm;
    DataManager dd;
    public RecyclerView recyclerView;
    private listrecview list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmview);
        recyclerView=(RecyclerView)findViewById(R.id.whatrec);
        dd=new DataManager(getApplicationContext());
        dm=new Data2(getApplicationContext());
        Cursor c=dm.select3();
        c.moveToFirst();
        while (c.moveToNext()){


            beerobj obj=new beerobj();
            obj.setName(c.getString(0));
            obj.setStyle(c.getString(1));
            obj.setAbv(c.getString(2));
            obj.setIbv(" " +c.getInt(3));
            obj.setId(c.getInt(4));
            obj.setOunces(c.getDouble(5));
            beers.add(obj);
        }
        list=new listrecview(getApplicationContext(), beers, new listrecview.DetailsAdapterListener() {
            @Override
            public void classOnClick(View v, int position) {
                show(position);
            }
        });
        RecyclerView.LayoutManager mlay=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mlay);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(list);

    }
    public void show(int pos){
        Toast.makeText(getApplicationContext(),"Item added to the cart",Toast.LENGTH_SHORT).show();
        dd.inserttwo(beers.get(pos));
        Cursor c=dd.select2();
        c.moveToFirst();
        while(c.moveToNext()){
            Log.i("kalyan",c.getString(0)+" "+c.getString(1));
        }

    }
}
