package com.example.eduguide10;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.List;
import androidx.core.content.ContextCompat;
import android.content.res.ColorStateList;
import androidx.core.content.ContextCompat;
import android.graphics.Color;


public class CourseListActivity extends AppCompatActivity {

    LinearLayout coursesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        coursesContainer = findViewById(R.id.coursesContainer);

        String group = getIntent().getStringExtra("group");
        setTitle(group + " Courses");

        List<Course> courseList = DataHolder.getInstance().getCoursesForGroup(group);
        if (courseList == null) {
            finish();
            return;
        }

        for (Course course : courseList) {

            MaterialButton btn = new MaterialButton(this, null, com.google.android.material.R.attr.materialButtonOutlinedStyle);
            btn.setText(course.getTitle());
            btn.setAllCaps(false);
            // Inside your method:
            btn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.buttonBackground));
            btn.setTextColor(Color.WHITE); // or Color.WHITE depending on contrast

// set layout params and margin as needed
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

// Outer spacing (distance from other views)
            lp.setMargins(0, 25, 0, 25);
            btn.setLayoutParams(lp);

// Inner spacing (distance between text and button edges)
            int paddingHorizontal = 32; // in px
            int paddingVertical = 24;   // in px
            btn.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);



            btn.setOnClickListener(v -> {
                Intent detailIntent = new Intent(CourseListActivity.this, CourseDetailActivity.class);
                detailIntent.putExtra("group", group);
                detailIntent.putExtra("fileName", course.getFileName());
                startActivity(detailIntent);
            });

            coursesContainer.addView(btn);
        }
    }
}
