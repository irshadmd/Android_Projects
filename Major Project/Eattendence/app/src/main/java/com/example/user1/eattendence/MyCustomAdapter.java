package com.example.user1.eattendence;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MViewHolder> {

    static View view;
    static final HashMap<String, String> hashMap = new HashMap();
    private ArrayList<DataModel> dataSet1;

    public static class MViewHolder extends RecyclerView.ViewHolder {
        TextView roll;
        TextView ab_ap;
        int key = 0;

        public MViewHolder(final View itemView) {
            super(itemView);
            this.roll = itemView.findViewById(R.id.rolltext);
            this.ab_ap = itemView.findViewById(R.id.ab_ap);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (key == 0) {
                        itemView.setBackgroundColor(Color.GREEN);
                        ab_ap.setText("Present");
                        ab_ap.setTextColor(Color.WHITE);
                        hashMap.put(roll.getText().toString(), "2");
                        key = 1;

                        Toast.makeText(getView().getContext(), roll.getText() + "", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getView().getContext(), hashMap + "", Toast.LENGTH_SHORT).show();
                    } else if (key == 1) {
                        itemView.setBackgroundColor(Color.RED);
                        ab_ap.setText("Absent");
                        ab_ap.setTextColor(Color.WHITE);
                        hashMap.put(roll.getText().toString(), "0");
                        key = 0;

                        Toast.makeText(getView().getContext(), roll.getText() + "", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getView().getContext(), hashMap + "", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public MyCustomAdapter(ArrayList<DataModel> data) {
        this.dataSet1 = data;
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent,
                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.icon_layout, parent, false);

        MyCustomAdapter.MViewHolder myViewHolder = new MViewHolder(view);
        this.view = view;
        return myViewHolder;
    }

    public static View getView() {
        return view;
    }

    @Override
    public void onBindViewHolder(final MyCustomAdapter.MViewHolder holder, final int listPosition) {
        TextView rollno = holder.roll;
        rollno.setText(dataSet1.get(listPosition).getStRoll());
    }

    @Override
    public int getItemCount() {
        return dataSet1.size();
    }

    @Override
    public int getItemViewType(int a) {
        return a;
    }

}
