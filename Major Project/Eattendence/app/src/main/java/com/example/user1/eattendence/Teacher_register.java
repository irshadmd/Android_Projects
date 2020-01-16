package com.example.user1.eattendence;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class Teacher_register extends Fragment {
    String USERNAMEt,PASSWORDt,FACULTYIDt,EMAILt,CONTACTt;
    EditText et1,et2,et3,et4,et5;
    Button bt1;
    String getID ;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_teacher_register, container, false);
        et1=view.findViewById(R.id.et1);
        et2=view.findViewById(R.id.et2);
        et3=view.findViewById(R.id.et3);
        et4=view.findViewById(R.id.et4);
        et5=view.findViewById(R.id.et5);
        bt1=view.findViewById(R.id.bt1);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Teacher");
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                USERNAMEt=et1.getText().toString();
                PASSWORDt=et2.getText().toString();
                FACULTYIDt=et3.getText().toString();
                EMAILt=et4.getText().toString();
                CONTACTt=et5.getText().toString();
                if (validate())
                {
                    Query query = databaseReference.orderByKey().equalTo(FACULTYIDt);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                                getID=String.valueOf(dataSnapshot1.getKey());
                                if(getID.equals(FACULTYIDt)) {
                                    if (dataSnapshot1.child("USERNAME").exists()) {
                                        Toast.makeText(getActivity(), "Account exists", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        auth = FirebaseAuth.getInstance();
                                        auth.createUserWithEmailAndPassword(EMAILt,PASSWORDt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if(task.isSuccessful())
                                                {
                                                    Toast.makeText(getActivity(), "Auth created", Toast.LENGTH_SHORT).show();
                                                    databaseReference.child(FACULTYIDt).child("USERNAME").setValue(USERNAMEt);
                                                    databaseReference.child(FACULTYIDt).child("PASSWORD").setValue(PASSWORDt);
                                                    databaseReference.child(FACULTYIDt).child("EMAIL").setValue(EMAILt);
                                                    databaseReference.child(FACULTYIDt).child("CONTACT").setValue(CONTACTt);
                                                    Toast.makeText(getActivity(), "Account Created", Toast.LENGTH_SHORT).show();
                                                    et1.setText("");
                                                    et2.setText("");
                                                    et3.setText("");
                                                    et4.setText("");
                                                    et5.setText("");
                                                    emailverified();
                                                    Toast.makeText(getActivity(), "You can now Login", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Toast.makeText(getActivity(), "Email Already Exist", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }
                                else {
                                    Toast.makeText(getActivity(), "Not exists", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getActivity(), "Error Registering", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }
    public boolean validate()
    {
        Boolean value=false;
        if(USERNAMEt.equals("")||PASSWORDt.equals("")||FACULTYIDt.equals("")||EMAILt.equals("")||CONTACTt.equals(""))
        {
            Toast.makeText(getActivity(),"enter all the field",Toast.LENGTH_SHORT).show();
            value=false;
        }
        else
        {
            if (EMAILt.contains("@"))
            {
                value=true;
            }
            else{
                value=false;
            }
        }
        return value;
    }
    public void emailverified()
    {
        auth = FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();

        firebaseUser.sendEmailVerification().addOnCompleteListener(getActivity(),new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(), "email send", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "email not send", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
