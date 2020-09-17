package me.codeenzyme.gadsleaderboard.views.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import me.codeenzyme.gadsleaderboard.models.TopLearner;
import me.codeenzyme.gadsleaderboard.models.TopSkilled;
import me.codeenzyme.gadsleaderboard.views.fragments.SkilledTabFragment;
import me.codeenzyme.gadsleaderboard.views.fragments.TabFragment;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> list;
    // private ArrayList<TopLearner> listOfTopLearners;
    // private ArrayList<TopSkilled> listOfTopSkilled;

    public static final String TOP_LIST_KEY = "top-learners-list";

    /*public TabsPagerAdapter(@NonNull FragmentManager fm, ArrayList<String> list, ArrayList<TopSkilled> topSkilledArrayList, ArrayList<TopLearner> topLearnerArrayList) {
        super(fm);
        this.list = list;
        this.listOfTopLearners = topLearnerArrayList;
        this.listOfTopSkilled = topSkilledArrayList;
    }*/

    public TabsPagerAdapter(@NonNull FragmentManager fm, ArrayList<String> list) {
        super(fm);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new TabFragment();
        Fragment skilledFragment = new SkilledTabFragment();
        Bundle args = new Bundle();
        args.putString(TabFragment.TAB_KEY, list.get(position));
        /*if (position == 0) {
            args.putParcelableArrayList(TOP_LIST_KEY, listOfTopLearners);
        } else {
            args.putParcelableArrayList(TOP_LIST_KEY, listOfTopSkilled);
        }*/
        fragment.setArguments(args);
        skilledFragment.setArguments(args);
        Fragment fragmentToReturn = null;
        if (position == 0) {
            fragmentToReturn = fragment;
        } else {
            fragmentToReturn = skilledFragment;
        }
        return fragmentToReturn;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
