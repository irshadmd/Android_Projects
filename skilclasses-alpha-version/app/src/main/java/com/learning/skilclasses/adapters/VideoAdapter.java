package com.learning.skilclasses.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.skilclasses.R;
import com.learning.skilclasses.models.Video;
import com.learning.skilclasses.preferences.UserSession;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.OkHttpClient;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<Video> videoList;
    private Context context;
    UserSession userSession;
    private OkHttpClient okHttpClient;

    public VideoAdapter(List<Video> messageList, Context context) {
        this.videoList = messageList;
        this.context = context;
        userSession = new UserSession(context);
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_layout, parent, false);
        return new VideoAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        Video video = videoList.get(position);
        try {

            holder.title.setText(video.getVdesp());
            holder.jcVideoPlayerStandard.setUp("http://www.digitalcatnyx.store/admin/" + video.getVpath(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
            holder.jcVideoPlayerStandard.thumbImageView.setImageDrawable(context.getDrawable(R.drawable.online_class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.video_title)
        TextView title;
        @BindView(R.id.videoplayer)
        JCVideoPlayerStandard jcVideoPlayerStandard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
