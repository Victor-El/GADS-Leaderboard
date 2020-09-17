package me.codeenzyme.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class ProjectSubmissionActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        toolbar = findViewById(R.id.sub_toolbar);
        setSupportActionBar(toolbar);
        ImageView backBtnImageView = findViewById(R.id.toolbar_back);
        backBtnImageView.setOnClickListener((View v) -> {
            onBackPressed();
        });
    }
}