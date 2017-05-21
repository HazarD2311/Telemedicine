package com.example.hazard.telemedicine.welcome;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hazard.telemedicine.Const;
import com.example.hazard.telemedicine.R;

import me.relex.circleindicator.CircleIndicator;

public class WelcomeActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        viewPager = (ViewPager) findViewById(R.id.welcome_view_pager);
        pagerAdapter = new WelcomePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        CircleIndicator circleIndicator = (CircleIndicator) findViewById(R.id.welcome_indicator);
        circleIndicator.setViewPager(viewPager);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class WelcomePageAdapter extends FragmentPagerAdapter {

        public WelcomePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case Const.WELCOME_FRAGMENT:
                    return WelcomeFragment.getInstance();
                case Const.DOCTOR_OR_PATIENT_FRAGMENT:
                    return DoctorOrPatientFragment.getInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return Const.WELCOME_PAGE_COUNT;
        }

    }
}
