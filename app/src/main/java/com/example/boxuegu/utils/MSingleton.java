package com.example.boxuegu.utils;

public abstract class MSingleton<T> {
    private T mInstance;

    protected abstract T create();

    public T getInstance() {
        synchronized (this) {
            if (mInstance == null) {
                mInstance = create();
            }
            return mInstance;
        }
    }
}
