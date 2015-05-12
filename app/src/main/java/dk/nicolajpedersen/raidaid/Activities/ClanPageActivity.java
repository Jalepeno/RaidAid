package dk.nicolajpedersen.raidaid.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import dk.nicolajpedersen.raidaid.Data.Clan;
import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;
import dk.nicolajpedersen.raidaid.Logic.ShoutArrayAdapter;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 08-04-2015.
 */
public class ClanPageActivity extends ActionBarActivity implements View.OnClickListener{
    private ListView lvShout;
    private TextView messageOfDay;
    private Button btnShout,btnInvite;
    private Clan clan;
    private EditText etShout;
    private HTTPLogic httpLogic;
    private ShoutArrayAdapter sAA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clan_page);
        httpLogic = new HTTPLogic();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_clan);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }


        try{
            clan = (Clan) getIntent().getSerializableExtra("MyClan");

            //setting up message of the day
            messageOfDay = (TextView) findViewById(R.id.tvMessageOfDay);
            messageOfDay.setText(clan.getWelcomeMessage());

            //filling shout wall
            lvShout = (ListView) findViewById(R.id.listviewShoutbox);
            sAA = new ShoutArrayAdapter(this.getApplicationContext(),clan.getShouts());
            lvShout.setAdapter(sAA);

            // assigning buttons and textboxes.
            btnInvite = (Button) findViewById(R.id.buttonFloatClanPage);
            btnShout = (Button) findViewById(R.id.btnShout);
            etShout =(EditText) findViewById(R.id.etShoutText);
            btnShout.setOnClickListener(this);
            btnInvite.setOnClickListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {
            case R.id.invitePlayer:
                invitePlayer();
                break;
            case R.id.PromotePlayer:
                startPromotePlayer();
                break;
            case R.id.menu_msgOfDay:
                getMsgOfDayDialog();
                break;
            case R.id.menu_AppointMan:
                getAppointmentManager();
                break;
            case R.id.menu_kickPlayer:
                kickPlayer();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAppointmentManager() {

    }

    private void getMsgOfDayDialog() {

    }

    private void startPromotePlayer() {

    }

    private void kickPlayer() {

    }

    private void invitePlayer() {

    }


    @Override
    public void onClick(View v) {
        if(v == btnInvite){
            // Open dialog box for invite text
        }else if(v == btnShout){
            httpLogic.sendShout(clan.getClanID(),etShout.getText().toString());
        }

    }
}
