package dk.nicolajpedersen.raidaid.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;

import dk.nicolajpedersen.raidaid.Fragments.LoginFragment;
import dk.nicolajpedersen.raidaid.Fragments.SignupFragment;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 14-04-2015.
 */

public class StartupActivity extends FragmentActivity{
    LoginFragment loginFragment;
    SignupFragment signupFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);
        setLoginFragment();


    }

    public void setSignupFragment() {
        signupFragment = new SignupFragment();
        getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .add(R.id.frameLayout1, signupFragment).commit();
    }

    public void setLoginFragment(){
        loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout1,loginFragment).commit();
    }

}
