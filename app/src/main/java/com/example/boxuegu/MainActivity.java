package com.example.boxuegu;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.Process;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.boxuegu.activity.BaseActivity;
import com.example.boxuegu.activity.CourseActivity;
import com.example.boxuegu.aidl.ITestManager;
import com.example.boxuegu.aidl.TestManager;
import com.example.boxuegu.aidl.User;
import com.example.boxuegu.preloadreact.ReactNativePreLoader;
import com.example.boxuegu.service.MyService;
import com.example.boxuegu.utils.LogUtils;

import java.io.File;
import java.io.FileDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class MainActivity extends BaseActivity {
    Button bt_rn;
    Button bt_service;
    ImageView iv_test;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.i("oncreate");

        init(1);
    }

    private void init(int a) {
        LogUtils.i("当前值", a);
        bt_rn = findViewById(R.id.main_bt_rn);
        bt_service = findViewById(R.id.main_bt_service);
//        bt_rn.post(new Runnable() {
//            @Override
//            public void run() {
//                LogUtils.i("高度2",bt_rn.getMeasuredHeight());
//            }
//        });
//        ViewTreeObserver observer =bt_rn.getViewTreeObserver();
//        observer.addOnGlobalLayoutListener(() -> {
//            LogUtils.i("高度4",bt_rn.getMeasuredHeight());
//        });
        bt_rn.setOnClickListener(onClickListener);
        bt_service.setOnClickListener(onClickListener);
    }

    /**
     * TODO 测试一下获取view的位置
     *
     * @param v
     * @return
     */
    public Rect getViewBounds(View v) {
        int[] pos = new int[2];
        v.getLocationOnScreen(pos);
        return new Rect(pos[0], pos[1], pos[0] + v.getWidth(), pos[1] + v.getHeight());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
//            LogUtils.i("高度3",bt_rn.getMeasuredHeight());

//            ReactNativePreLoader.preLoad(MainActivity.this,"WishListPage");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i("onstart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i("onrestart");
    }


    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i("onpause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i("onresume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i("onstop");
    }

    @Override
    protected void onDestroy() {
        LogUtils.i("ondestroy");
        super.onDestroy();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.i("onnewIntent");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.i("onsaveinstancestate");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.i("onrestoreinstancestate");
    }

    protected Runnable findStaticMain(String className) {
        Class<?> cl;

        try {
            cl = Class.forName(className, true, getClassLoader());
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(
                    "Missing class when invoking static main " + className,
                    ex);
        }
        Method[] methods = cl.getDeclaredMethods();
        Method m = null;
        for (int i = 0; i < methods.length; i++) {
            if ("init".equals(methods[i].getName())) {
                Class<?>[]  paras = methods[i].getParameterTypes();
                for(int j=0;j<paras.length;j++){
                    System.out.println(paras[j]);
                }
            }
        }
        try {
            m = cl.getDeclaredMethod("init", int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        LogUtils.i(Process.myPid(), Process.myTid(), Process.myUid(), Process.getThreadPriority(Process.myTid()));

        try {
            if (m != null)
                m.invoke(MainActivity.this, 231);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.main_bt_rn:
//                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                    startActivity(intent);

                    findStaticMain("com.example.boxuegu.MainActivity");
                    break;
                case R.id.main_bt_service:
                    Intent intent2 = new Intent();
                    intent2.setClassName("com.mrnapp", "com.mrnapp.MainActivity");
                    FileDescriptor d;
//                    intent2.setClassName("com.example.boxuegu", "com.example.boxuegu.service.MyService");

//                    bindService(intent2, new ServiceConnection() {
//                        @Override
//                        public void onServiceConnected(ComponentName name, IBinder service) {
//                            LogUtils.i("获取连接了哦");
//                            ITestManager testManager = (ITestManager) ITestManager.Stub.asInterface(service);
//                            try {
//                                testManager.addUser(new User(1, "ddd"));
//                                LogUtils.i(testManager.getUserList().get(0).name);
//                            } catch (RemoteException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onServiceDisconnected(ComponentName name) {
//                            LogUtils.i("连接关闭");
//                        }
//                    }, BIND_AUTO_CREATE);
                    break;
            }
        }
    };
}