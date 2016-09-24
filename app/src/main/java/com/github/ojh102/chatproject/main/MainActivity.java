package com.github.ojh102.chatproject.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.main.chat.ChatFragment;
import com.github.ojh102.chatproject.main.friend.FriendFragment;
import com.github.ojh102.chatproject.main.setting.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    MainPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        initView();
    }

    private void initView() {
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(R.drawable.ic_people_outline_white_24dp, "친구", FriendFragment.newInstance());
        mAdapter.addFragment(R.drawable.ic_message_white_24dp, "메세지", ChatFragment.newInstance());
        mAdapter.addFragment(R.drawable.ic_more_horiz_white_24dp, "더보기", SettingFragment.newInstance());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
            mTabLayout.getTabAt(i).setIcon(mAdapter.getFragmentInfo(i).getIconResId());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Fragment fragment = mAdapter.getFragmentInfo(0).getFragment();
        if(fragment != null) {
            if(fragment instanceof FriendFragment) {
                ((FriendFragment) fragment).getData();
            } else if(fragment instanceof ChatFragment) {
                ((ChatFragment)fragment).getData();
            }
        }
    }
}
