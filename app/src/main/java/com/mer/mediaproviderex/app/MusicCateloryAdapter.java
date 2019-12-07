package com.mer.mediaproviderex.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MusicCateloryAdapter extends FragmentPagerAdapter {

    String[] mTabList = {"Internal", "External"};

    public MusicCateloryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return setIndexwithFragment(position);
    }

    @Override
    public int getCount() {
        return mTabList.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList[position];
    }

    private Fragment setIndexwithFragment(int index) {
        MusicListFragment frag = new MusicListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        frag.setArguments(bundle);
        return frag;
    }
}
