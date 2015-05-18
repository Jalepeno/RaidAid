package dk.nicolajpedersen.raidaid.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;

import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 17-05-2015.
 */
public class RaidAidLoginSplash extends Activity{

    private final long SPLASH_DISPLAY_LENGTH = 2500;

    int success;
    String username,password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_login);
        ProgressBar pgBar = (ProgressBar) findViewById(R.id.splash_pgbar);
        pgBar.getIndeterminateDrawable().setColorFilter(0xFFFF9800,
                android.graphics.PorterDuff.Mode.SRC_IN);
        pgBar.setVisibility(View.VISIBLE);
        System.out.println("ContentView has been set!");
        success =0;


        Bundle loginCredentials = getIntent().getExtras();
        username = loginCredentials.getString("Username");
        password = loginCredentials.getString("Password");
        System.out.println("login username is: "+username);
        System.out.println("login password is: " + password);
//        success = HTTPLogic.getProfileByLogin(username,password,this);
        success = HTTPLogic.getDummyProfile(username,password,this);

        IntentLauncher launcher = new IntentLauncher();
        launcher.start();

    }

    private void loginSuccess(){
        Intent mainIntent = new Intent(getApplicationContext(),PageViewActivity.class);
        startActivity(mainIntent);
        finish();
    }

    private void loginFail(String username,String password){
        Intent mainIntent = new Intent(getApplicationContext(),StartupActivity.class);
        mainIntent.putExtra("Username", username);
        mainIntent.putExtra("Password",password);
        RaidAidLoginSplash.this.startActivity(mainIntent);

        finish();
    }

    private class IntentLauncher extends Thread {
        public void run() {
            try {
                // Sleeping
                Thread.sleep(SPLASH_DISPLAY_LENGTH);
            } catch (Exception e) {
                Log.e("TAG", e.getMessage());
            }
            // Start main activity
            if(success==1){
                loginSuccess();
            }else{
                loginFail(username,password);
            }
        }
    }

}
