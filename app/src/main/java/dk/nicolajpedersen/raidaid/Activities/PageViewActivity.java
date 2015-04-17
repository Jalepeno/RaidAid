package dk.nicolajpedersen.raidaid.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import dk.nicolajpedersen.raidaid.R;
import dk.nicolajpedersen.raidaid.ViewpageFragments.ClanListFragment;
import dk.nicolajpedersen.raidaid.ViewpageFragments.ProfileFragment;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class PageViewActivity extends Activity{
    ViewPager viewPager;
    FrameLayout frameLayout;
    ClanListFragment clanList;
    ProfileFragment profileAppoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        viewPager = (ViewPager) findViewById(R.id.pager);
        frameLayout = (FrameLayout) findViewById(R.id.viewPageFrame);
        clanList = new ClanListFragment();
        profileAppoint = new ProfileFragment();



    }
}
