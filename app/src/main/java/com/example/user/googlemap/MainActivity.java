package com.example.user.googlemap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFindLocation = (Button) findViewById(R.id.btnFindLocation);
        btnFindLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), FindLocationActivity.class);
                startActivity(myIntent);
            }
        });

        Button btnMyLocationMap = (Button) findViewById(R.id.btnMyLocationMap);
        btnMyLocationMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MyLocationMapActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
