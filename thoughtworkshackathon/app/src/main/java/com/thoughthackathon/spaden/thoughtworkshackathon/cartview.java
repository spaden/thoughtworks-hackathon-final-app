package com.thoughthackathon.spaden.thoughtworkshackathon;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class cartview extends AppCompatActivity {
    public List<beerobj> beers=new ArrayList<>();
    public RecyclerView hey;
    DataManager dm;
    private listrecview list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartview);
        hey=(RecyclerView)findViewById(R.id.hey);
        dm=new DataManager(getApplicationContext());
        Cursor c=dm.select2();
        c.moveToFirst();
        while (c.moveToNext()){
            beerobj obj=new beerobj();
            obj.setName(c.getString(0));
            obj.setStyle(c.getString(1));
            obj.setOunces(c.getDouble(2));
            beers.add(obj);
        }
        list=new listrecview(getApplicationContext(), beers, new listrecview.DetailsAdapterListener() {
            @Override
            public void classOnClick(View v, int position) {
                show(position);
            }
        });
        RecyclerView.LayoutManager mlay=new LinearLayoutManager(getApplicationContext());
        hey.setLayoutManager(mlay);
        hey.setItemAnimator(new DefaultItemAnimator());

        hey.setAdapter(list);



    }
    public  void show(int pos){
        Toast.makeText(getApplicationContext(),"Item already in the cart",Toast.LENGTH_SHORT).show();
    }
}
