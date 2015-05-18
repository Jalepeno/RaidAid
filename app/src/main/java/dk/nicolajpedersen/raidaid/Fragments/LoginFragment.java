package dk.nicolajpedersen.raidaid.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gc.materialdesign.views.ButtonFlat;

import java.util.ArrayList;

import dk.nicolajpedersen.raidaid.Activities.PageViewActivity;
import dk.nicolajpedersen.raidaid.Activities.RaidAidLoginSplash;
import dk.nicolajpedersen.raidaid.Activities.StartupActivity;
import dk.nicolajpedersen.raidaid.Data.Appointment;
import dk.nicolajpedersen.raidaid.Data.Clan;
import dk.nicolajpedersen.raidaid.Data.Friend;
import dk.nicolajpedersen.raidaid.Data.Profile;
import dk.nicolajpedersen.raidaid.Logic.HTTPLogic;
import dk.nicolajpedersen.raidaid.R;

/**
 * Created by Nicolaj on 14-04-2015.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{
    EditText username,password;
    ButtonFlat signup;
    ButtonFlat login;
    View loginView;
    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginView = inflater.inflate(R.layout.fragment_login,container,false);

        username =(EditText) loginView.findViewById(R.id.etUserName);
        password =(EditText) loginView.findViewById(R.id.etpassword);

        signup = (ButtonFlat) loginView.findViewById(R.id.btnSignup);
        signup.setBackgroundColor(Color.WHITE);
        login = (ButtonFlat) loginView.findViewById(R.id.btn_login);
        login.setBackgroundColor(Color.parseColor("#FF9800"));

        signup.setOnClickListener(this);
        login.setOnClickListener(this);

        return loginView;
    }

    @Override
    public void onClick(View v) {
        if(v == login) {
            if (Profile.myAppointments == null) {
                Profile.myAppointments = new ArrayList<Appointment>();
                Profile.myClans = new ArrayList<Clan>();
                Profile.myFriends = new ArrayList<Friend>();
            }
            HTTPLogic httpLogic = new HTTPLogic();

            httpLogic.getDummyProfile(username.getText().toString(), password.getText().toString(), getActivity());

            getActivity().finish();



//            Intent i = new Intent(getActivity().getApplicationContext(), PageViewActivity.class);
//            startActivity(i);

            // login splash sequence,
            Intent i = new Intent(getActivity().getApplicationContext(), RaidAidLoginSplash.class);
            Bundle b = new Bundle();
            b.putString("Username", username.getText().toString());
            b.putString("Password", password.getText().toString());
            i.putExtras(b);
            startActivity(i);


        }else if(v == signup){
            ((StartupActivity)getActivity()).setSignupFragment();
        }
    }
}
