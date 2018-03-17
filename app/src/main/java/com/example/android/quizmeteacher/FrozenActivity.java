package com.example.android.quizmeteacher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

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

    public static String classNBR;
    public ArrayList<CandidateObject> candidates;

    public FirebaseDatabase mFirebaseDatabase;
    public DatabaseReference mDatabaseReference;
    public ChildEventListener mChildEventListener;

    private static final String TAG = "FrozenActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        final ListView listView = (ListView)findViewById(R.id.list);

        candidates = new ArrayList<>();

        final DataAdapter adapter;
        Bundle bundle = getIntent().getExtras();
        classNBR = bundle.getString("classNBR");

        refresh();

        adapter = new DataAdapter(this,candidates);





        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("class").child(classNBR).child("candidates");
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CandidateObject object = new CandidateObject();
                object.setRegistrtionNumber(dataSnapshot.child("reg").getValue().toString());
                object.setMarksObtained(dataSnapshot.child("questions_solved").getValue().toString());
                object.setQuestionsAttempted(dataSnapshot.child("questions_attempted").getValue().toString());
                object.setFrozen(dataSnapshot.child("freeze").getValue().toString());

                candidates.add(object);
                listView.setAdapter(adapter);
                ProgressBar bar = (ProgressBar)findViewById(R.id.spinner);
                bar.setVisibility(View.GONE);
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
    private void refresh(){
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshes();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Check if user triggered a refresh:
            case R.menu.refresh:
                Log.i(TAG, "Refresh menu item selected");

                // Signal SwipeRefreshLayout to start the progress indicator
                SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
                swipeRefreshLayout.setRefreshing(true);

                // Start the refresh background task.
                // This method calls setRefreshing(false) when it's finished.
                refreshes();

                return true;
        }

        // User didn't trigger a refresh, let the superclass handle this action
        return super.onOptionsItemSelected(item);

    }
    public void refreshes(){
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
