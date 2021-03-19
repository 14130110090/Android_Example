package com.example.boxuegu.aidl;

import android.os.RemoteException;

import com.example.boxuegu.utils.LogUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestManager extends ITestManager.Stub{
    CopyOnWriteArrayList<User> list = new CopyOnWriteArrayList<>();

    @Override
    public List<User> getUserList() throws RemoteException {
        return list;
    }

    @Override
    public void addUser(User user) throws RemoteException {
         if(user!=null)list.add(user);
    }
}
