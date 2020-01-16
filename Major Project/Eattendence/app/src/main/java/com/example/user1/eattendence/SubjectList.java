package com.example.user1.eattendence;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class SubjectList extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    Spinner spin;
    Button startbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, MyData.semArray);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String day = LocalDate.now().getDayOfWeek().name();
        if (MyData.semArray[position] == "SELECT") {
            Toast.makeText(this, "select Semester", Toast.LENGTH_SHORT).show();

        } else if (MyData.semArray[position] == "Sem-I") {
            data = new ArrayList<>();
            for (int i = 0; i < MyData.subSem1.length; i++) {
                data.add(new DataModel(
                        MyData.subSem1[i],
                        MyData.semArray[position],
                        MyData.id_[i]
                ));
            }
            adapter = new CustomAdapter(data);
            recyclerView.setAdapter(adapter);
        } else if (MyData.semArray[position] == "Sem-II") {
            data = new ArrayList<>();
            for (int i = 0; i < MyData.subSem2.length; i++) {
                data.add(new DataModel(
                        MyData.subSem2[i],
                        MyData.semArray[position],
                        MyData.id_[i]
                ));
            }
            adapter = new CustomAdapter(data);
            recyclerView.setAdapter(adapter);
        } else if (MyData.semArray[position] == "Sem-III") {
            data = new ArrayList<>();
            for (int i = 0; i < MyData.subSem3.length; i++) {
                data.add(new DataModel(
                        MyData.subSem3[i],
                        MyData.semArray[position],
                        MyData.id_[i]
                ));
            }
            adapter = new CustomAdapter(data);
            recyclerView.setAdapter(adapter);
        } else if (MyData.semArray[position] == "Sem-IV") {
            data = new ArrayList<>();
            for (int i = 0; i < MyData.subSem4.length; i++) {
                data.add(new DataModel(
                        MyData.subSem4[i],
                        MyData.semArray[position],
                        MyData.id_[i]
                ));
            }
            adapter = new CustomAdapter(data);
            recyclerView.setAdapter(adapter);
        } else if (MyData.semArray[position] == "Sem-V") {
            data = new ArrayList<>();
            for (int i = 0; i < MyData.subSem5.length; i++) {
                data.add(new DataModel(
                        MyData.subSem5[i],
                        MyData.semArray[position],
                        MyData.id_[i]
                ));
            }
            adapter = new CustomAdapter(data);
            recyclerView.setAdapter(adapter);
        } else if (MyData.semArray[position] == "Sem-VI") {
            if (day.equalsIgnoreCase("monday")) {
                data = new ArrayList<>();
                for (int i = 0; i < MyData.sem6mon.length; i++) {
                    data.add(new DataModel(
                            MyData.sem6mon[i],
                            MyData.semArray[position],
                            MyData.id_[i]
                    ));
                }
                adapter = new CustomAdapter(data);
                recyclerView.setAdapter(adapter);
            } else if (day.equalsIgnoreCase("tuesday")) {
                data = new ArrayList<>();
                for (int i = 0; i < MyData.sem6tue.length; i++) {
                    data.add(new DataModel(
                            MyData.sem6tue[i],
                            MyData.semArray[position],
                            MyData.id_[i]
                    ));
                }
                adapter = new CustomAdapter(data);
                recyclerView.setAdapter(adapter);
            } else if (day.equalsIgnoreCase("wednesday")) {
                data = new ArrayList<>();
                for (int i = 0; i < MyData.sem6wed.length; i++) {
                    data.add(new DataModel(
                            MyData.sem6wed[i],
                            MyData.semArray[position],
                            MyData.id_[i]
                    ));
                }
                adapter = new CustomAdapter(data);
                recyclerView.setAdapter(adapter);
            } else if (day.equalsIgnoreCase("thursday")) {
                data = new ArrayList<>();
                for (int i = 0; i < MyData.sem6thur.length; i++) {
                    data.add(new DataModel(
                            MyData.sem6thur[i],
                            MyData.semArray[position],
                            MyData.id_[i]
                    ));
                }
                adapter = new CustomAdapter(data);
                recyclerView.setAdapter(adapter);
            } else if (day.equalsIgnoreCase("friday")) {
                data = new ArrayList<>();
                for (int i = 0; i < MyData.sem6fri.length; i++) {
                    data.add(new DataModel(
                            MyData.sem6fri[i],
                            MyData.semArray[position],
                            MyData.id_[i]
                    ));
                }
                adapter = new CustomAdapter(data);
                recyclerView.setAdapter(adapter);
            } else if (day.equalsIgnoreCase("saturday") || day.equalsIgnoreCase("sunday")) {
                Toast.makeText(getApplicationContext(), "today is leave day", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}