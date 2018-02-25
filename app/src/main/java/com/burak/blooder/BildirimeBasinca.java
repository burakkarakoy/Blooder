package com.burak.blooder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BildirimeBasinca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bildirime_basinca);

        final TextView basinca = (TextView) findViewById(R.id.basinca);
    }
}
