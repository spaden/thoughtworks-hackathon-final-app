package com.thoughthackathon.spaden.thoughtworkshackathon;

import android.app.VoiceInteractor;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class litfragment extends Fragment {
    private RecyclerView recyclerView;
    private listrecview mAdapter;
    DataManager dm;
    RequestQueue queue;
    String url ="http://starlord.hackerearth.com/beercraft";
    private List<beerobj> beerlist =new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dm = new DataManager(getContext());
        View v= inflater.inflate(R.layout.litfrag,container,false);
        recyclerView=(RecyclerView)v.findViewById(R.id.vertrec);
       // queue = Volley.newRequestQueue(getContext());
        for(int i=0;i<20;i++){
            beerobj ot=new beerobj();
            ot.setId(i);
            ot.setName(" "+i);
            ot.setIbv(" "+i);
            ot.setAbv(" "+i);
            ot.setOunces(i);
            beerlist.add(ot);
        }
        mAdapter=new listrecview(getContext(), beerlist, new listrecview.DetailsAdapterListener() {
            @Override
            public void classOnClick(View v, int position) {
                 show(position);
            }
        });
        RecyclerView.LayoutManager mlay=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mlay);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return v;
    }
    public  void setupadapter(final Context con){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                       // Toast.makeText(con,response.length(),Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                    }
                }
        );
          queue = Volley.newRequestQueue(con);

         queue.add(jsonArrayRequest);

    }
    public void changeme(List<beerobj> hey){
        beerlist.clear();
        beerlist.addAll(hey);
        mAdapter=new listrecview(beerlist);
        mAdapter.notifyDataSetChanged();
    }
    public void show(int position){
        Toast.makeText(getContext()," "+beerlist.get(position).getId(),Toast.LENGTH_SHORT).show();
        dm.inserttwo(beerlist.get(position));
        Cursor c=dm.select2();
        c.moveToFirst();
        while(c.moveToNext()){
            Log.i("kalyan",c.getString(0)+" "+c.getString(1));
        }
    }


}
