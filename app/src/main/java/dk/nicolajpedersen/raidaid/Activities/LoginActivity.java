package dk.nicolajpedersen.raidaid.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dk.nicolajpedersen.raidaid.R;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {
    Button btn_google,btn_facebook,btn_new;
    EditText et_newEmail,et_newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_google = (Button) findViewById(R.id.btn_google_signup);
        btn_google.setOnClickListener(this);
        btn_facebook = (Button) findViewById(R.id.btn_facebook_signup);
        btn_facebook.setOnClickListener(this);
        btn_new = (Button) findViewById(R.id.btn_new_signup);
        btn_new.setOnClickListener(this);
        et_newEmail = (EditText) findViewById(R.id.et_new_email);
        et_newPassword = (EditText) findViewById(R.id.et_new_password);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
    // login stuff will happen here
        if(v == btn_google){

        }
        if(v == btn_facebook){

        }
        if(v == btn_new){
        //    setupNewAccount();
        }

        // save profile in shared preferences.
        //  saveProfile();
        logIn();
    }

    private void logIn() {
        Intent i = new Intent(this,PageViewActivity.class);
        this.finish();
        startActivity(i);
    }

    private void saveProfile() {
        SharedPreferences userDetails = this.getSharedPreferences("userdetails", MODE_PRIVATE);
        SharedPreferences.Editor edit = userDetails.edit();
        edit.clear();

        //put profile info here
    }

    private void setupNewAccount() {
        // setting up new account here using email and password.
        //et_newEmail.getText();
        //et_newPassword.getText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
