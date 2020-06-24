package com.learning.skilclasses.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.learning.skilclasses.R;
import com.learning.skilclasses.Utilities.ApiUrl;
import com.learning.skilclasses.activities.AskDoubtActivity;
import com.learning.skilclasses.adapters.MessageAdapter;
import com.learning.skilclasses.models.Message;
import com.learning.skilclasses.preferences.UserSession;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

public class DoubtsFragment extends Fragment {
    private List<Message> messageList;

    @BindView(R.id.doubts)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    @OnClick(R.id.ask_doubts)
    void askDoubt() {
        startActivity(new Intent(getActivity(), AskDoubtActivity.class));
    }


    UserSession userSession;
    OkHttpClient okHttpClient;


    MessageAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Ask Your Doubts");
        View view = inflater.inflate(R.layout.fragment_doubts, container, false);
        ButterKnife.bind(this, view);
        userSession = new UserSession(getActivity());
        messageList = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        try {
            getMessages(ApiUrl.GET_MESSAGES, userSession.getUserDetails().get(UserSession.KEY_COURSE));
            swipeRefreshLayout.setOnRefreshListener(() -> {
                try {
                    getMessages(ApiUrl.GET_MESSAGES, userSession.getUserDetails().get(UserSession.KEY_COURSE));
                    swipeRefreshLayout.setRefreshing(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public void getMessages(String url, String course) throws Exception {
        AndroidNetworking.post(url)
                .addBodyParameter("course", course)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            progressBar.setVisibility(View.GONE);
                            messageList.clear();
                            JSONArray jsonArray = response;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                messageList.add(new Message(jsonObject.getString("message"), jsonObject.getString("message_image"), jsonObject.getString("course"), jsonObject.getString("user_id"), jsonObject.getString("sentat")));
                            }
                            Collections.reverse(messageList);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            adapter = new MessageAdapter(messageList, getActivity());
                            adapter.notifyDataSetChanged();
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() == null) return;
    }

}
