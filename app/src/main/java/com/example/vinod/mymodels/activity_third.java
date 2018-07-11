package com.example.vinod.mymodels;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class activity_third extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tabLayout=(TabLayout)findViewById(R.id.tab);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        setViewPageAdapter(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setViewPageAdapter(ViewPager viewPager) {
        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new oneFragment(),"One");
        viewPager.setAdapter(viewPageAdapter);

    }

    class ViewPageAdapter extends FragmentPagerAdapter{
        List<Fragment> tabs=new ArrayList<Fragment>();
        List<String> tabsTitle=new ArrayList<String>();

        public ViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabs.get(position);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return tabsTitle.get(position);
        }
        @Override
        public int getCount() {
            return ((null != tabs) ? tabs.size():0);
        }

        public void addFragment(Fragment fragment, String title){
            tabs.add(fragment);
            tabsTitle.add(title);
        }


    }
}
