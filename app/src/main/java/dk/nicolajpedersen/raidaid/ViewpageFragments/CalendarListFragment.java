package dk.nicolajpedersen.raidaid.ViewpageFragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import dk.nicolajpedersen.raidaid.Data.Profile;
import dk.nicolajpedersen.raidaid.Logic.AppointmentArrayAdapter;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 05-05-2015.
 */
public class CalendarListFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendarlist, container, false);
        ListView appointmentList = (ListView) view.findViewById(R.id.lvAppointmentList);
        AppointmentArrayAdapter appointmentAdapter = new AppointmentArrayAdapter(getActivity(), Profile.myAppointments);
        appointmentList.setAdapter(appointmentAdapter);



      return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
