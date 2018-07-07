package com.thoughthackathon.spaden.thoughtworkshackathon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class listrecview extends RecyclerView.Adapter<listrecview.Bottleadapter> {
    private List<beerobj> bottles;
    public Context con;
    beerobj beer;
    public DetailsAdapterListener onClickListener;
    public listrecview(Context context, List<beerobj> mTrainDetails,
                              DetailsAdapterListener listener) {
        this.con = context;
        this.bottles = mTrainDetails;
        this.onClickListener = listener;
    }
    public class Bottleadapter extends RecyclerView.ViewHolder {
        public TextView name,style,abv,ibv,ounces;
        public Button addtocart;


        public Bottleadapter(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.botname);
            style=(TextView)itemView.findViewById(R.id.botstyle);
            abv=(TextView)itemView.findViewById(R.id.botabv);
            ibv=(TextView)itemView.findViewById(R.id.botibv);
            ounces=(TextView)itemView.findViewById(R.id.botounces);
            addtocart=(Button)itemView.findViewById(R.id.botadd);
           addtocart.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   onClickListener.classOnClick(view,getAdapterPosition());
               }
           });
        }
    }
    public listrecview(List<beerobj> beerlist) {
        this.bottles=beerlist;
    }





    @NonNull
    @Override
    public listrecview.Bottleadapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lit,viewGroup,false);

        return new Bottleadapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final listrecview.Bottleadapter bottleadapter, int i) {
         beer=bottles.get(i);
          bottleadapter.name.setText(beer.getName());
          bottleadapter.style.setText(beer.getStyle());
          bottleadapter.abv.setText(" "+beer.getAbv());
          bottleadapter.ibv.setText(" "+beer.getIbv());
          bottleadapter.ounces.setText(" "+beer.getOunces());
          bottleadapter.addtocart.setText(String.valueOf(beer.getId()));

    }
    public interface DetailsAdapterListener {

        void classOnClick(View v, int position);

     //   void daysOnClick(View v, int position);
    }
    public void touchme(Button hey){
        Toast.makeText(con,hey.getText().toString(),Toast.LENGTH_SHORT).show();
    }
    @Override
    public int getItemCount() {
        return bottles.size();
    }
}
