package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dk.nicolajpedersen.raidaid.Data.Appointment;
import dk.nicolajpedersen.raidaid.Data.Friend;
import dk.nicolajpedersen.raidaid.Data.Game;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 14-04-2015.
 */
public class AppointmentArrayAdapter extends ArrayAdapter<Appointment> {
    private final Context context;
    private final ArrayList<Appointment> appointments;
    TextView tvMembersReady;

    public AppointmentArrayAdapter(Context context, ArrayList<Appointment> appointments) {
        super(context, R.layout.list_element_appointment_parent, appointments);
        this.context = context;
        this.appointments = appointments;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.list_element_appointment_parent, parent, false);


        final Appointment thisAppointment = appointments.get(position);


        // Overview Part

        // assigning views
        TextView headline =(TextView) rowView.findViewById(R.id.tvAppointName);
        ImageView appointGame = (ImageView) rowView.findViewById(R.id.imgAppointGame);
        TextView clanName = (TextView) rowView.findViewById(R.id.tvAppointClan);
        LinearLayout background = (LinearLayout) rowView.findViewById(R.id.llAppointBanner);
        TextView tvAppointDate = (TextView) rowView.findViewById(R.id.tvAppointDate);
        TextView tvAppointTime = (TextView) rowView.findViewById(R.id.tvAppointTimeofDay);

        // set values
        headline.setText(thisAppointment.getHeadline());
        clanName.setText(thisAppointment.getClan().getClanName());
        Game thisAppointGame = thisAppointment.getClan().getGame();

        tvAppointDate.setText(thisAppointment.getDateString());
        tvAppointTime.setText(thisAppointment.getTimeSring());
        switch (thisAppointGame){
            case COUNTERSTRIKE:
                background.setBackgroundResource(R.drawable.background_cs); //R.drawable. - some couterstringe image
                appointGame.setImageResource(R.drawable.logo_cs);
                break;
            case DIABLO:
                background.setBackgroundResource(R.drawable.background_diablo); //R.drawable. - some diablo image
                appointGame.setImageResource(R.drawable.logo_diablo);
                break;
            case DOTA2:
                background.setBackgroundResource(R.drawable.background_dota); //R.drawable. - some dota2 image
                appointGame.setImageResource(R.drawable.logo_dota);
                break;
            case LEAGUEOFLEGENDS:
                background.setBackgroundResource(R.drawable.background_lol); //R.drawable. - some lol image
                appointGame.setImageResource(R.drawable.logo_lol);
                break;
            case STARCRAFT:
                background.setBackgroundResource(R.drawable.background_sc); //R.drawable. - some starcraft image
                appointGame.setImageResource(R.drawable.logo_starcraft);
                break;
            case WORLDOFWARCRAFTALLIANCE:
                background.setBackgroundResource(R.drawable.background_alliance); //R.drawable. - some wow alliance image
                appointGame.setImageResource(R.drawable.logo_wow);
                break;
            case WORLDOFWARCRAFTHORDE:
                background.setBackgroundResource(R.drawable.background_horde); //R.drawable. - some wow horde image
                appointGame.setImageResource(R.drawable.logo_wow);
                break;
            case HEROESOFTHESTORM:
                background.setBackgroundResource(R.drawable.background_hots); //R.drawable. - some wow horde image
                appointGame.setImageResource(R.drawable.logo_hots);
                break;
            default:
                background.setBackgroundColor(Color.DKGRAY);
                break;
        }

        // detail part

        final LinearLayout detail = (LinearLayout) rowView.findViewById(R.id.llAppointDetail);
        detail.setVisibility((thisAppointment.isExpanded()) ? View.VISIBLE : View.GONE);

        TextView tvDescription = (TextView) rowView.findViewById(R.id.tvAppointDescription);
        tvDescription.setText(thisAppointment.getDescription());

        tvMembersReady = (TextView) rowView.findViewById(R.id.tvAppointMembersReady);
        tvMembersReady.setText("Members Ready: " + thisAppointment.getMembersReady() + "/" + thisAppointment.getInviteList().size());

        final CheckBox imReady = (CheckBox) rowView.findViewById(R.id.cbAppointReady);
        imReady.setChecked(thisAppointment.isAmIReady());
        imReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == imReady) {
                    thisAppointment.setAmIReady(imReady.isChecked());
                    thisAppointment.getMe().setIsInvited(thisAppointment.isAmIReady());
                    notifyDataSetChanged();
                }
            }
        });


        // adding expantion animation
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == rowView){
                    if(thisAppointment.isExpanded()){
                        CalendarAnimation.collapse(detail);
                    }else{
                        CalendarAnimation.expand(detail);
                    }
                    thisAppointment.setExpanded(!thisAppointment.isExpanded());
                }
            }
        });


        return rowView;

    }


}
