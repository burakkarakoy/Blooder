package com.burak.blooder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfile extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);


        final TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        final TextView myProfile_name = (TextView) findViewById(R.id.myProfileName);

        final Button logOutButton = (Button) findViewById(R.id.logOutButton);
        final Button my_notifications = (Button) findViewById(R.id.notificationsButton);

        mAuth = FirebaseAuth.getInstance();


       // myProfile_name.setText(mAuth.getCurrentUser().getEmail());

        my_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MyProfile.this, NotificationScreen.class);
                MyProfile.this.startActivity(ıntent);
            }
        });


// TODO GET CURRENT USER ID Sİ REQUESTERİD YE ESİT OLARAK QUERY YAZILACAK.


        final DatabaseReference dinle = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference dinle2 = dinle.child("users");
        dinle2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Users me = new Users();
                for (DataSnapshot mDataSnapshot : dataSnapshot.getChildren()) {

                    me = dataSnapshot.getValue(Users.class);
                }
                if (me.userId.equals(mAuth.getCurrentUser().getUid())) {
                    myProfile_name.setText(me.name.toUpperCase() + " " + me.surname.toUpperCase() + " " + "(" + me.bloodGroup + ")");
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//-----------------------------------------------------------------------------------------------------------------


        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logOutUser();


            }
        });
    }

    private void logOutUser() {

        mAuth.getInstance().signOut();

        if (mAuth.getCurrentUser() == null) {

            Toast.makeText(getApplicationContext(), "Çıkış Başarılı", Toast.LENGTH_LONG).show();
            //TODO LOGOUT OLDUKTAN SONRA GERİ TUŞUNA BASINCA TEKRAR LOGİN GİBİ GÖRÜNMEMESİ İÇİN.

            Intent intent = new Intent(MyProfile.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


        }


    }
}
