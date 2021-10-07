package ir.selfino.android.Function.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

import ir.selfino.android.Fragment.DaysFragment;
import ir.selfino.android.Function.Models.Day;
import ir.selfino.android.Interfaces.DaysListener;

/**
 * Created by Alireza on 9/29/2017.
 */

public class DaysPagerAdapter extends FragmentPagerAdapter
{
    private List<Day> days;
    private DaysListener listener;
    public DaysPagerAdapter(FragmentManager fm,List<Day>days,DaysListener listener)
    {
        super(fm);
        this.days=days;
        this.listener=listener;
    }

    @Override
    public Fragment getItem(int position)
    {
        return DaysFragment.newInstance(days.get(position).getId(),listener);
    }

    @Override
    public int getCount()
    {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        if(position<7)
        {
            Day today = days.get(position);
            String title = today.getName() + "\n" + today.getDate();
            return title;
        }
        else
            return "";
    }
}
