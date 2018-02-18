package com.example.android.quizmeteacher;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AddedStudentsActivity extends AppCompatActivity {

    private TextView addedStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added_students);
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
}
