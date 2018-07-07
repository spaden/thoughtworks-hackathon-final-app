package com.thoughthackathon.spaden.thoughtworkshackathon;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class pleasewait extends Fragment {
    private int ct=0;
    ImageView img;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.pleasewait,container,false);
        img=(ImageView)v.findViewById(R.id.resp);

        return v;

    }
    public void ok(){
        img.setBackgroundColor(getContext().getColor(R.color.okcheck));
    }
    public void ctchange(){

          img.setImageDrawable(getContext().getDrawable(R.drawable.noint));
    }
}
