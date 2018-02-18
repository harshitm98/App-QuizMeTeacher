package com.example.android.quizmeteacher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Harshit Maheshwari on 05-09-2017.
 */

public class FrozenActivity extends AppCompatActivity {

    public ArrayList<CandidateObject> candidates;

    public FirebaseDatabase mFirebaseDatabase;
    public DatabaseReference mDatabaseReference;
    public ChildEventListener mChildEventListener;

    public Button button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        ListView listView = (ListView)findViewById(R.id.list);

        candidates = new ArrayList<>();

        final DataAdapter adapter;

        button = (Button)findViewById(R.id.refresh);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });

        adapter = new DataAdapter(this,candidates);



        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("candidates");
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CandidateObject object = new CandidateObject();
                object.setRegistrtionNumber(dataSnapshot.child("reg").getValue().toString());
                object.setMarksObtained(dataSnapshot.child("questions_solved").getValue().toString());
                object.setQuestionsAttempted(dataSnapshot.child("questions_attempted").getValue().toString());
                object.setFrozen(dataSnapshot.child("freeze").getValue().toString());

                candidates.add(object);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(view.getId() == R.id.frozen){
                    CandidateObject object = (CandidateObject) adapterView.getItemAtPosition(i);
                    object.setFrozen("0");
                }

            }
        });
    }
}
