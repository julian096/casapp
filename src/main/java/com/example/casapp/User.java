package com.example.casapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class User extends AppCompatActivity {
    TextView etiUser;
    Button logoutUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        logoutUser = (Button) findViewById(R.id.logoutUser);
        etiUser = (TextView) findViewById(R.id.etiUser);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        String name = b.getString("user");
        String spotlights = b.getString("spotlights");
        String arraySpotlights[] = spotlights.split(",");
        etiUser.setText(name);

        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(logout);
            }
        });


    }
}
