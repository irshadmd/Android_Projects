package com.example.user1.eattendence;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomAdapter_viewList extends RecyclerView.Adapter<MyCustomAdapter_viewList.MViewHolder2> {

    static View view;
    private ArrayList<DataModel> dataSet1;
    public MyCustomAdapter_viewList(ArrayList<DataModel> data) {
        this.dataSet1 = data;
    }
    public static class MViewHolder2 extends RecyclerView.ViewHolder {
        TextView presentdata;
        public MViewHolder2(final View itemView) {
            super(itemView);
            this.presentdata=itemView.findViewById(R.id.present);
        }
    }


    @Override
    public MViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_attendence_layout, parent, false);

        MyCustomAdapter_viewList.MViewHolder2 myViewHolder = new MViewHolder2(view);

        this.view = view;
        return myViewHolder;
    }

    public static View getView() {
        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomAdapter_viewList.MViewHolder2 holder, final int listPosition) {
         TextView present=holder.presentdata;
        // present.setText(dataSet1.get(listPosition).getId());
        present.setText(Integer.toString(dataSet1.get(listPosition).getId()));
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
