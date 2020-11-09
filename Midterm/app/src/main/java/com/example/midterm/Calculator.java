package com.example.midterm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


public class Calculator extends AppCompatActivity {

    double input1 = 0, input2 = 0;
    TextView edt1;
    boolean Addition, Subtract, Multiplication, Division, mRemainder, decimal;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonAdd, buttonSub,
            buttonMul, buttonDivision, buttonEqual, buttonDel, buttonDot, Remainder, goBack;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonDot = findViewById(R.id.buttonDot);
        buttonAdd = findViewById(R.id.buttonadd);
        buttonSub = findViewById(R.id.buttonsub);
        buttonMul = findViewById(R.id.buttonmul);
        buttonDivision = findViewById(R.id.buttondiv);
        Remainder = findViewById(R.id.Remainder);
        buttonDel = findViewById(R.id.buttonDel);
        buttonEqual = findViewById(R.id.buttoneql);
        goBack = findViewById(R.id.goBack);

        edt1 = findViewById(R.id.display);

        button1.setOnClickListener(v -> edt1.setText(edt1.getText() + "1"));

        button2.setOnClickListener(v -> edt1.setText(edt1.getText() + "2"));

        button3.setOnClickListener(v -> edt1.setText(edt1.getText() + "3"));

        button4.setOnClickListener(v -> edt1.setText(edt1.getText() + "4"));

        button5.setOnClickListener(v -> edt1.setText(edt1.getText() + "5"));

        button6.setOnClickListener(v -> edt1.setText(edt1.getText() + "6"));

        button7.setOnClickListener(v -> edt1.setText(edt1.getText() + "7"));

        button8.setOnClickListener(v -> edt1.setText(edt1.getText() + "8"));

        button9.setOnClickListener(v -> edt1.setText(edt1.getText() + "9"));

        button0.setOnClickListener(v -> edt1.setText(edt1.getText() + "0"));

        buttonAdd.setOnClickListener(v -> {
            if (edt1.getText().length() != 0) {
                input1 = Float.parseFloat(edt1.getText() + "");
                Addition = true;
                decimal = false;
                edt1.setText(null);
            }
        });

        buttonSub.setOnClickListener(v -> {
            if (edt1.getText().length() != 0) {
                input1 = Float.parseFloat(edt1.getText() + "");
                Subtract = true;
                decimal = false;
                edt1.setText(null);
            }
        });

        buttonMul.setOnClickListener(v -> {
            if (edt1.getText().length() != 0) {
                input1 = Float.parseFloat(edt1.getText() + "");
                Multiplication = true;
                decimal = false;
                edt1.setText(null);
            }
        });

        buttonDivision.setOnClickListener(v -> {
            if (edt1.getText().length() != 0) {
                input1 = Float.parseFloat(edt1.getText() + "");
                Division = true;
                decimal = false;
                edt1.setText(null);
            }
        });

        Remainder.setOnClickListener(v -> {
            if (edt1.getText().length() != 0) {
                input1 = Float.parseFloat(edt1.getText() + "");
                mRemainder = true;
                decimal = false;
                edt1.setText(null);
            }
        });

        buttonEqual.setOnClickListener(v -> {
            if (Addition || Subtract || Multiplication || Division || mRemainder) {
                input2 = Float.parseFloat(edt1.getText() + "");
            }

            if (Addition) {

                edt1.setText(input1 + input2 + "");
                Addition = false;
            }

            if (Subtract) {

                edt1.setText(input1 - input2 + "");
                Subtract = false;
            }

            if (Multiplication) {
                edt1.setText(input1 * input2 + "");
                Multiplication = false;
            }

            if (Division) {
                edt1.setText(input1 / input2 + "");
                Division = false;
            }
            if (mRemainder) {
                edt1.setText(input1 % input2 + "");
                mRemainder = false;
            }
        });

        buttonDel.setOnClickListener(v -> {
            edt1.setText("");
            input1 = 0.0;
            input2 = 0.0;
        });

        buttonDot.setOnClickListener(v -> {
            if (!decimal) {
                edt1.setText(edt1.getText() + ".");
                decimal = true;
            }

        });

        goBack.setOnClickListener(v -> goBack());
    }
    public void goBack(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
