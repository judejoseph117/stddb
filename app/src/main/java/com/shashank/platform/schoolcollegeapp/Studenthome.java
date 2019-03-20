package com.shashank.platform.schoolcollegeapp;


import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class Studenthome extends AppCompatActivity {

    private Toolbar toolbar;      //Toolbar selected for intilasation must be with v7
    private ViewPager viewpager;
    private TabLayout tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studenthome);

        toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitle("Student");       // to display app name on toolbar
        setSupportActionBar(toolbar); //to convert actionbar to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enable back button on toolbar
        viewpager=findViewById(R.id.view_pagerxml);

        setupViewpager(viewpager);  //create method
        tablayout=findViewById(R.id.tab_layoutxml);
        tablayout.setupWithViewPager(viewpager); //connect viewpager with tablayout
    }

    private void setupViewpager(ViewPager viewpager) {

        viewpageradapter adapter=new viewpageradapter(getSupportFragmentManager()); //setting adapter for enabling data transferring in fragment
        viewpager.setAdapter(adapter);

    }
    class viewpageradapter extends FragmentPagerAdapter {

        //impliment methods and after that "create constructer matching super"

        public viewpageradapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {

                case 0:
                    Stuhome home=new  Stuhome();
                    return home;
                case 1:
                    stuattend attnd=new stuattend();
                    return attnd;
                case 2:
                    sturesult reslt=new sturesult();
                    return reslt;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){

                case 0:
                    return "Home";
                case 1:
                    return "Attendance";
                case 2:
                    return "Result";
                default:
                    return null;
            }
        }
    }
}
