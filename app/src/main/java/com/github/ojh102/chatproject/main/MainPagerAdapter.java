package com.github.ojh102.chatproject.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OhJaeHwan on 2016-09-24.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private final List<FragmentInfo> mFragmentInfoList = new ArrayList<FragmentInfo>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(int iconResId, String title, Fragment fragment) {
        FragmentInfo info = new FragmentInfo(iconResId, title, fragment);
        mFragmentInfoList.add(info);
    }

    public FragmentInfo getFragmentInfo(int position) {
        return mFragmentInfoList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentInfoList.get(position).getFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentInfoList.get(position).getTitleText();
    }

    @Override
    public int getCount() {
        return mFragmentInfoList.size();
    }


    public static class FragmentInfo {
        private int iconResId;
        private String text;
        private Fragment fragment;

        public FragmentInfo(int iconResId, String text, Fragment fragment) {
            this.iconResId = iconResId;
            this.text = text;
            this.fragment = fragment;
        }

        public int getIconResId() {
            return iconResId;
        }

        public String getTitleText() {
            return text;
        }

        public Fragment getFragment() {
            return fragment;
        }
    }

}
