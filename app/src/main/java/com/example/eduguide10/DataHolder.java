package com.example.eduguide10;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHolder {
    private static DataHolder instance;
    private Map<String, List<Course>> coursesMap = new HashMap<>();

    // Private constructor for singleton
    private DataHolder() {
    }

    public static synchronized DataHolder getInstance() {
        if (instance == null) instance = new DataHolder();
        return instance;
    }

    // Load data from assets JSON file
    public void loadData(Context context) {
        try {
            InputStream is = context.getAssets().open("course_data.json");
            InputStreamReader reader = new InputStreamReader(is);
            Type mapType = new TypeToken<Map<String, List<Course>>>() {}.getType();

            coursesMap = new Gson().fromJson(reader, mapType);
            reader.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public List<Course> getCoursesForGroup(String group) {
        return coursesMap.get(group);
    }

    public Course getCourseByFileName(String group, String fileName) {
        List<Course> list = getCoursesForGroup(group);
        if (list != null) {
            for (Course c : list) {
                if (c.getFileName().equals(fileName)) return c;
            }
        }
        return null;
    }
}
