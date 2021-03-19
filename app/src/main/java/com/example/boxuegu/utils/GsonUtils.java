package com.example.boxuegu.utils;

import com.example.boxuegu.entity.CourseEntity;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonUtils {
    public static CourseEntity[] fromJSONString(String s, Class<CourseEntity[]> type) {
        Gson gson = new Gson();
        return gson.fromJson(s, type);
    }

    public static String toJSONString(Object s) {
        Gson gson = new Gson();
        return gson.toJson(s);
    }
}
