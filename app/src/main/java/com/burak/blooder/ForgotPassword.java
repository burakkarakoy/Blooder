package com.burak.blooder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final EditText forgetMail = (EditText) findViewById(R.id.forgetMailText);
        final Button recoveryButton = (Button) findViewById(R.id.recoveryButon);
        mAuth = FirebaseAuth.getInstance();


        recoveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String forgetMailText = forgetMail.getText().toString();

                resetPassword(forgetMailText);

            }
        });

    }

    private void resetPassword(final String email) {

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "E-posta gönderilirken bir hata oluştu", Toast.LENGTH_SHORT).show();


                        } else {

                            Toast.makeText(getApplicationContext(), "Şifreleme bağlantısı mailinize gönderildi", Toast.LENGTH_SHORT).show();

                        }


                    }
                });

    }

}
