package com.example.user1.eattendence;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AttendenceList extends AppCompatActivity {
    Button savebt;
    TextView ab_ap, rollno;
    View view1;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_list);
        recyclerView = findViewById(R.id.st_icon_recycler);
        recyclerView.setHasFixedSize(true);
        savebt = findViewById(R.id.savebt);
        view1 = MyCustomAdapter.getView();
        savebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAttendence();
            }
        });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (MyData.key == 1) {
            data = new ArrayList<>();
            for (int i = 0; i < MyData.theory_icon.length; i++) {
                data.add(new DataModel(
                        MyData.st_roll[i]
                ));
            }
            adapter = new MyCustomAdapter(data);
            recyclerView.setAdapter(adapter);
        } else if (MyData.key == 2) {
            data = new ArrayList<>();
            for (int i = 0; i < MyData.st_roll.length / 2; i++) {
                data.add(new DataModel(
                        MyData.st_roll[i]
                ));
            }
            adapter = new MyCustomAdapter(data);
            recyclerView.setAdapter(adapter);
        } else if (MyData.key == 3) {
            data = new ArrayList<>();
            for (int i = (MyData.st_roll.length / 2) + 1; i < MyData.st_roll.length; i++) {
                data.add(new DataModel(
                        MyData.st_roll[i]
                ));
            }
            adapter = new MyCustomAdapter(data);
            recyclerView.setAdapter(adapter);
        } else if (MyData.key == 4) {
            data = new ArrayList<>();
            for (int i = 0; i < MyData.st_roll.length / 3; i++) {
                data.add(new DataModel(
                        MyData.st_roll[i]
                ));
            }
            adapter = new MyCustomAdapter(data);
            recyclerView.setAdapter(adapter);
        } else if (MyData.key == 5) {
            data = new ArrayList<>();
            for (int i = MyData.st_roll.length / 3; i < (MyData.st_roll.length * 2) / 3; i++) {
                data.add(new DataModel(
                        MyData.st_roll[i]
                ));
            }
            adapter = new MyCustomAdapter(data);
            recyclerView.setAdapter(adapter);
        } else if (MyData.key == 6) {
            data = new ArrayList<>();
            for (int i = (MyData.st_roll.length * 2) / 3; i < MyData.st_roll.length; i++) {
                data.add(new DataModel(
                        MyData.st_roll[i]
                ));
            }
            adapter = new MyCustomAdapter(data);
            recyclerView.setAdapter(adapter);
        }
    }

    public void updateAttendence() {

        int hashmapsize = MyCustomAdapter.hashMap.size();
        int count = 0;
        for (String key : MyCustomAdapter.hashMap.keySet()) {
            if (MyCustomAdapter.hashMap.get(key).equals("2")) {
                count++;
            }
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Present : " + count + "\n Absent : " + (hashmapsize - count))
                .setCancelable(true)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        final DatabaseReference databaseReference = firebaseDatabase.getReference("attendence");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (String key : MyCustomAdapter.hashMap.keySet()) {
                                    String dbvalue = (dataSnapshot.child(key).getValue().toString());
                                    int a = Integer.parseInt(dbvalue);
                                    String hashmapvalue = MyCustomAdapter.hashMap.get(key);
                                    int b = Integer.parseInt(hashmapvalue);
                                    int c = a + b;
                                    String finalvalue = c + "";
                                    databaseReference.child(key).setValue(finalvalue);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        finish();
                        Intent intent = new Intent(AttendenceList.this, Teacher_home.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", null);
        AlertDialog alert = builder.create();
        alert.setTitle("Confirm!!");
        alert.show();


    }
}