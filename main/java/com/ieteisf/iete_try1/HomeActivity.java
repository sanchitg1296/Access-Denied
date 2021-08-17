package com.ieteisf.iete_try1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ArrayList<Integer> queue;
    BottomNavigationView bottomnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListener);


        queue=new ArrayList<Integer>();
        queue.add(R.id.home);
        queue.add(R.id.home);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            try {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        queue.add(R.id.home);
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.sponsor:
                        queue.add(R.id.sponsor);
                        selectedFragment = new SponsorFragment();
                        break;
                    case R.id.info:
                        queue.add(R.id.info);
                        selectedFragment = new InfoFragment();
                        break;
                    case R.id.timeline:
                        queue.add(R.id.timeline);
                        selectedFragment = new TimelineFragment();
                        break;
                    case R.id.speaker:
                        queue.add(R.id.speaker);
                        selectedFragment = new SpeakerFragment();
                        break;
                }
                // It will help to replace the
                // one fragment to other.
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .addToBackStack(null)
                        .commit();
            }catch (Exception e){
                Toast.makeText(HomeActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }

            return true;
        }
    };

    @Override
    public void onBackPressed() {
        bottomnav = findViewById(R.id.bottom_navigation);
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
            if(queue.size()!=0)
            {

                queue.remove(queue.size()-1);

                if(queue.size()==0)
                {
                    finish();
                }
                else {
                    int top = queue.get(queue.size() - 1);
                    bottomnav.setSelectedItemId(top);
                    queue.remove(queue.size() - 1);

                }

            }

        } else {
            getFragmentManager().popBackStack();
        }
    }
}