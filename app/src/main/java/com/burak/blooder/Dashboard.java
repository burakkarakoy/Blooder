package com.burak.blooder;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Dashboard extends AppCompatActivity {
    private String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final int REQUEST_CODE_PERMISSION = 2;
    private GPSTracker gps;
    private float[] uzaklikSonuc = new float[1];
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference myDbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        final Button meButton = (Button) findViewById(R.id.meButton);
        final TextView dash_iNeedText = (TextView) findViewById(R.id.iNeedText);
        final Spinner dash_spinner = (Spinner) findViewById(R.id.meSpinner);
        final EditText dash_description = (EditText) findViewById(R.id.email);
        final Button dash_announceButton = (Button) findViewById(R.id.announceButton);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        myDbRef = mDatabase.getReference();






        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.kan_gruplari, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dash_spinner.setAdapter(adapter);


        meButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, MyProfile.class));
            }
        });


        dash_announceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Instantiate a Date object
                Date date = new Date();

                // display time and date
                String str = String.format("%tc", date);

                final String meSpinner = dash_spinner.getSelectedItem().toString();
                final String description = dash_description.getText().toString();

                Announces announce = new Announces();

                announce.setRequesterId(mAuth.getCurrentUser().getUid());
                announce.setRequestedBlood(meSpinner);
                announce.setDescription(description);
                announce.setDate(str);
                konumBul(announce);

                myDbRef.child("announces").push().setValue(announce);
                Toast.makeText(getApplicationContext(), "Duyurunuz iletildi", Toast.LENGTH_LONG).show();

                dash_description.setText("");




                //todo          READ FROM DB CODE STARTS HERE//





            }
        });




    }



    public void konumBul(Announces announce) {
        //GPS Konum bulma

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        gps = new GPSTracker(Dashboard.this);

        if (gps.canGetLocation()) {


            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            announce.setRequesterLocX(latitude);
            announce.setRequesterLocY(longitude);

        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

    }//konumBul ends

    /** for exit from app when press back button **/

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);

    }


}
