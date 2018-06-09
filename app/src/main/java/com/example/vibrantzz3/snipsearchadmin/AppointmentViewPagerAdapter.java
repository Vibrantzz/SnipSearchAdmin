package com.example.vibrantzz3.snipsearchadmin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vibrantzz3 on 2/5/2018.
 */

public class AppointmentViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> FragmentList= new ArrayList<>();
    private final List<String> FragmentListTitles= new ArrayList<>();
    private final Bundle fragmentBundle;


    public AppointmentViewPagerAdapter(FragmentManager fm, Bundle data) {
        super(fm);
        fragmentBundle = data;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RequestFragment f1 = new RequestFragment();
                // Pass extra values using bundle extras as arguments.
                f1.setArguments(this.fragmentBundle);
                return f1;
            case 1:
                ConfirmedFragment f2 = new ConfirmedFragment();
                // Pass extra values using bundle extras as arguments.
                f2.setArguments(this.fragmentBundle);
                return f2;
            case 2:
                PastFragment f3 = new PastFragment();
                // Pass extra values using bundle extras as arguments.
                f3.setArguments(this.fragmentBundle);
                return f3;
            case 3:
                CancelledFragment f4 = new CancelledFragment();
                // Pass extra values using bundle extras as arguments.
                f4.setArguments(this.fragmentBundle);
                return f4;
            default:
                return new RequestFragment();
        }
    }
    @Override
    public int getCount() {
        return FragmentListTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTitles.get(position);
    }

    public void AddFragment(Fragment fragment, String Titles) {

        FragmentList.add(fragment);
        FragmentListTitles.add(Titles);

    }
}
