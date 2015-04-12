package dk.nicolajpedersen.raidaid.Activities;

import android.app.Activity;
import android.os.Bundle;

import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 13-03-2015.
 */
public class PageViewActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);


    }
}
