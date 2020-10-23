package com.example.homework01;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GamePage extends AppCompatActivity {
    int score = 0;
    int attempt = 0;
    int maxAttempt = 3;
    int randomNumber = MainActivity.randomNumber;
    TextView scoreView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamepage);

        TextView welcomeUser = (TextView) findViewById(R.id.welcome);
        welcomeUser.setText("Hello " + MainActivity.userName);

        TextView scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText("You have " + score + " score");

    }

    @SuppressLint("SetTextI18n")
    public void play(View view){

        String message;
        System.out.println(attempt);
        if(attempt < maxAttempt){
            EditText userNumberView = findViewById(R.id.userNumber);
            int userNumber = Integer.parseInt(userNumberView.getText().toString());
            if(userNumber > randomNumber){
                message = "Enter lower number";
                attempt += 1;
            }
            else if (userNumber < randomNumber){
                message = "Enter higher number";
                attempt += 1;
            }
            else {
                message = String.format("Congratulations %s, you guess the number!!! ", MainActivity.userName);
                score += 1;
                TextView scoreView = (TextView) findViewById(R.id.scoreView);
                scoreView.setText("You have " + score + " score");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
        if(attempt >= maxAttempt) {
            Toast.makeText(this, "You lose", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);


        }

    }


}
