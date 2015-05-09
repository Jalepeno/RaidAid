package dk.nicolajpedersen.raidaid.Activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

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
public class PageViewActivity extends FragmentActivity{

    private RAPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    String[] tabs ={"Clans","Friends","Calendar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Bind the tabs to the ViewPager





        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_TABS);
        setContentView(R.layout.activity_viewpager);
        listPaging();

        // setting up tablistener

        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

            @Override
            public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

            }
        };

    /*   // setting up tabs
        for(String s : tabs){
            actionBar.addTab(actionBar.newTab().setText(s).setTabListener(tabListener));
        }
    */
    }

    private void listPaging(){
        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this,ClanListFragment.class.getName()));
        fragments.add(Fragment.instantiate(this,FriendListFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, CalendarListFragment.class.getName()));
        pagerAdapter= new RAPagerAdapter(this.getSupportFragmentManager(),fragments);

        viewPager = (ViewPager) findViewById(R.id.pager);

        // making sure the current tab is marked.
        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });
        viewPager.setAdapter(pagerAdapter);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(viewPager);



    }

}
