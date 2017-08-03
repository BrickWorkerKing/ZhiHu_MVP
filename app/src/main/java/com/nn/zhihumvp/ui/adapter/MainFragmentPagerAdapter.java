package com.nn.zhihumvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nn.zhihumvp.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author LiuZongRui  16/11/17
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private Context context;

    private String tabTitles[] = {"最新", "栏目"};
    private int tabIcons[] = {R.drawable.ic_latest_news, R.drawable.ic_section};

    public MainFragmentPagerAdapter(@NonNull FragmentManager fm, @NonNull Context context, @NonNull List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //此方法用来显示tab上的名字，此方法必须重写，否则将无法显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
//        return titleList.get(position);
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    public View getTabView(int position) {
        View tabView = LayoutInflater.from(context).inflate(R.layout.item_mian_tab, null);
        AppCompatImageView ivIcon = ButterKnife.findById(tabView, R.id.iv_icon);
        TextView tvText = ButterKnife.findById(tabView, R.id.tv_text);
        ivIcon.setImageResource(tabIcons[position]);
        tvText.setText(tabTitles[position]);
        return tabView;
    }
}
