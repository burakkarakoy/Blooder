package com.burak.blooder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference myDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText reg_mail = (EditText) findViewById(R.id.email);

        final EditText reg_pwd = (EditText) findViewById(R.id.password);

        final EditText reg_name = (EditText) findViewById(R.id.name);

        final EditText reg_surname = (EditText) findViewById(R.id.surname);

        final EditText reg_phoneNumber = (EditText) findViewById(R.id.phoneNumber);

        final Spinner reg_spinner = (Spinner) findViewById(R.id.bloodGroup);

        final Button reg_saveButton = (Button) findViewById(R.id.reg_saveButton);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        myDbRef = mDatabase.getReference();


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.kan_gruplari, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        reg_spinner.setAdapter(adapter);


        reg_saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = reg_mail.getText().toString();
                final String password = reg_pwd.getText().toString();
                final String name = reg_name.getText().toString();
                final String surname = reg_surname.getText().toString();
                final String phoneNumber = reg_phoneNumber.getText().toString();
                final String bloodGroup = reg_spinner.getSelectedItem().toString();


                if (email.length() < 1 || password.length() < 1 || name.length() < 1 || surname.length() < 1 || phoneNumber.length() < 1) {
                    Toast.makeText(getApplicationContext(), "Lütfen boş alan bırakmayınız", Toast.LENGTH_SHORT).show();

                } else {

                    signUpUser(email, password, name, surname, phoneNumber, bloodGroup);


                }


            }

        });
    }

    private void signUpUser(final String email, final String password, final String name, final String surname, final String phoneNumber, final String bloodGroup) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                        } else {

                            Users user = new Users();

                            user.setUserId(mAuth.getCurrentUser().getUid());
                            user.setEmail(mAuth.getCurrentUser().getEmail());
                            user.setPassword(password);
                            user.setName(name);
                            user.setSurname(surname);
                            user.setPhoneNumber(phoneNumber);
                            user.setBloodGroup(bloodGroup);
                            user.setToken(FirebaseInstanceId.getInstance().getToken());


                            myDbRef.child("users").push().setValue(user);
                            Intent intent = new Intent(SignUp.this, MainActivity.class);

                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            startActivity(intent);
                        }
                    }
                });
    }

}





