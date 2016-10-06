package com.github.ojh102.chatproject.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.main.message.MessageRoomFragment;
import com.github.ojh102.chatproject.main.friend.FriendFragment;
import com.github.ojh102.chatproject.main.setting.SettingFragment;
import com.github.ojh102.chatproject.util.BackPressCloseHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.github.ojh102.chatproject.main.message.detail.MessageActivity.FILTER_FCM;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    MainPagerAdapter mAdapter;

    BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        registerReceiver(mMessageReceiver, new IntentFilter(FILTER_FCM));
    }
    private void initView() {

        setSupportActionBar(mToolbar);
        backPressCloseHandler = new BackPressCloseHandler(this);

        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(R.drawable.ic_people_outline_white_24dp, "친구", FriendFragment.newInstance());
        mAdapter.addFragment(R.drawable.ic_message_white_24dp, "메세지", MessageRoomFragment.newInstance());
        mAdapter.addFragment(R.drawable.ic_more_horiz_white_24dp, "더보기", SettingFragment.newInstance());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
            mTabLayout.getTabAt(i).setIcon(mAdapter.getFragmentInfo(i).getIconResId());
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dataRefresh();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataRefresh();
    }

    private void dataRefresh() {
        if(mAdapter==null || mViewPager == null) {
            return;
        }

        Fragment fragment = mAdapter.getFragmentInfo(mViewPager.getCurrentItem()).getFragment();
        if(fragment != null) {
            if(fragment instanceof FriendFragment && ((FriendFragment) fragment).friendPresenter != null) {
                ((FriendFragment) fragment).getData();
            } else if(fragment instanceof MessageRoomFragment) {
                ((MessageRoomFragment)fragment).getData();
            }
        }
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            MessageRoomFragment fragment = (MessageRoomFragment)mAdapter.getFragmentInfo(1).getFragment();
            fragment.getData();
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }


}



