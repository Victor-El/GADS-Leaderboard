package me.codeenzyme.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import me.codeenzyme.gadsleaderboard.models.TopLearner;
import me.codeenzyme.gadsleaderboard.models.TopSkilled;
import me.codeenzyme.gadsleaderboard.viewmodels.LearnersViewModel;
import me.codeenzyme.gadsleaderboard.views.adapters.TabsPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> tabNames;
    private ViewPager pager;
    private FragmentStatePagerAdapter pagerAdapter;
    private Toolbar toolbar;

    private Button goToSubmitProjectBtn;

    private LearnersViewModel learnersViewModel;

    private ArrayList<TopLearner> topLearnerArrayList;
    private ArrayList<TopSkilled> topSkilledArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        goToSubmitProjectBtn = findViewById(R.id.go_to_submit_project);

        goToSubmitProjectBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, ProjectSubmissionActivity.class));
        });

        topLearnerArrayList = new ArrayList<TopLearner>();
        topSkilledArrayList = new ArrayList<TopSkilled>();

        tabNames = new ArrayList<>();
        tabNames.add(getString(R.string.learning_leaders));
        tabNames.add(getString(R.string.skill_leaders));

        pager = findViewById(R.id.pager);
        pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), tabNames);
        pager.setAdapter(pagerAdapter);


//        learnersViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LearnersViewModel.class);
//        learnersViewModel.getTopLearners().observe(this, topLearners -> {
//            Log.d("MainActivity", topLearners.get(1).getName());
//            topLearnerArrayList.addAll(topLearners);
//        });
//        learnersViewModel.getTopSkilled().observe(this, topSkilleds -> {
//            topSkilledArrayList.addAll(topSkilleds);
//        });

    }
}