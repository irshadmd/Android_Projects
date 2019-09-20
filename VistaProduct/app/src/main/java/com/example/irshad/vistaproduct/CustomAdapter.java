package com.example.irshad.vistaproduct;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView card_img;
        TextView card_text1, card_text2, card_text3, card_text4;

        public MyViewHolder(final View itemView) {
            super(itemView);
            this.card_img = (ImageView) itemView.findViewById(R.id.card_img);
            this.card_text1 = itemView.findViewById(R.id.card_text1);
            this.card_text2 = itemView.findViewById(R.id.card_text2);
            this.card_text3 = itemView.findViewById(R.id.card_text3);
            this.card_text4 = itemView.findViewById(R.id.card_text4);
        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardlayout, parent, false);

        CardView cardView = view.findViewById(R.id.card);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "Selected", Toast.LENGTH_SHORT).show();
            }
        });

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int listPosition) {

        TextView text1 = holder.card_text1;
        TextView text2 = holder.card_text2;
        TextView text3 = holder.card_text3;
        TextView text4 = holder.card_text4;
        ImageView src = holder.card_img;

        text1.setText(dataSet.get(listPosition).getText1());
        text2.setText(dataSet.get(listPosition).getText2());
        text3.setText(dataSet.get(listPosition).getText3());
        text4.setText(dataSet.get(listPosition).getText4());
        src.setImageResource(dataSet.get(listPosition).getImgId());

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
