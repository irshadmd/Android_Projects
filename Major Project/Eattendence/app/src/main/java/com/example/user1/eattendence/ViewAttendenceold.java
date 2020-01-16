package com.example.user1.eattendence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewAttendenceold extends AppCompatActivity {
    TextView pr1,pr2,pr3,pr4,pr5,pr6,pr7,pr8,pr9,pr10,pr11,pr12,pr13,pr14,pr15,pr16,pr17,pr18,pr19,pr20,pr21,pr22,
    pr23,pr24,pr25,pr26,pr27,pr28,pr29,pr30,pr31,pr32,pr33,pr34,pr35,pr36,pr37,pr38,pr39,pr40,pr41,pr42,pr43,pr44,
    pr45,pr46,pr47,pr48,pr49,pr50,pr51,pr52,pr53,pr54,pr55,pr56,pr57,pr58,pr59,pr60;
    String s[];
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendenceold);
        pr1=findViewById(R.id.pr1);
        pr2=findViewById(R.id.pr2);
        pr3=findViewById(R.id.pr3);
        pr4=findViewById(R.id.pr4);
        pr5=findViewById(R.id.pr5);
        pr6=findViewById(R.id.pr6);
        pr7=findViewById(R.id.pr7);
        pr8=findViewById(R.id.pr8);
        pr9=findViewById(R.id.pr9);
        pr10=findViewById(R.id.pr10);
        pr11=findViewById(R.id.pr11);
        pr12=findViewById(R.id.pr12);
        pr13=findViewById(R.id.pr13);
        pr14=findViewById(R.id.pr14);
        pr15=findViewById(R.id.pr15);
        pr16=findViewById(R.id.pr16);
        pr17=findViewById(R.id.pr17);
        pr18=findViewById(R.id.pr18);
        pr19=findViewById(R.id.pr19);
        pr20=findViewById(R.id.pr20);
        pr21=findViewById(R.id.pr21);
        pr22=findViewById(R.id.pr22);
        pr23=findViewById(R.id.pr23);
        pr24=findViewById(R.id.pr24);
        pr25=findViewById(R.id.pr25);
        pr26=findViewById(R.id.pr26);
        pr27=findViewById(R.id.pr27);
        pr28=findViewById(R.id.pr28);
        pr29=findViewById(R.id.pr29);
        pr30=findViewById(R.id.pr30);
        pr31=findViewById(R.id.pr31);
        pr32=findViewById(R.id.pr32);
        pr33=findViewById(R.id.pr33);
        pr34=findViewById(R.id.pr34);
        pr35=findViewById(R.id.pr35);
        pr36=findViewById(R.id.pr36);
        pr37=findViewById(R.id.pr37);
        pr38=findViewById(R.id.pr38);
        pr39=findViewById(R.id.pr39);
        pr40=findViewById(R.id.pr40);
        pr41=findViewById(R.id.pr41);
        pr42=findViewById(R.id.pr42);
        pr43=findViewById(R.id.pr43);
        pr44=findViewById(R.id.pr44);
        pr45=findViewById(R.id.pr45);
        pr46=findViewById(R.id.pr46);
        pr47=findViewById(R.id.pr47);
        pr48=findViewById(R.id.pr48);
        pr49=findViewById(R.id.pr49);
        pr50=findViewById(R.id.pr50);
        pr51=findViewById(R.id.pr51);
        pr52=findViewById(R.id.pr52);
        pr53=findViewById(R.id.pr53);
        pr54=findViewById(R.id.pr54);
        pr55=findViewById(R.id.pr55);
        pr56=findViewById(R.id.pr56);
        pr57=findViewById(R.id.pr57);
        pr58=findViewById(R.id.pr58);
        pr59=findViewById(R.id.pr59);
        pr60=findViewById(R.id.pr60);
        for(i=1;i<=60;i++){
           s[i]="pr"+i;
        }

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("attendence");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
