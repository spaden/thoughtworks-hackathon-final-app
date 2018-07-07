package com.thoughthackathon.spaden.thoughtworkshackathon;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class track extends AppCompatActivity {
    DataManager dm;
    Data2 dd;
    List<String> autocomp=new ArrayList<>();
    pleasewait wait;
    litfragment fg;
    FragmentManager fm;
    Fragment which;
    AutoCompleteTextView search;
    List<beerobj> beer =new ArrayList<>();
    Button sear;
    Button cart,filter,asc,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        dm = new DataManager(getApplicationContext());
        dd=new Data2(getApplicationContext());
        fg=new litfragment();
        fm=getSupportFragmentManager();
        wait=new pleasewait();
        which = fm.findFragmentById(R.id.fragselect);

        if(which == null){
            which=wait;
            fm.beginTransaction().add(R.id.fragselect,which).commit();
        }

        sear=(Button)findViewById(R.id.sear);
        sear.setEnabled(false);

        search=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);
        sear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Searching please wait",Toast.LENGTH_SHORT).show();
                searchquery();
            }
        });
        cart=(Button)findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),cartview.class);
                startActivity(i);
            }
        });
        filter= (Button)findViewById(R.id.exp);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i= new Intent(getApplicationContext(),cmview.class);
                    startActivity(i);
            }
        });
        asc=(Button)findViewById(R.id.ltview);
        asc.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
                  Toast.makeText(getApplicationContext(),"Ascending order, Please wait",Toast.LENGTH_SHORT).show();
                  Cursor c=dm.selectasc();
                  c.moveToFirst();
                List<beerobj> beersearchlist=new ArrayList<>();
                while(c.moveToNext()){
                    beerobj obj=new beerobj();
                    obj.setName(c.getString(0));
                    obj.setStyle(c.getString(1));
                    obj.setAbv(c.getString(2));
                    obj.setIbv(" " +c.getInt(3));
                    obj.setId(c.getInt(4));
                    obj.setOunces(c.getDouble(5));
                    beersearchlist.add(obj);

                }
                dd.insert3(beersearchlist);
                Intent i= new Intent(getApplicationContext(),cmview.class);
                startActivity(i);
            }
        });
        desc=(Button)findViewById(R.id.gdview);
        desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Descending order,please wait",Toast.LENGTH_SHORT).show();
                Cursor c=dm.selectdesc();
                c.moveToFirst();
                List<beerobj> beersearchlist=new ArrayList<>();
                while(c.moveToNext()){
                    beerobj obj=new beerobj();
                    obj.setName(c.getString(0));
                    obj.setStyle(c.getString(1));
                    obj.setAbv(c.getString(2));
                    obj.setIbv(" " +c.getInt(3));
                    obj.setId(c.getInt(4));
                    obj.setOunces(c.getDouble(5));
                    beersearchlist.add(obj);

                }
                dd.delete2();
                dd.insert3(beersearchlist);
                Intent i= new Intent(getApplicationContext(),cmview.class);
                startActivity(i);
            }
        });
        search.setEnabled(false);
        setupadapter();
    }
    public void searchquery(){
            Cursor c=  dm.select1(search.getText().toString());
            c.moveToFirst();
           /* */
           if(c.getCount()>1){
               beerobj bt=new beerobj();
               List<beerobj> beersearchlist=new ArrayList<>();
               while(c.moveToNext()){
                   beerobj obj=new beerobj();
                   obj.setName(c.getString(0));
                   obj.setStyle(c.getString(1));
                   obj.setAbv(c.getString(2));
                   obj.setIbv(" " +c.getInt(3));
                   obj.setId(c.getInt(4));
                   obj.setOunces(c.getDouble(5));
                   beersearchlist.add(obj);

               }
               dd.delete2();
               dd.insert3(beersearchlist);
               Intent i= new Intent(getApplicationContext(),cmview.class);
               startActivity(i);
           }else {
            //   Toast.makeText(getApplicationContext(),c.getString(0)+" "+c.getString(1) +"Length "+c.getCount(),Toast.LENGTH_SHORT).show();
             Intent i= new Intent(getApplicationContext(),myitem.class);
             i.putExtra("Name",c.getString(0));
             i.putExtra("Style",c.getString(1));
             startActivity(i);
           }

    }
    public void checkiffull(){
            dm.delete();
            dm.insert1(beer);
            Cursor c=dm.isFUll1();
            c.moveToFirst();
            while(c.moveToNext()){
                Log.i("testtwo",c.getString(1) + " "+c.getString(2)+" "+c.getString(3));
            }

    }

    public  void setupadapter(){
        RequestQueue queue=Volley.newRequestQueue(getApplicationContext());
        String url ="http://starlord.hackerearth.com/beercraft";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("fetching","Fet");
                        // Do something with response
                        //mTextView.setText(response.toString());
                        try {
                         for(int i=0;i<response.length();i++){
                             Log.i("name","fetched");
                             JSONObject job=response.getJSONObject(i);
                             beerobj beeri=new beerobj();
                             beeri.setName(job.getString("name"));
                             String abv=job.getString("abv");
                             String ibu=job.getString("ibu");
                             int id=job.getInt("id");
                             String name=job.getString("name");
                             String style=job.getString("style");
                             autocomp.add(name);
                             autocomp.add(style);
                             Double ounces=job.getDouble("ounces");
                             Log.i("content",job.getString("name")+ " "+abv+" "+ibu+" ");
                             beeri.setAbv(abv);
                             beeri.setIbv(ibu);
                             beeri.setId(id);
                             beeri.setStyle(style);
                             beeri.setOunces(ounces);
                             beer.add(beeri);

                         }

                             alter();
                             setsearchstring();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        wait.ctchange();
                        which=wait;

                        fm.beginTransaction().add(R.id.fragselect,which).commit();
                          // Do something when error occurred
                    }
                }
        );

        queue.add(jsonArrayRequest);

    }
    public void alter(){
        Log.i("completed","ok altering recycler view now");
         checkiffull();
          fg.changeme(beer);
        wait.ok();
        which=fg;
          fm.beginTransaction().add(R.id.fragselect,which).commit();
    }
    public void alter1(){
        fg.changeme(beer);
        which=wait;
        fm.beginTransaction().add(R.id.fragselect,which).commit();
        for(int i=0;i<200;i++){

        }
        which=fg;
        fm.beginTransaction().add(R.id.fragselect,which).commit();
    }
    public void setsearchstring(){
        String [] key=new String[autocomp.size()];
        for(int i=0;i<autocomp.size();i++){
            key[i]=autocomp.get(i);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.simp,key);
        search.setAdapter(adapter);
        search.setEnabled(true);
        sear.setEnabled(true);
        search.setThreshold(1);
    }
}
