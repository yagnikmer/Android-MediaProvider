package com.mer.mediaproviderex.app;

import android.Manifest;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mer.mediaproviderex.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

public class MusicLibActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        mViewPager = findViewById(R.id.vppager);
        mTabLayout = findViewById(R.id.tllayout);

        ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE);
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isDataShow = true;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == -1) {
                isDataShow = false;
                finish();
                break;
            }
        }
        if (isDataShow) {
            MusicCateloryAdapter sectionsPagerAdapter = new MusicCateloryAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(sectionsPagerAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
        }
    }

}