package dk.nicolajpedersen.raidaid.Logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import dk.nicolajpedersen.raidaid.Data.Appointment;
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


        return rowView;

    }
}
