package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import dk.nicolajpedersen.raidaid.Data.Appointment;
import dk.nicolajpedersen.raidaid.Data.Game;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 14-04-2015.
 */
public class AppontmentArrayAdapter extends ArrayAdapter<Appointment> {
    private final Context context;
    private final ArrayList<Appointment> appointments;

    public AppontmentArrayAdapter(Context context, ArrayList<Appointment> appointments) {
        super(context, R.layout.list_element_appointment, appointments);
        this.context = context;
        this.appointments = appointments;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_element_appointment, parent, false);
        Appointment thisAppointment = appointments.get(position);
        TextView headline =(TextView) rowView.findViewById(R.id.tvAppointName);
        headline.setText(thisAppointment.getHeadline());
        TextView clanName = (TextView) rowView.findViewById(R.id.tvAppointClan);
        TextView rdyMembers = (TextView) rowView.findViewById(R.id.tvAppointMembersReady);
        clanName.setText(thisAppointment.getClan().getClanName());
        Game thisAppointGame = thisAppointment.getClan().getGame();
        LinearLayout background = (LinearLayout) rowView.findViewById(R.id.outerLayout);
        switch (thisAppointGame){
            case COUNTERSTRIKE:
                background.setBackgroundResource(R.drawable.background_cs); //R.drawable. - some couterstringe image
                break;
            case DIABLO:
                background.setBackgroundResource(R.drawable.background_diablo); //R.drawable. - some diablo image
                break;
            case DOTA2:
                background.setBackgroundResource(R.drawable.background_dota); //R.drawable. - some dota2 image
                break;
            case LEAGUEOFLEGENDS:
                background.setBackgroundResource(R.drawable.background_lol); //R.drawable. - some lol image
                break;
            case STARCRAFT:
                background.setBackgroundResource(R.drawable.background_sc); //R.drawable. - some starcraft image
                break;
            case WORLDOFWARCRAFTALLIANCE:
                background.setBackgroundResource(R.drawable.background_alliance); //R.drawable. - some wow alliance image
                break;
            case WORLDOFWARCRAFTHORDE:
                background.setBackgroundResource(R.drawable.background_horde); //R.drawable. - some wow horde image
                break;
            case HEROESOFTHESTORM:
                background.setBackgroundResource(R.drawable.background_hots); //R.drawable. - some wow horde image
                break;
            default:
                background.setBackgroundColor(Color.DKGRAY);
                break;
        }

        return rowView;

    }
}
