package com.example.user1.eattendence;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


public class St_login extends Fragment {
    EditText et1, et2;
    Button loginbt;
    String EMAIL, PASSWORD;
    int k = 0;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    ArrayList arrayList;
    Iterator iterator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stlogin, container, false);
        et1 = view.findViewById(R.id.et1);
        et2 = view.findViewById(R.id.et2);
        loginbt = view.findViewById(R.id.loginbt);
        arrayList = new ArrayList();
        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EMAIL = et1.getText().toString();
                PASSWORD = et2.getText().toString();
                if (validate()) {
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("Student");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                String s = dataSnapshot1.child("EMAIL").getValue().toString();
                                arrayList.add(s);
                            }
                            Toast.makeText(getActivity(), "" + arrayList, Toast.LENGTH_SHORT).show();
                            iterator = arrayList.iterator();
                            while (iterator.hasNext()) {
                                if (iterator.next().toString().equals(EMAIL)) {
                                    auth = FirebaseAuth.getInstance();
                                    auth.signInWithEmailAndPassword(EMAIL, PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                arrayList.removeAll(arrayList);
                                                Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getActivity(), St_Home.class);
                                                startActivity(intent);
                                            } else {
                                                arrayList.removeAll(arrayList);
                                                Toast.makeText(getActivity(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    break;
                                } else {
                                    k++;
                                }
                            }
                            if (k > 0) {
                                arrayList.removeAll(arrayList);
                                Toast.makeText(getActivity(), "Not Registered", Toast.LENGTH_SHORT).show();
                                k = 0;
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            }
        });
        return view;
    }

    public boolean validate() {
        Boolean valid;
        if (EMAIL.equals("") || PASSWORD.equals("")) {
            Toast.makeText(getActivity(), "Enter Email or Password", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }
}
