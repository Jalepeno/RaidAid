package dk.nicolajpedersen.raidaid.Activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.astuetz.PagerSlidingTabStrip;

import java.util.List;
import java.util.Vector;

import dk.nicolajpedersen.raidaid.Logic.RAPagerAdapter;
import dk.nicolajpedersen.raidaid.R;
import dk.nicolajpedersen.raidaid.ViewpageFragments.CalendarListFragment;
import dk.nicolajpedersen.raidaid.ViewpageFragments.ClanListFragment;
import dk.nicolajpedersen.raidaid.ViewpageFragments.FriendListFragment;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class PageViewActivity extends ActionBarActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);



        // Initialize the ViewPager and set an adapter
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new RAPagerAdapter(getSupportFragmentManager()));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

    }

}
