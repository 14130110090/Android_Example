package com.example.boxuegu.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.boxuegu.aidl.ITestManager;
import com.example.boxuegu.aidl.TestManager;
import com.example.boxuegu.utils.MSingleton;


public class MyService extends Service {
    public MyService() {
    }

    Binder binder = new TestManager();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
