package com.example.boxuegu.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boxuegu.R;
import com.example.boxuegu.adapter.CourseAdapter;
import com.example.boxuegu.entity.CourseEntity;
import com.example.boxuegu.utils.GsonUtils;
import com.example.boxuegu.utils.LogUtils;
import com.reactnativenavigation.NavigationActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CourseActivity extends BaseActivity {
    RecyclerView mRecyclerView;
    CourseEntity[] dataSet = new CourseEntity[1];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        init();
    }

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.course_rv_list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        CourseAdapter mAdapter = new CourseAdapter(dataSet);
//        mRecyclerView.setAdapter(mAdapter);
        fetchData();
    }

    private void fetchData() {
        OkHttpClient client = new OkHttpClient();

        try {
            Request request = new Request.Builder().url("https://www.v2ex.com/api/topics/hot.json").build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtils.i("连接失败");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
//                    注意一定要在主线程执行更新UI操作
                    runOnUiThread(() -> {
                        try {
                            dataSet = GsonUtils.fromJSONString(response.body().string(), CourseEntity[].class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        CourseAdapter mAdapter = new CourseAdapter(dataSet);
                        mRecyclerView.setAdapter(mAdapter);
                    });
                }
            });

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i("销毁");
    }
}