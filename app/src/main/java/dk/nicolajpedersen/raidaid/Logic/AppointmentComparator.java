package dk.nicolajpedersen.raidaid.Logic;

import java.util.Comparator;

import dk.nicolajpedersen.raidaid.Data.Appointment;

/**
 * Created by Nicolaj on 09-05-2015.
 */
public class AppointmentComparator implements Comparator<Appointment> {
    @Override
    public int compare(Appointment lhs, Appointment rhs) {
        return lhs.getDate().compareTo(rhs.getDate());
    }
}
