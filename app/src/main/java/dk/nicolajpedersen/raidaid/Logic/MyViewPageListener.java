package dk.nicolajpedersen.raidaid.Logic;

import android.support.v4.view.ViewPager;

/**
 * Created by Nicolaj on 14-05-2015.
 */
public class MyViewPageListener extends ViewPager.SimpleOnPageChangeListener {
    private int currentePage;

    @Override
    public void onPageSelected(int position) {
        currentePage = position;
        super.onPageSelected(position);
    }

    public int getCurrentePage(){
        return currentePage;
    }
}
