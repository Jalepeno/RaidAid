package dk.nicolajpedersen.raidaid.Logic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import java.util.List;

import dk.nicolajpedersen.raidaid.ViewpageFragments.CalendarListFragment;
import dk.nicolajpedersen.raidaid.ViewpageFragments.ClanListFragment;
import dk.nicolajpedersen.raidaid.ViewpageFragments.FriendListFragment;

/**
 * Created by Nicolaj on 20-04-2015.
 */
public class RAPagerAdapter extends FragmentPagerAdapter {

    ClanListFragment clanListFragment;
    FriendListFragment friendListFragment;
    CalendarListFragment calendarListFragment;


    public RAPagerAdapter(FragmentManager fm) {
        super(fm);
        clanListFragment = new ClanListFragment();
        friendListFragment = new FriendListFragment();
        calendarListFragment = new CalendarListFragment();

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return clanListFragment;
            case 1:
                return friendListFragment;
            case 2:
                return calendarListFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Clans";
            case 1:
                return "Friends";
            case 2:
                return "Calendar";
            default:
                return null;
        }
    }

}
