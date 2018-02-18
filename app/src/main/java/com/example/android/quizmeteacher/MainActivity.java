package com.example.android.quizmeteacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonSetA,buttonSetB,buttonSetC,buttonSetD,frozen;

    public static String selectedButton, photoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSetA = (Button)findViewById(R.id.set_a);
        buttonSetB = (Button)findViewById(R.id.set_b);
        buttonSetC = (Button)findViewById(R.id.set_c);
        buttonSetD = (Button)findViewById(R.id.set_d);
        frozen = (Button)findViewById(R.id.freeze);


        buttonSetA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = buttonSetA.getText().toString();
                photoUrl = "https://firebasestorage.googleapis.com/v0/b/quizme-fd313.appspot.com/o/sets%2FsetA.png?alt=media&token=b8efb99e-035d-4549-9d3e-215add3eff59";
                Intent i = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(i);
            }
        });

        buttonSetB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = buttonSetB.getText().toString();
                photoUrl = "https://firebasestorage.googleapis.com/v0/b/quizme-fd313.appspot.com/o/sets%2FsetB.png?alt=media&token=9e1d137b-363a-47d2-b629-7b49091e8dc5";
                Intent i = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(i);
            }
        });

        buttonSetC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = buttonSetC.getText().toString();
                photoUrl = "https://firebasestorage.googleapis.com/v0/b/quizme-fd313.appspot.com/o/sets%2FsetC.png?alt=media&token=4b6c54c7-f7d0-44a1-9ead-24ac4a4a8112";
                Intent i = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(i);
            }
        });

        buttonSetD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = buttonSetD.getText().toString();
                photoUrl = "https://firebasestorage.googleapis.com/v0/b/quizme-fd313.appspot.com/o/sets%2FsetD.png?alt=media&token=be50970c-32a2-4131-8b7e-d87f16d7c6b0";
                Intent i = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(i);
            }
        });

        frozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,FrozenActivity.class);
                startActivity(i);
            }
        });





    }
}
