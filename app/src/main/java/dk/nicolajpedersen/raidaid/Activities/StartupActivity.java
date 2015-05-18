package dk.nicolajpedersen.raidaid.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

//import com.splunk.mint.Mint;

import java.util.logging.Handler;

import dk.nicolajpedersen.raidaid.Fragments.LoginFragment;
import dk.nicolajpedersen.raidaid.Fragments.SignupFragment;
import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 14-04-2015.
 */

public class StartupActivity extends FragmentActivity {

    FrameLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);
        layout = (FrameLayout) findViewById(R.id.frameLayout1);

//       Mint.initAndStartSession(StartupActivity.this, "f309539b");

        if (savedInstanceState == null) {


            SharedPreferences prefs = getSharedPreferences("RaidAid", MODE_PRIVATE);
            String thisUser = prefs.getString("Username", "");
            String thisPassword = prefs.getString("Password", "");
            if (!thisUser.equalsIgnoreCase("") && !thisPassword.equalsIgnoreCase("")) {
                loginSequence(thisUser, thisPassword);
            }
        }
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(layout.getId(), loginFragment).commit();

    }

    private void loginSequence(String thisUser, String thisPassword) {
    }

    public void setSignupFragment() {
        SignupFragment signupFragment = new SignupFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(layout.getId(), signupFragment).addToBackStack(null).commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}