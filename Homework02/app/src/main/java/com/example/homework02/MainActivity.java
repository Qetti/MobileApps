package com.example.homework02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button goBack;
    private LinearLayout HomeLayout;
    private LinearLayout LogInLayout;
    private LinearLayout RegistrationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeLayout = (LinearLayout) findViewById(R.id.HomeLayout);
        LogInLayout = (LinearLayout) findViewById(R.id.LogInLayout);
        RegistrationLayout = (LinearLayout) findViewById(R.id.RegistrationLayout);

        Button logInButton = (Button) findViewById(R.id.LogInButton);
        Button registrationButton = (Button) findViewById(R.id.RegistrationButton);
        goBack = (Button) findViewById(R.id.goBack);

        LogInLayout.setVisibility(View.INVISIBLE);
        RegistrationLayout.setVisibility(View.INVISIBLE);
        goBack.setVisibility(View.INVISIBLE);


        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeLayout.setVisibility(View.INVISIBLE);
                LogInLayout.setVisibility(View.VISIBLE);
                goBack.setVisibility(View.VISIBLE);
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeLayout.setVisibility(View.INVISIBLE);
                LogInLayout.setVisibility(View.INVISIBLE);
                RegistrationLayout.setVisibility(View.VISIBLE);
                goBack.setVisibility(View.VISIBLE);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeLayout.setVisibility(View.VISIBLE);
                LogInLayout.setVisibility(View.INVISIBLE);
                RegistrationLayout.setVisibility(View.INVISIBLE);
                goBack.setVisibility(View.INVISIBLE);

            }
        });



    }
}