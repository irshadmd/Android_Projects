package com.learning.skilclasses.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.learning.skilclasses.R;
import com.learning.skilclasses.adapters.VideoAdapter;
import com.learning.skilclasses.models.Video;
import com.learning.skilclasses.preferences.UserSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {


    @BindView(R.id.group_choices)
    RadioGroup group;

    @BindView(R.id.choice_a)
    RadioButton toggleA;

    @BindView(R.id.choice_b)
    RadioButton toggleB;

    @BindView(R.id.choice_c)
    RadioButton toggleC;

    @BindView(R.id.choice_d)
    RadioButton toggleD;

    @BindView(R.id.choice_e)
    RadioButton toggleE;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    VideoAdapter videoAdapter;
    List<Video> videoList;
    static Context activity;
    UserSession userSession;
    String student_class;
    private static String CLASS_URL = "http://www.digitalcatnyx.store/api/class_detail.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Skil Classes");
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        // student_class = userSession.getUserDetails().get(UserSession.KEY_COURSE);
        activity = getActivity();
        userSession = new UserSession(activity);
        student_class = userSession.getUserDetails().get(UserSession.KEY_COURSE);
        if (activity != null) {
            fetchClassDetails(student_class, null);
            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.choice_a:
                            fetchClassDetails(student_class, null);
                            toggleA.setBackground(activity.getDrawable(R.drawable.checked_grade_background1));
                            toggleA.setTextColor(Color.WHITE);
                            toggleB.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleB.setTextColor(Color.BLACK);
                            toggleC.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleC.setTextColor(Color.BLACK);
                            toggleD.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleD.setTextColor(Color.BLACK);
                            toggleE.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleE.setTextColor(Color.BLACK);
                            break;
                        case R.id.choice_b:
                            fetchClassDetails(student_class, toggleB.getText().toString().trim());
                            toggleA.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleA.setTextColor(Color.BLACK);
                            toggleC.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleC.setTextColor(Color.BLACK);
                            toggleD.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleD.setTextColor(Color.BLACK);
                            toggleE.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleE.setTextColor(Color.BLACK);
                            toggleB.setBackground(activity.getDrawable(R.drawable.checked_grade_background1));
                            toggleB.setTextColor(Color.WHITE);
                            break;
                        case R.id.choice_c:
                            fetchClassDetails(student_class, toggleC.getText().toString().trim());
                            toggleA.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleA.setTextColor(Color.BLACK);
                            toggleC.setBackground(activity.getDrawable(R.drawable.checked_grade_background1));
                            toggleC.setTextColor(Color.WHITE);
                            toggleD.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleD.setTextColor(Color.BLACK);
                            toggleE.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleE.setTextColor(Color.BLACK);
                            toggleB.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleB.setTextColor(Color.BLACK);
                            break;
                        case R.id.choice_d:
                            fetchClassDetails(student_class, toggleD.getText().toString().trim());
                            toggleA.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleA.setTextColor(Color.BLACK);
                            toggleC.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleC.setTextColor(Color.BLACK);
                            toggleE.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleE.setTextColor(Color.BLACK);
                            toggleB.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleB.setTextColor(Color.BLACK);
                            toggleD.setBackground(activity.getDrawable(R.drawable.checked_grade_background1));
                            toggleD.setTextColor(Color.WHITE);
                            break;
                        case R.id.choice_e:
                            fetchClassDetails(student_class, toggleE.getText().toString().trim());
                            toggleA.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleA.setTextColor(Color.BLACK);
                            toggleC.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleC.setTextColor(Color.BLACK);
                            toggleD.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleD.setTextColor(Color.BLACK);
                            toggleB.setBackground(activity.getDrawable(R.drawable.message_layout_background));
                            toggleB.setTextColor(Color.BLACK);
                            toggleE.setBackground(activity.getDrawable(R.drawable.checked_grade_background1));
                            toggleE.setTextColor(Color.WHITE);
                            break;
                    }
                }
            });
        }
        return view;
    }

    private void fetchClassDetails(String _class, String subject) {
        try {
            videoList = new ArrayList<>();
            StringRequest request = new StringRequest(Request.Method.POST, CLASS_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                videoList.clear();
                                Log.d("SUC_RES", response);

                                //JSONObject jsonObject = new JSONObject(response);

                                //Toast.makeText(getApplicationContext(), "" + jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                // progressBar.setVisibility(View.GONE);
                                JSONArray array = new JSONArray(response);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject detail = array.getJSONObject(i);
                                    videoList.add(new Video(detail.getString("vid"), detail.getString("vname"), detail.getString("vofclass")
                                            , detail.getString("vdesp"), detail.getString("vdate"), detail.getString("vsub"), detail.getString("vpath")));
                                }
                                recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                                videoAdapter = new VideoAdapter(videoList, activity);
                                videoAdapter.notifyDataSetChanged();
                                recyclerView.setAdapter(videoAdapter);
                                videoAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ERROR_MESSAGE", error.getMessage() + "");
                    //     Toast.makeText(getContext(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("classtype", _class);
                    if (subject != null) {
                        params.put("subtype", subject.toLowerCase());

                    }
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() == null) return;
    }
}
