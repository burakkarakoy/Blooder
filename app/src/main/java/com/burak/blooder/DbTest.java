package com.burak.blooder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DbTest extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);

        final TextView db_description = (TextView) findViewById(R.id.email);

        final Button db_getir = (Button) findViewById(R.id.getir);
        mAuth = FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance();
        mDbRef=mDatabase.getReference();




        db_getir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final DatabaseReference oku = FirebaseDatabase.getInstance().getReference().child("ANNOUNCE").child("-KizAn8SV_f9nFiKVvw9");

                ValueEventListener dinle = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Announces k=new Announces();
                        k=dataSnapshot.getValue(Announces.class);
                        db_description.setText(k.requestedBlood +"      "+ k.description);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };

                oku.addValueEventListener(dinle);




            }
        });


    }


}

