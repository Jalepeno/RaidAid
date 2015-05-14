package dk.nicolajpedersen.raidaid.Activities;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonRectangle;

import dk.nicolajpedersen.raidaid.Data.Clan;
import dk.nicolajpedersen.raidaid.Data.Membership;
import dk.nicolajpedersen.raidaid.Data.Profile;
import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;
import dk.nicolajpedersen.raidaid.Logic.ShoutArrayAdapter;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 08-04-2015.
 */
public class ClanPageActivity extends ActionBarActivity implements View.OnClickListener{

    private ListView lvShout;
    private TextView tvMessageOfDay, tvClanName, tvMemberCloud;
    private ButtonRectangle btnShout;
    private ButtonFloat btnInvite;
    private ImageView clanGameImg;
    private  LinearLayout clanBanner;
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


        clan = Profile.myClans.get(getIntent().getIntExtra("clanNr",0));

        //setting up message of the day
        tvMessageOfDay = (TextView) findViewById(R.id.tvMessageOfDay);
        tvMessageOfDay.setText(clan.getWelcomeMessage());

        //setting up banner
        clanBanner = (LinearLayout) findViewById(R.id.clanBanner);
        tvClanName = (TextView) findViewById(R.id.clanName);
        tvClanName.setText(clan.getClanName());
        clanGameImg = (ImageView) findViewById(R.id.clanGameImage);


        //filling shout wall
        lvShout = (ListView) findViewById(R.id.listviewShoutbox);
        sAA = new ShoutArrayAdapter(this.getApplicationContext(),clan.getShouts());
        lvShout.setAdapter(sAA);

        tvMemberCloud = (TextView) findViewById(R.id.tvClanMembers);
        tvMemberCloud.setText(Html.fromHtml(fillCloud()));

        // assigning buttons and textboxes.
        btnInvite = (ButtonFloat) findViewById(R.id.buttonFloatClanPage);
        btnInvite.setBackgroundColor(Color.parseColor("#FF9800"));
        btnShout = (ButtonRectangle) findViewById(R.id.btnShout);
        btnShout.setBackgroundColor(Color.parseColor("#FF9800"));
        etShout =(EditText) findViewById(R.id.etShoutText);
        etShout.setFocusable(false);
        btnShout.setOnClickListener(this);
        btnInvite.setOnClickListener(this);

        Drawable d;

        switch (clan.getGame()){
            case COUNTERSTRIKE:
                clanBanner.setBackgroundResource(R.drawable.background_cs); //R.drawable. - some couterstrike image
                clanGameImg.setImageResource(R.drawable.logo_cs);
                d = getResources().getDrawable(R.drawable.cs_rally);
                btnInvite.setDrawableIcon(d);
                break;
            case DIABLO:
                clanBanner.setBackgroundResource(R.drawable.background_diablo); //R.drawable. - some diablo image
                clanGameImg.setImageResource(R.drawable.logo_diablo);
                d = getResources().getDrawable(R.drawable.wow_rally);
                btnInvite.setDrawableIcon(d);
                break;
            case DOTA2:
                clanBanner.setBackgroundResource(R.drawable.background_dota); //R.drawable. - some dota2 image
                clanGameImg.setImageResource(R.drawable.logo_dota);
                d = getResources().getDrawable(R.drawable.dota_rally);
                btnInvite.setDrawableIcon(d);
                break;
            case LEAGUEOFLEGENDS:
                clanBanner.setBackgroundResource(R.drawable.background_lol); //R.drawable. - some lol image
                clanGameImg.setImageResource(R.drawable.logo_lol);
                d = getResources().getDrawable(R.drawable.lol_rally);
                btnInvite.setDrawableIcon(d);
                break;
            case STARCRAFT:
                clanBanner.setBackgroundResource(R.drawable.background_sc); //R.drawable. - some starcraft image
                clanGameImg.setImageResource(R.drawable.logo_starcraft);
                d = getResources().getDrawable(R.drawable.sc_rally);
                btnInvite.setDrawableIcon(d);
                break;
            case WORLDOFWARCRAFTALLIANCE:
                clanBanner.setBackgroundResource(R.drawable.background_alliance); //R.drawable. - some wow alliance image
                clanGameImg.setImageResource(R.drawable.logo_wow);
                d = getResources().getDrawable(R.drawable.wow_rally);
                btnInvite.setDrawableIcon(d);
                break;
            case WORLDOFWARCRAFTHORDE:
                clanBanner.setBackgroundResource(R.drawable.background_horde); //R.drawable. - some wow horde image
                clanGameImg.setImageResource(R.drawable.logo_wow);
                d = getResources().getDrawable(R.drawable.wow_rally);
                btnInvite.setDrawableIcon(d);
                break;
            case HEROESOFTHESTORM:
                clanBanner.setBackgroundResource(R.drawable.background_hots); //R.drawable. - some hots image
                clanGameImg.setImageResource(R.drawable.logo_hots);
                d = getResources().getDrawable(R.drawable.hots_rally);
                btnInvite.setDrawableIcon(d);
                break;
            default:
                clanBanner.setBackgroundColor(Color.DKGRAY);
                d = getResources().getDrawable(R.drawable.hots_rally);
                btnInvite.setDrawableIcon(d);
                break;
        }

    }

    private String fillCloud() {
        String CharString = "";
        for(Membership m : clan.getMembers()){
            CharString = CharString+", ";
            if(m.getRank() == 6){
                CharString = CharString+"<font color=\"#FF9800\"><bold>"
                        + m.getUserName()
                        + "</bold></font>";
            }else{
                CharString = CharString+m.getUserName();
            }

        }
        CharString = CharString.substring(2);
        return CharString;
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
