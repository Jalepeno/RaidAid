package dk.nicolajpedersen.raidaid.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 02-04-2015.
 */
public class TempLoginActivity extends Activity implements View.OnClickListener{
    Button login;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_login);
        login =(Button) findViewById(R.id.btnTmpLogin);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == login){
            Intent i = new Intent(this,PageViewActivity.class);
            startActivity(i);
        }

    }
}
