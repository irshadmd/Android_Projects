package com.learning.skilclasses.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.learning.skilclasses.R;
import com.learning.skilclasses.activities.QuizActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

    @OnClick(R.id.start)
    void startQuiz() {
        Intent intent = new Intent(getContext(), QuizActivity.class);
        startActivity(intent);
    }

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Quiz");
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
