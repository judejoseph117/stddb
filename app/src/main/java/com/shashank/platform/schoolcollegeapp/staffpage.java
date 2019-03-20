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

public class staffpage extends AppCompatActivity {
    private Toolbar toolbr;
    private TabLayout tablout;
    private ViewPager viewpger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffpage);
        toolbr=findViewById(R.id.toolbar);
        toolbr.setTitle("Staff");
        setSupportActionBar(toolbr);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//used to add back arrow
        viewpger=findViewById(R.id.viewpager);
        setupViewpager(viewpger);
        tablout=findViewById(R.id.tblayt);
        tablout.setupWithViewPager(viewpger);
    }

    private void setupViewpager(ViewPager viewpger) {
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewpger.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    staffhomfra hm=new staffhomfra();
                    return hm;
                case 1:
                    staffmarkfra  mark = new staffmarkfra();//layoutname obj=new layoutname;
                    return mark;
                case 2:
                    staffattentfra att=new staffattentfra();
                    return att;


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
            switch (position)
            {
                case 0:
                    return "Home";
                case 1:
                    return "Mark";
                case 2:
                    return "Attendance";

                default:return null;
            }
        }
    }

}
