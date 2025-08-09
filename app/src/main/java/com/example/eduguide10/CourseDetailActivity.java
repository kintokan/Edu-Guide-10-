package com.example.eduguide10;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {

    LinearLayout detailsContainer;
    Button btnLearnMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        detailsContainer = findViewById(R.id.detailsContainer);
        btnLearnMore = findViewById(R.id.btnLearnMore);

        String group = getIntent().getStringExtra("group");
        String fileName = getIntent().getStringExtra("fileName");

        Course course = DataHolder.getInstance().getCourseByFileName(group, fileName);
        if (course == null) {
            addSection(detailsContainer, "Error", List.of("Course details not found."));
            return;
        }

        // Set toolbar title
        setTitle(course.getTitle());


        TextView headingTextView = findViewById(R.id.courseHeadingTextView);


// Set heading in page
        headingTextView.setText(course.getTitle());
        // Add sections one-by-one
        addSection(detailsContainer, "About", course.getAbout());
        addSection(detailsContainer, "Further Studies", course.getFurtherStudies());
        addSection(detailsContainer, "Competitive Exams", course.getCompetitiveExams());
        addSection(detailsContainer, "Jobs", course.getJobs());
        addSection(detailsContainer, "Summary", course.getSummary());

        // Learn More Button click
        btnLearnMore.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://kintokan.live"));
            startActivity(browserIntent);
        });
    }

    /** Adds a section title with bullet points **/
    private void addSection(LinearLayout container, String sectionTitle, List<String> items) {
        if (items == null || items.isEmpty()) return;

        // Section title
        TextView titleView = new TextView(this);
        titleView.setText(sectionTitle);
        titleView.setTextSize(18);
        titleView.setTypeface(Typeface.DEFAULT_BOLD);
        titleView.setTextColor(Color.parseColor("#26336B"));
        titleView.setPadding(0, 24, 0, 8);
        container.addView(titleView);

        // Bullet point items
        for (String item : items) {
            TextView bulletView = new TextView(this);
            bulletView.setText("• " + item);
            bulletView.setTextSize(15);
            bulletView.setTextColor(Color.parseColor("#444444"));
            bulletView.setPadding(8, 4, 0, 4);
            container.addView(bulletView);
        }
    }
}
