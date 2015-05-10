package dk.nicolajpedersen.raidaid.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;

//import com.splunk.mint.Mint;

import dk.nicolajpedersen.raidaid.Fragments.LoginFragment;
import dk.nicolajpedersen.raidaid.Fragments.SignupFragment;
import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 14-04-2015.
 */

public class StartupActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);

//       Mint.initAndStartSession(StartupActivity.this, "f309539b");

        if(savedInstanceState == null){
            setLoginFragment();

            SharedPreferences prefs = getSharedPreferences("RaidAid", MODE_PRIVATE);
            String thisUser = prefs.getString("username", "");
            String thisPassword = prefs.getString("password","");
            if(!thisUser.equalsIgnoreCase("")&&!thisPassword.equalsIgnoreCase("")){
                loginSequence(thisUser,thisPassword);
            }
        }


    }

    private void loginSequence(String thisUser, String thisPassword) {
    }

    public void setSignupFragment() {
        SignupFragment signupFragment = new SignupFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout1, signupFragment).addToBackStack(null).commit();
    }

    public void setLoginFragment(){
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout1, loginFragment).addToBackStack(null).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
