package com.example.eduguide10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnDiploma, btnITI, btnIIIT, btnIntermediate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDiploma = findViewById(R.id.btnDiploma);
        btnITI = findViewById(R.id.btnITI);
        btnIIIT = findViewById(R.id.btnIIIT);
        btnIntermediate = findViewById(R.id.btnIntermediate);

        View.OnClickListener groupListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String group = null;
                if (v == btnDiploma) group = "Diploma";
                else if (v == btnITI) group = "ITI";
                else if (v == btnIIIT) group = "IIIT";
                else if (v == btnIntermediate) group = "Intermediate";

                Intent intent = new Intent(MainActivity.this, CourseListActivity.class);
                intent.putExtra("group", group);
                startActivity(intent);
            }
        };

        btnDiploma.setOnClickListener(groupListener);
        btnITI.setOnClickListener(groupListener);
        btnIIIT.setOnClickListener(groupListener);
        btnIntermediate.setOnClickListener(groupListener);

        // Load JSON data on app start
        DataHolder.getInstance().loadData(this);
    }
}