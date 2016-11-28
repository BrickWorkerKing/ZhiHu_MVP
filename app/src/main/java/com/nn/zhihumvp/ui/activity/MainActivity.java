package com.nn.zhihumvp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.nn.zhihumvp.R;
import com.nn.zhihumvp.base.BaseActivity;
import com.nn.zhihumvp.ui.adapter.MainFragmentPagerAdapter;
import com.nn.zhihumvp.ui.fragment.LatestNewsFragment;
import com.nn.zhihumvp.ui.fragment.SectionListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTabLayout();
    }

    private void initTabLayout() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new LatestNewsFragment());
        fragmentList.add(new SectionListFragment());
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), this, fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        int len = tabLayout.getTabCount();
        for (int i = 0; i < len; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
                tabLayout.setSelected(i == 0);
            }
        }
    }


}
