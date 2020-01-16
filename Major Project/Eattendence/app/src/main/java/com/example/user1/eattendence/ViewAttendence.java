package com.example.user1.eattendence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAttendence extends AppCompatActivity {
    View view1;
    String val;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendence);
        recyclerView = findViewById(R.id.view_list_recyclerView);
        recyclerView.setHasFixedSize(true);

        view1 = MyCustomAdapter_viewList.getView();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        presentData();

        data = new ArrayList<>();
        for (int i = 0; i < 60; i++) {

            data.add(new DataModel(MyData.id_[i]));
        }
        adapter = new MyCustomAdapter_viewList(data);
        recyclerView.setAdapter(adapter);
    }
    int i=1;
    public void presentData(){

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("attendence");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        val = dataSnapshot1.child("Roll no: 1").getValue().toString();
                        Toast.makeText(ViewAttendence.this, val + "", Toast.LENGTH_SHORT).show();

                    }
                }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
