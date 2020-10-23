package com.example.homework01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String userName;
    public static int randomNumber;
    public int startRange, endRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button startButton = (Button) findViewById(R.id.startButton);
        final EditText userNameText = (EditText) findViewById(R.id.userName);
        final EditText startRangeText = (EditText) findViewById(R.id.startRange);
        final EditText endRangeText = (EditText) findViewById(R.id.endRange);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = userNameText.getText().toString();
                startRange = Integer.parseInt(startRangeText.getText().toString());
                endRange = Integer.parseInt(endRangeText.getText().toString());
                randomNumber = setRandomNumber(startRange, endRange);
                Log.i(String.valueOf(randomNumber), "onClick: ");
                startGame();
            }
        });

    }
    public void startGame() {
        Intent intent = new Intent(this, GamePage.class);
        startActivity(intent);
    }

    public int setRandomNumber(int startRange, int endRange){
        int k = (int) (Math.random() * (endRange - startRange + 1) + startRange);
        System.out.println(k);
        return k;
    }

}