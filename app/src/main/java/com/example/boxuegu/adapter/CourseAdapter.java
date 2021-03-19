package com.example.boxuegu.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.boxuegu.R;
import com.example.boxuegu.entity.CourseEntity;
import com.example.boxuegu.utils.LogUtils;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private CourseEntity[] dataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;
        private final Context context;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.course_tv_name);
            imageView = (ImageView) view.findViewById(R.id.course_iv_avatar);
            context =view.getContext();
        }
    }

    public CourseAdapter(CourseEntity[] dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_course_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (dataSet != null && dataSet[position] != null) {
            holder.textView.setText(dataSet[position].getContent());
            Glide.with(holder.context).load(dataSet[position].getNode().getAvatar_large()).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
