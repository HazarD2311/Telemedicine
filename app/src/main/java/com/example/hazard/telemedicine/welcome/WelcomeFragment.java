package com.example.hazard.telemedicine.welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hazard.telemedicine.R;


public class WelcomeFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_welcome;

    private View view;

    public static WelcomeFragment getInstance() {
        Bundle args = new Bundle();
        WelcomeFragment fragment = new WelcomeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }
}
