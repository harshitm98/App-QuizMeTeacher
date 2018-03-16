package com.example.android.quizmeteacher;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class QuestionsAdderActivity extends AppCompatActivity {
    private static final String TAG = "QuestionsAdderActivity";
    private String classNBR;
    private String JSONresponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_adder);
        getExtra();
        getFileFromStorage();
    }

    private void getExtra(){
        Bundle bundle = getIntent().getExtras();
        classNBR = bundle.getString("classNBR");
    }

    private void getFileFromStorage(){
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard,"questions.txt");
        StringBuilder stringBuilder = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line  = reader.readLine())!= null){
                stringBuilder.append(line);
            }
            JSONresponse = stringBuilder.toString();
            Log.d(TAG, "getFileFromStorage: " + JSONresponse);
            JSONParser(JSONresponse);
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "JSON string not parsed properly", Toast.LENGTH_SHORT).show();
        }
    }
    private void JSONParser(String JSONresponse) throws JSONException {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("class").child(classNBR).child("questions");
        JSONObject rootio = new JSONObject(JSONresponse);
        JSONObject root = rootio.getJSONObject("questions");
        Log.d(TAG, "JSONParser: " + root.length());
        for(int i=1;i<root.length()+1;i++){
            JSONObject question;
            String questionNumber;
            if(i<10){
                questionNumber = "question0" + i;
            }
            else{
                questionNumber = "question" + i;
            }
            question = root.getJSONObject(questionNumber);
            String questionText, correctAnswer, set, option1, option2, option3, option4;
            questionText = question.getString("question_text");
            correctAnswer = question.getString("correct_answer");
            set = question.getString("set");
            option1 = question.getString("option1");
            option2 = question.getString("option2");
            option3 = question.getString("option3");
            option4 = question.getString("option4");
            reference.child(questionNumber).child("question_text").setValue(questionText);
            reference.child(questionNumber).child("correct_answer").setValue(correctAnswer);
            reference.child(questionNumber).child("set").setValue(set);
            reference.child(questionNumber).child("option1").setValue(option1);
            reference.child(questionNumber).child("option2").setValue(option2);
            reference.child(questionNumber).child("option3").setValue(option3);
            reference.child(questionNumber).child("option4").setValue(option4);
        }
    }
}
