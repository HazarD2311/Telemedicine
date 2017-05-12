package com.example.hazard.telemedicine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hazard.telemedicine.adapter.TabPagerFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabs();
    }

    private void initTabs() {
        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        viewPager = (ViewPager) findViewById(R.id.main_pager);
        TabPagerFragmentAdapter adapter = new TabPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(Const.PROFILE_TAB).setIcon(R.drawable.ic_person_black_24dp);
        tabLayout.getTabAt(Const.APPEAL_TAB).setIcon(R.drawable.ic_healing_black_24dp);
        tabLayout.getTabAt(Const.MESSAGES_TAB).setIcon(R.drawable.ic_message_black_24dp);
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
            case R.id.action_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
