package com.example.boxuegu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.boxuegu.MainActivity;
import com.example.boxuegu.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {
    TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        tv_version = findViewById(R.id.splash_tv_version);
        try {
            PackageInfo info;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                info = getPackageManager().getPackageInfo(getOpPackageName(), 0);
            } else {
                info = getPackageManager().getPackageInfo(getPackageName(), 0);
            }
            if (info != null) {
                tv_version.setText(String.format(getResources().getString(R.string.splash_version), info.versionName));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tv_version.setText("V");
        }
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(task, 3000);
    }

}