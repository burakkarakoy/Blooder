package com.burak.blooder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.burak.blooder.R.id.email;
import static com.burak.blooder.R.id.password;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText main_mail = (EditText) findViewById(email);

        final EditText main_password = (EditText) findViewById(password);

        final Button loginButton = (Button) findViewById(R.id.loginButton);

        final TextView forgotPassword = (TextView) findViewById(R.id.forgotPassword);

        final TextView orText = (TextView) findViewById(R.id.orText);

        final TextView singUp = (TextView) findViewById(R.id.signUp);

        final ImageView imageView = (ImageView) findViewById(R.id.icon);


        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null)
            startActivity(new Intent(MainActivity.this, Dashboard.class));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = main_mail.getText().toString();
                String password = main_password.getText().toString();

                if (email.length() < 1 || password.length() < 1) {

                    Toast.makeText(getApplicationContext(), "Please enter e mail and password together", Toast.LENGTH_SHORT).show();

                } else loginUser(email, password);

            }
        });

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUp.class));

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgotPassword.class));
            }
        });


    }


    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        /*** FLAG KISMINA TEKRAR BAK ***/

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                            Intent myIntent = new Intent(MainActivity.this, Dashboard.class);
                            MainActivity.this.startActivity(myIntent);
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "Yanlış E-posta veya şifre girdiniz", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }



}






