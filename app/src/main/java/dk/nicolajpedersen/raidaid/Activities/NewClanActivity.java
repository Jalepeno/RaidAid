package dk.nicolajpedersen.raidaid.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dk.nicolajpedersen.raidaid.Data.Game;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 08-04-2015.
 */
public class NewClanActivity extends Activity implements View.OnClickListener{
    Button btnCreate,btnAddToInvite;
    EditText etNewClanName,etAddToInvite;
    TextView tvListOfInvited;
    Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_clan);
    }

    @Override
    public void onClick(View v) {

    }
}
