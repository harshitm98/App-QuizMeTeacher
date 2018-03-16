package com.example.android.quizmeteacher;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button buttonSetA,buttonSetB,buttonSetC,buttonSetD,frozen, addStudents;

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
        addStudents = (Button)findViewById(R.id.add_students);


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

        addStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               enterTheClassNBR();
            }
        });



    }

    @SuppressLint("RestrictedApi")
    public void enterTheClassNBR(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Class NBR");
        alert.setMessage("Enter the class NBR: ");
        Log.d(TAG, "enterTheClassNBR: Created alert dialogue");
        

        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        input.setLayoutParams(lp);
        input.setText("VL201718500");
        alert.setView(input,50,0,50,0);

        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: Clicked on yes");
                String classNBR = input.getText().toString();
                if(!classNBR.equals("VL201718500")){
                    //TODO: Add regular expression for classNBR
                    Intent intent = new Intent(MainActivity.this,AddedStudentsActivity.class);
                    intent.putExtra("classNBR",classNBR);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Please fill the complete classNBR", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alert.show();
    }
}
