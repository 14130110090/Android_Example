package com.example.boxuegu;

import android.app.Application;
import android.content.Context;

import com.reactnativenavigation.NavigationApplication;
import com.facebook.react.ReactNativeHost;
import com.reactnativenavigation.react.NavigationReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by Song on 2017/2/13.
 */

public class MainApplication extends NavigationApplication {

    public static Context appContext;
    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appContext = getApplicationContext();
        
    }

    private final ReactNativeHost mReactNativeHost = new NavigationReactNativeHost(this) {

        @Nullable
        @Override
        protected String getJSBundleFile() {
//            File file = new File (FileConstant.JS_BUNDLE_LOCAL_PATH);
//            if(file != null && file.exists()) {
//                return FileConstant.JS_BUNDLE_LOCAL_PATH;
//            } else {
                return super.getJSBundleFile();
//            }
        }

        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    /**
     *包名
     */
    public String getAppPackageName() {
        return this.getPackageName();
    }

    /**
     * 获取Application实例
     */
    public static MainApplication getInstance() {
        return instance;
    }





}
