package com.example.android.quizmeteacher;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Harshit Maheshwari on 05-09-2017.
 */

public class DataAdapter extends ArrayAdapter<CandidateObject> {

    public FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference().child("class").child(FrozenActivity.classNBR).child("candidates");
    public ChildEventListener mChildEventListener;

    public String reg;

    public DataAdapter(@NonNull Context context, @NonNull List<CandidateObject> objects) {
        super(context, 0, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitem = convertView;

        if(listitem == null){
            listitem = LayoutInflater.from(getContext()).inflate(R.layout.list_layout,parent,false);
        }

        CandidateObject obj = getItem(position);

        final TextView registrationNumber = (TextView)listitem.findViewById(R.id.reg);
        registrationNumber.setText(obj.getRegistrtionNumber());

        reg = obj.getRegistrtionNumber();

        final TextView questionStat = (TextView)listitem.findViewById(R.id.questions_solved);
        questionStat.setText(obj.getMarksObtained() + "/" + obj.getQuestionsAttempted());

        final Button button = (Button)listitem.findViewById(R.id.frozen);
        if(obj.getFrozen().equals("1")){
            button.setEnabled(true);
        }
        else{
            button.setEnabled(false);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseReference.child(registrationNumber.getText().toString()).child("freeze").setValue(0);
                button.setEnabled(false);
                Toast.makeText(getContext(),registrationNumber.getText().toString(),LENGTH_SHORT).show();
            }
        });

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
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

        return listitem;
    }
}
