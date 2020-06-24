package com.learning.skilclasses.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.learning.skilclasses.R;
import com.learning.skilclasses.Utilities.ApiUrl;
import com.learning.skilclasses.models.Message;
import com.learning.skilclasses.preferences.UserSession;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> messageList;
    private Context context;
    UserSession userSession;
    private OkHttpClient okHttpClient;

    public MessageAdapter(List<Message> messageList, Context context) {
        this.messageList = messageList;
        this.context = context;
        userSession = new UserSession(context);
        BigImageViewer.initialize(GlideImageLoader.with(context));
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout, parent, false);
        return new MessageAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        try {
            holder.actualMessage.setText(message.getMessage());
            holder.progressBar.setVisibility(View.VISIBLE);
            Glide.get(context).clearMemory();
            Glide.with(context).load(displayPicture(message.getUser_id()))
                    .into(holder.circleImageView);
            if (message.getImage().length() == 0) {
                holder.progressBar.setVisibility(View.GONE);
                holder.doubtImage.setVisibility(View.GONE);
            } else {
                try {
                    Glide.get(context).clearMemory();
                    Glide.with(context).load(message.getImage()).listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                            .fitCenter()
                            .into(holder.doubtImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Log.d("image_doubt", message.getImage());
            holder.timestamp.setText(message.getSentat() + "");
            try {
                String response = updateLogin(ApiUrl.GET_USER_BY_ID, message.getUser_id(), 1);
                Log.d("response", "this is response" + response);
                JSONObject jsonObject = new JSONObject(response);
                boolean status = jsonObject.getBoolean("status");
                if (!status) {
                } else {
                    holder.senderUsername.setText(jsonObject.getString("username"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sendername)
        TextView senderUsername;
        @BindView(R.id.actual_doubt)
        TextView actualMessage;
        @BindView(R.id.doubt_image)
        ImageView doubtImage;
        @BindView(R.id.chatlayout)
        LinearLayout linearLayout;
        @BindView(R.id.profile)
        CircleImageView circleImageView;
        @BindView(R.id.timestamp)
        TextView timestamp;
        @BindView(R.id.image_progress)
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public String displayPicture(String id) throws Exception {
        String picture_url = getUserProfilePicture(ApiUrl.GET_USER_PROFILE_PIC, id);
        JSONObject jsonObject = new JSONObject(picture_url);
        if (jsonObject.getBoolean("status")) {

            return jsonObject.getString("image");
        } else {
            return "R.drawable.profile";
        }
    }

    public String getUserProfilePicture(String url, String id) throws Exception {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", id)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private String updateLogin(String url, String id, int i) throws Exception {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", id)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public void update(List<Message> datas) {
        messageList.clear();
        messageList.addAll(datas);
        notifyDataSetChanged();
    }
}
