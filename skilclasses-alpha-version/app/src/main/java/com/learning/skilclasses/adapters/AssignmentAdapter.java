package com.learning.skilclasses.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.skilclasses.R;
import com.learning.skilclasses.models.AssignmentBean;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ItemsViewHolder> {

    private Context context;
    private List<AssignmentBean> list;

    public AssignmentAdapter(Context context, List<AssignmentBean> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.assignment_recycler, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        AssignmentBean assignmentBean = list.get(position);
        // holder.aid.setText(assignmentBean.getAid());
        holder.aname.setText(assignmentBean.getAdesp());
        holder.aofclass.setText("Class " + assignmentBean.getAofclass() + " | " + assignmentBean.getAdate());
        holder.relativeLayout.setOnClickListener(v -> {
            try {
                assignmentBean.setApath(assignmentBean.getApath().replaceAll(" ", "%20"));
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.digitalcatnyx.store/admin/" + assignmentBean.getApath()));
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //holder.adesp.setText("Description|"+assignmentBean.getAdesp());
        //holder.adate.setText(assignmentBean.getAdate());
        // holder.asub.setText("Subject: "+assignmentBean.getAsub());
        // holder.apath.setText("path: "+assignmentBean.getApath());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        TextView aid, aname, aofclass, adesp, adate, asub, apath;

        RelativeLayout relativeLayout;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            //aid=itemView.findViewById(R.id.aid);
            aname = itemView.findViewById(R.id.aname);
            aofclass = itemView.findViewById(R.id.aofclass);
            relativeLayout = itemView.findViewById(R.id.open_pdf);
            //adesp=itemView.findViewById(R.id.adesp);
            // adate=itemView.findViewById(R.id.adate);
            //asub=itemView.findViewById(R.id.asub);
            //apath=itemView.findViewById(R.id.apath);
        }
    }

}
