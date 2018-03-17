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

    private Button buttonSetA,buttonSetB,buttonSetC,buttonSetD,frozen, addStudents,addQuestions;

    public static String selectedButton, photoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frozen = (Button)findViewById(R.id.freeze);
        addStudents = (Button)findViewById(R.id.add_students);
        addQuestions = (Button)findViewById(R.id.add_questions);

        frozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterTheClassNBR(2);
            }
        });

        addStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               enterTheClassNBR(0);
            }
        });

        addQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterTheClassNBR(1);
            }
        });



    }

    @SuppressLint("RestrictedApi")
    public void enterTheClassNBR(final int flag){
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
                    if(flag == 0){
                        Intent intent = new Intent(MainActivity.this,AddedStudentsActivity.class);
                        intent.putExtra("classNBR",classNBR);
                        startActivity(intent);
                    }
                    else if(flag == 1){
                        Intent intent = new Intent(MainActivity.this,QuestionsAdderActivity.class);
                        intent.putExtra("classNBR",classNBR);
                        startActivity(intent);
                    }
                    else if(flag == 2){
                        Intent intent = new Intent(MainActivity.this,FrozenActivity.class);
                        intent.putExtra("classNBR",classNBR);
                        startActivity(intent);
                    }
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
