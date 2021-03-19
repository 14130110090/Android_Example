// ITestManager.aidl
package com.example.boxuegu.aidl;

// Declare any non-default types here with import statements
import com.example.boxuegu.aidl.User;

interface ITestManager {

    List<User> getUserList();

    void addUser(in User user);
}
