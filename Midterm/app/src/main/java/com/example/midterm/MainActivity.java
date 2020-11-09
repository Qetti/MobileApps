package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logInButton = findViewById(R.id.logInButton);
        logInButton.setOnClickListener(view -> openCalculator());

    }
    private void openCalculator(){
        Intent intent = new Intent(this, Calculator.class);
        startActivity(intent);
    }

}