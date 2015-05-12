package dk.nicolajpedersen.raidaid.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;

import dk.nicolajpedersen.raidaid.Activities.PageViewActivity;
import dk.nicolajpedersen.raidaid.Activities.StartupActivity;
import dk.nicolajpedersen.raidaid.Data.Appointment;
import dk.nicolajpedersen.raidaid.Data.Clan;
import dk.nicolajpedersen.raidaid.Data.Friend;
import dk.nicolajpedersen.raidaid.Data.Profile;
import dk.nicolajpedersen.raidaid.Data.User;
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

        sharedPreferences = getActivity().getSharedPreferences("RaidAidPrefs", Context.MODE_PRIVATE);

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
            Intent i = new Intent(getActivity()
                    .getApplicationContext(), PageViewActivity.class);
            startActivity(i);


            //login session
/*
            HTTPLogic httpLogic = new HTTPLogic();
            int didStuffHappen = httpLogic.getProfileByLogin(username.getText().toString(), password.getText().toString(), getActivity().getApplicationContext());


            // if login successful
            if(didStuffHappen == 1){

                // save login valuables
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",username.getText().toString());
                editor.putString("password",password.getText().toString());
                editor.commit();

                // change activity.
                Intent i = new Intent(getActivity()
                        .getApplicationContext(), PageViewActivity.class);
                startActivity(i);

            }else{

                // make toast to inform of bad login
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Your username and password did not match";
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
            }

*/
        }else if(v == signup){
            ((StartupActivity)getActivity()).setSignupFragment();
        }
    }
}
