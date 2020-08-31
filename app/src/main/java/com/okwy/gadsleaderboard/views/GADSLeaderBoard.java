package com.okwy.gadsleaderboard.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.okwy.gadsleaderboard.R;
import com.okwy.gadsleaderboard.adapters.TabLayoutAdapter;

public class GADSLeaderBoard extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gads_leaderboard);
        Toolbar toolbar = findViewById(R.id.toolbar);


        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);

        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(getSupportFragmentManager(), this);

        //add fragments to tabLayout
        tabLayoutAdapter.addFragment(new TopLearnersFragment(), getString(R.string.learning_leaders));
        tabLayoutAdapter.addFragment(new SkillIQFragment(), getString(R.string.skill_iq_leaders));

        viewPager.setAdapter(tabLayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }



    public void gotoGADSSubmission(View view) {
        startActivity(new Intent(this, GADSSubmission.class));
    }
}
