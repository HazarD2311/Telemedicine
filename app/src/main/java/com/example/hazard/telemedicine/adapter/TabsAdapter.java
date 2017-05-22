package com.example.hazard.telemedicine.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hazard.telemedicine.Const;
import com.example.hazard.telemedicine.SharedPref;
import com.example.hazard.telemedicine.fragment.ChatFragment;
import com.example.hazard.telemedicine.fragment.DoctorTasksFragment;
import com.example.hazard.telemedicine.fragment.PatientComplaintFragment;
import com.example.hazard.telemedicine.fragment.ProfileFragment;

public class TabsAdapter extends FragmentPagerAdapter {

    //private String[] tabs;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
        /*
        tabs = new String[] {
                Const.PROFILE_TAB_NAME,
                Const.APPEAL_TAB_NAME,
                Const.MESSAGES_TAB_NAME
        };
        */
    }

    @Override
    public Fragment getItem(int position) {
        Boolean isDoctor = SharedPref.getSharedPreferences().getBoolean(Const.IS_DOCTOR, false);
        Boolean isPatient = SharedPref.getSharedPreferences().getBoolean(Const.IS_PATIEND, false);

        if (isDoctor && !isPatient) {
            switch (position) {
                case Const.PROFILE_TAB:
                    return ProfileFragment.getInstance();
                case Const.APPEAL_TAB:
                    return DoctorTasksFragment.getInstance();
                case Const.MESSAGES_TAB:
                    return ChatFragment.getInstance();
            }
        } else {
            switch (position) {
                case Const.PROFILE_TAB:
                    return ProfileFragment.getInstance();
                case Const.APPEAL_TAB:
                    return PatientComplaintFragment.getInstance();
                case Const.MESSAGES_TAB:
                    return ChatFragment.getInstance();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return Const.COUNT_OF_TABS;
    }

    /*
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
    */
}
