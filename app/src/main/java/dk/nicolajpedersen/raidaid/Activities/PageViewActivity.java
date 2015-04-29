package dk.nicolajpedersen.raidaid.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import java.util.List;
import java.util.Vector;

import dk.nicolajpedersen.raidaid.Logic.PagerAdapter;
import dk.nicolajpedersen.raidaid.R;
import dk.nicolajpedersen.raidaid.ViewpageFragments.ClanListFragment;
import dk.nicolajpedersen.raidaid.ViewpageFragments.ProfileFragment;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class PageViewActivity extends FragmentActivity{
;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        listPaging();
    }

    private void listPaging(){
        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this,ClanListFragment.class.getName()));
        fragments.add(Fragment.instantiate(this,ProfileFragment.class.getName()));
        pagerAdapter= new PagerAdapter(this.getSupportFragmentManager(),fragments);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

    }
}
