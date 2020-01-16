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
import android.widget.Toast;


public class Fragment_Home extends Fragment {
    Button bt1, bt2;
    android.support.v4.app.FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__home, container, false);
        bt1 = view.findViewById(R.id.bt1);
        bt2 = view.findViewById(R.id.bt2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Clicked!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SubjectList.class);
                startActivity(intent);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked!!", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getActivity(),ViewAttendence.class);
                startActivity(intent);

            }
        });
        return view;
    }
}
