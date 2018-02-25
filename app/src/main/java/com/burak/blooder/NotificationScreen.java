package com.burak.blooder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class NotificationScreen extends AppCompatActivity{
    private ArrayList<String> mAnnounces = new ArrayList<>();
    private FirebaseAuth mAuth;

    double enlem;
    double boylam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_screen);


        final ListView mListView = (ListView) findViewById(R.id.listView);
        final ImageButton refershButton = (ImageButton) findViewById(R.id.refreshButton);


        mAuth = FirebaseAuth.getInstance();


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mAnnounces);
        mListView.setAdapter(arrayAdapter);

        /***------------------------------------------------------- TO FIND MY BLOOD GROUP ---------------------------------------------------------**/
        final String[] mine = new String[1];


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
                    mine[0] = me.bloodGroup;
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

//------------------------------------------------------------------------------- END --------------------------------------------------------------

        final DatabaseReference oku = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference oku2 = oku.child("announces");

        oku2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Announces test = new Announces();
                for (DataSnapshot mDataSnapshot : dataSnapshot.getChildren()) {

                    test = dataSnapshot.getValue(Announces.class);
                }


                if (test.requestedBlood.equals(mine[0])) {


                    mAnnounces.add(test.requestedBlood + "         " + test.description+ ","+ test.requesterLocX +"-"+ test.requesterLocY);

                    enlem = Double.valueOf(test.requesterLocX);
                    boylam = Double.valueOf(test.requesterLocY);
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


        refershButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                finish();
                startActivity(intent);


            }


        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);


                String name = parent.getItemAtPosition(position).toString();
                String veri[]=name.split(",");
                String yol[]=veri[1].split("-");
                Toast.makeText(getApplicationContext(),"enlem:"+yol[0]+"boylam:"+yol[1], Toast.LENGTH_SHORT).show();
                intent.putExtra("enlem_verisi", yol[0]);
                intent.putExtra("boylam_verisi", yol[1]);
                intent.putExtra("duyuru",veri[0]);
                startActivity(intent);


            }
        });


    }


}



