package com.example.user1.eattendence;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        Button startbt, listbt;
        TextView textSubName;
        TextView textSem;

        public MyViewHolder(final View itemView) {
            super(itemView);
            this.textSubName = (TextView) itemView.findViewById(R.id.subName);
            this.textSem = (TextView) itemView.findViewById(R.id.semText);
            this.startbt = itemView.findViewById(R.id.startbt);
            this.listbt = itemView.findViewById(R.id.listbt);
            startbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyCustomAdapter.hashMap.clear();
                    Toast.makeText(itemView.getContext(), "Startbt clicked!!", Toast.LENGTH_SHORT).show();
                    String Subname = textSubName.getText().toString();

                    if (Subname.equalsIgnoreCase("ARDBMS(Theory)")
                            || Subname.equalsIgnoreCase("ICT(Theory)")
                            || Subname.equalsIgnoreCase("Artificial Intelligence(Theory)")
                            || Subname.equalsIgnoreCase("Visual Basic(Theory)")
                            || Subname.equalsIgnoreCase("Cyber Security(Theory)")) {
                        MyData.key(1);
                    } else if (Subname.equalsIgnoreCase("RDBMS-LAB(1st group)")
                            || Subname.equalsIgnoreCase("VB-LAB(1st group)")) {
                        MyData.key(2);
                    } else if (Subname.equalsIgnoreCase("RDBMS-LAB(2nd group)")
                            || Subname.equalsIgnoreCase("VB-LAB(2nd group)")) {
                        MyData.key(3);
                    } else if (Subname.equalsIgnoreCase("Major-Project(1st group)")) {
                        MyData.key(4);
                    } else if (Subname.equalsIgnoreCase("Major-Project(2nd group)")) {
                        MyData.key(5);
                    } else if (Subname.equalsIgnoreCase("Major-Project(3rd group)")) {
                        MyData.key(6);
                    }

                    Intent intent = new Intent(itemView.getContext(), AttendenceList.class);
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textName = holder.textSubName;
        TextView textSem = holder.textSem;

        textName.setText(dataSet.get(listPosition).getName());
        textSem.setText(dataSet.get(listPosition).getSem());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
