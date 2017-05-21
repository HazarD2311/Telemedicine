package com.example.hazard.telemedicine.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hazard.telemedicine.Const;
import com.example.hazard.telemedicine.R;
import com.example.hazard.telemedicine.SharedPref;
import com.example.hazard.telemedicine.activity.AuthorizationActivity;

public class DoctorOrPatientFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_doctor_or_patient;
    private View view;
    private Button iAmDoctor;
    private Button iAmPatient;


    public static DoctorOrPatientFragment getInstance() {
        Bundle args = new Bundle();
        DoctorOrPatientFragment fragment = new DoctorOrPatientFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        iAmDoctor = (Button) view.findViewById(R.id.i_am_doctor);
        iAmDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDoctor();
                Intent intent = new Intent(getActivity(), AuthorizationActivity.class);
                startActivity(intent);
            }
        });

        iAmPatient = (Button) view.findViewById(R.id.i_am_patient);
        iAmPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePatient();
                Intent intent = new Intent(getActivity(), AuthorizationActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void chooseDoctor() {
        SharedPreferences.Editor edit = SharedPref.getSharedPreferences().edit();
        edit.putBoolean(Const.IS_DOCTOR, true);
        edit.putBoolean(Const.IS_PATIEND, false);
        edit.apply();
    }

    private void choosePatient() {
        SharedPreferences.Editor edit = SharedPref.getSharedPreferences().edit();
        edit.putBoolean(Const.IS_PATIEND, true);
        edit.putBoolean(Const.IS_DOCTOR, false);
        edit.apply();
    }

}
