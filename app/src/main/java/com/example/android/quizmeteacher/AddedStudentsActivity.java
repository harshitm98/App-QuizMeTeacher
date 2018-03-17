package com.example.android.quizmeteacher;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AddedStudentsActivity extends AppCompatActivity {

    private TextView addedStudents;
    private String classNBR,listOfStudents = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added_students);

        Bundle bundle = getIntent().getExtras();

        classNBR = bundle.getString("classNBR");
        Button button = (Button)findViewById(R.id.update_list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingDataToFirebase();
            }
        });


        addedStudents = (TextView)findViewById(R.id.added_students);
        File sdcard = Environment.getExternalStorageDirectory();


        //Get the text file
        File file = new File(sdcard,"reg.txt");
        //Read text from file
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "" ;

            while ((line = br.readLine()) != null) {
                text.append(line);
                listOfStudents += line + " ";
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
            e.printStackTrace();
            Toast.makeText(AddedStudentsActivity.this,"Something went wrong!",Toast.LENGTH_SHORT).show();
        }

        //Set the text
        addedStudents.setText(text.toString());
    }

    private void addingDataToFirebase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.child("class").child(classNBR).child("classNBR").setValue(classNBR);
        reference.child("class").child(classNBR).child("studentList").setValue(listOfStudents);
        reference.child("class").child(classNBR).child("status").setValue(0);
        Toast.makeText(this, "List of students successfully added to database.", Toast.LENGTH_SHORT).show();
    }

}
