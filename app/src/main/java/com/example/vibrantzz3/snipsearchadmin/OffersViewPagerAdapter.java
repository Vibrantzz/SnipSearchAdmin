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

public class OffersViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> FragmentList= new ArrayList<>();
    private final List<String> FragmentListTitles= new ArrayList<>();
    private final Bundle fragmentBundle;


    public OffersViewPagerAdapter(FragmentManager fm, Bundle data) {
        super(fm);
        fragmentBundle = data;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ActiveOffers f1 = new ActiveOffers();
                // Pass extra values using bundle extras as arguments.
                f1.setArguments(this.fragmentBundle);
                return f1;
            case 1:
                InactiveOffersFragment f2 = new InactiveOffersFragment();
                // Pass extra values using bundle extras as arguments.
                f2.setArguments(this.fragmentBundle);
                return f2;
            /*case 2:
                PassedFragment f3 = new PassedFragment();
                // Pass extra values using bundle extras as arguments.
                f3.setArguments(this.fragmentBundle);
                return f3;*/
            default:
                return new ActiveOffers();
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
