package dk.nicolajpedersen.raidaid.Activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.astuetz.PagerSlidingTabStrip;

import dk.nicolajpedersen.raidaid.Logic.RAPagerAdapter;
import dk.nicolajpedersen.raidaid.Logic.MyViewPageListener;
import dk.nicolajpedersen.raidaid.R;


/**
 * Created by Nicolaj on 13-03-2015.
 */
public class PageViewActivity extends ActionBarActivity{
    private MyViewPageListener vpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_viewPager);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        // Initialize the ViewPager and set an adapter
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new RAPagerAdapter(getSupportFragmentManager()));
        vpl = new MyViewPageListener();


        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        tabs.setOnPageChangeListener(vpl);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

                getMenuInflater().inflate(R.menu.menu_mainview, menu);
/*
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
*/
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        switch (vpl.getCurrentePage()){
            case 0:
                menu.findItem(R.id.menu_newClan).setVisible(true);
                menu.findItem(R.id.menu_findClan).setVisible(true);
                menu.findItem(R.id.menu_checkInvitesToClan).setVisible(true);
                menu.findItem(R.id.menu_leaveClan).setVisible(true);

                menu.findItem(R.id.menu_addFriend).setVisible(false);
                menu.findItem(R.id.menu_pendingFriends).setVisible(false);
                menu.findItem(R.id.menu_removeFriend).setVisible(false);

                break;
            case 1:
                menu.findItem(R.id.menu_newClan).setVisible(false);
                menu.findItem(R.id.menu_findClan).setVisible(false);
                menu.findItem(R.id.menu_checkInvitesToClan).setVisible(false);
                menu.findItem(R.id.menu_leaveClan).setVisible(false);


                menu.findItem(R.id.menu_addFriend).setVisible(true);
                menu.findItem(R.id.menu_pendingFriends).setVisible(true);
                menu.findItem(R.id.menu_removeFriend).setVisible(true);
                break;
            default:
                menu.findItem(R.id.menu_newClan).setVisible(false);
                menu.findItem(R.id.menu_findClan).setVisible(false);
                menu.findItem(R.id.menu_checkInvitesToClan).setVisible(false);
                menu.findItem(R.id.menu_leaveClan).setVisible(false);

                menu.findItem(R.id.menu_addFriend).setVisible(false);
                menu.findItem(R.id.menu_pendingFriends).setVisible(false);
                menu.findItem(R.id.menu_removeFriend).setVisible(false);
                break;
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
