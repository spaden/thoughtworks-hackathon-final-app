package com.thoughthackathon.spaden.thoughtworkshackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class myitem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myitem);
        String name=getIntent().getStringExtra("Name");
        String style=getIntent().getStringExtra("Style");
        TextView textView=(TextView)findViewById(R.id.whichbrand);
        textView.setText(name.toString());
        TextView style2=(TextView)findViewById(R.id.style);
        style2.setText(style.toString());
        TextView con=(TextView)findViewById(R.id.cont);
        String content="\n" +
                "Egyptian wooden model of beer making in ancient Egypt, Rosicrucian Egyptian Museum, San Jose, California\n" +
                "Beer is one of the world's oldest prepared beverages. There is some evidence that beer was produced at Göbekli Tepe during the Pre-Pottery Neolithic (PPN).[12] (The PPN is lasted from around 8500 BC to 5500 BC.) The earliest clear chemical evidence of barley beer dates to about 3500–3100 BC, from the site of Godin Tepe in the Zagros Mountains of western Iran.[13][14] It is possible, but not proven, that it dates back even further — to about 10,000 BC, when cereal was first farmed.[15] Beer is recorded in the written history of ancient Iraq and ancient Egypt,[16] and archaeologists speculate that beer was instrumental in the formation of civilizations.[17] Approximately 5000 years ago, workers in the city of Uruk (modern day Iraq) were paid by their employers in beer.[18] During the building of the Great Pyramids in Giza, Egypt, each worker got a daily ration of four to five litres of beer, which served as both nutrition and refreshment that was crucial to the pyramids' construction.";
        con.setText(content.toString());
    }
}
