package dk.nicolajpedersen.raidaid.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 14-04-2015.
 */
public class StartupActivity extends Activity{
    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);
        frame = (FrameLayout) findViewById(R.id.frameLayout1);
    }

}
