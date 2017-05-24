package com.example.hazard.telemedicine.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hazard.telemedicine.Const;
import com.example.hazard.telemedicine.R;
import com.example.hazard.telemedicine.SharedPref;
import com.example.hazard.telemedicine.adapter.TabsAdapter;
import com.example.hazard.telemedicine.logic.ProfileSingleton;
import com.example.hazard.telemedicine.welcome.DoctorOrPatientFragment;
import com.example.hazard.telemedicine.welcome.WelcomeActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firechatUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkDoctorPatient();
        initAuth();
        initToolbar();
        initTabs();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.main_pager);
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(Const.PROFILE_TAB).setIcon(R.drawable.ic_person_black_24dp);
        tabLayout.getTabAt(Const.APPEAL_TAB).setIcon(R.drawable.ic_healing_black_24dp);
        tabLayout.getTabAt(Const.MESSAGES_TAB).setIcon(R.drawable.ic_message_black_24dp);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setOnTabSelectedListener(this);
    }

    private void checkDoctorPatient() {
        Boolean isDoctor = SharedPref.getSharedPreferences().getBoolean(Const.IS_DOCTOR, false);
        Boolean isPatient = SharedPref.getSharedPreferences().getBoolean(Const.IS_PATIEND, false);

        if (!isDoctor && !isPatient) {
            Intent intent = new Intent(this, AuthorizationActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO: сделать переход в настройки

        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
            case R.id.action_logout:
                firebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(googleApiClient);
                clearSharedPref();
                startActivity(new Intent(this, WelcomeActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearSharedPref() {
        SharedPreferences.Editor editor = SharedPref.getSharedPreferences().edit();
        editor.putBoolean(Const.IS_DOCTOR, false);
        editor.putBoolean(Const.IS_PATIEND, false);
        editor.apply();
    }

    private void initAuth() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        firebaseAuth = FirebaseAuth.getInstance();
        firechatUser = firebaseAuth.getCurrentUser();
        if (firechatUser == null) {
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            return;
        } else {
            ProfileSingleton.getInstance().setUsername(firechatUser.getDisplayName());
            if (firechatUser.getPhotoUrl() != null) {
                ProfileSingleton.getInstance().setPhotoUrl(firechatUser.getPhotoUrl().toString());
            }
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
