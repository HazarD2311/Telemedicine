package com.example.hazard.telemedicine.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.hazard.telemedicine.Const;
import com.example.hazard.telemedicine.R;
import com.example.hazard.telemedicine.fragment.ChatFragment;

public class ChatDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_doctor);

        // Инициализируем ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Добавление кнопки назад в toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Принимаем от DoctorTasksFragment
        Bundle bundle = new Bundle();
        bundle.putString(Const.PATIENT_NAME, getIntent().getStringExtra(Const.PATIENT_NAME));
        //TODO принять во фрагменте бандл и соединить в чате Доктора и Пациента

        //инициализация и открытие фрагмента
        FragmentManager fm = getSupportFragmentManager();
        Fragment chatFragment = new ChatFragment();
        chatFragment.setArguments(bundle);
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.doctor_chat_fragment_container, chatFragment);
        ft.commit();

    }
}
