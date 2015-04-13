package dk.nicolajpedersen.raidaid.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import dk.nicolajpedersen.raidaid.Data.Clan;
import dk.nicolajpedersen.raidaid.Data.UserProfile;
import dk.nicolajpedersen.raidaid.Data.WallShout;
import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 08-04-2015.
 */
public class ClanPageActivity extends Activity implements View.OnClickListener{
    private ListView lvShout;
    private TextView messageOfDay;
    private Button btnShout,btnInvite;
    private Clan clan;
    private EditText etShout;
    private HTTPLogic httpLogic;

    public ClanPageActivity(Clan clan){
        this.clan = clan;
        httpLogic = new HTTPLogic();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clan_page);
        lvShout = (ListView) findViewById(R.id.listviewShoutbox);
        btnInvite = (Button) findViewById(R.id.btnInvite);
        btnShout = (Button) findViewById(R.id.btnShout);
        btnShout.setOnClickListener(this);
        btnInvite.setOnClickListener(this);
        messageOfDay = (TextView) findViewById(R.id.tvMessageOfDay);
        messageOfDay.setText(clan.getWelcomeMessage());

    }

    private void FillClanWall(){
        for(WallShout ws :clan.getShouts()){

        }
    }

    @Override
    public void onClick(View v) {
        if(v == btnInvite){
            // Open dialog box for invite text
        }else if(v == btnShout){
            httpLogic.sendShout(clan.getClanID(),etShout.getText());
        }

    }
}
