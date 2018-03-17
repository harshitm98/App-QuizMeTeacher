package com.example.android.quizmeteacher;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
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
    private static final String TAG = "DataAdapter";
    int count=0;

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
        questionStat.setText(obj.getMarksObtained());

        final TextView percentage = (TextView)listitem.findViewById(R.id.percentage);
        int i_marks, i_questionsAttempted;
        i_marks = Integer.parseInt(obj.getMarksObtained());
        i_questionsAttempted = Integer.parseInt(obj.getQuestionsAttempted());
        if(i_questionsAttempted == 0){
            percentage.setText("0");
        }
        else{
            float t = i_marks*100/i_questionsAttempted;
            percentage.setText(t + "");
        }


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

        //TODO Unfreeze and freeze button and text

        final Button button2 = (Button)listitem.findViewById(R.id.frozen2);


        final View finalListitem = listitem;
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.child("reg").getValue().toString().equals(registrationNumber.getText().toString())){
                    final TextView name = (TextView) finalListitem.findViewById(R.id.card_name);
                    name.setText(dataSnapshot.child("name").getValue().toString());
                    count++;
                    int colorInt;
                    if(count%5 == 1){
                        colorInt = Color.parseColor("#7FA8F7");
                    }
                    else if(count%5 == 2){
                        colorInt = Color.parseColor("#F1A460");

                    }
                    else if(count%5 == 3){
                        colorInt = Color.parseColor("#EB6186");
                    }
                    else if(count%5 == 3){
                        colorInt = Color.parseColor("#A07BFE");
                    }
                    else{
                        colorInt = Color.parseColor("#6AE3C4");
                    }
                    final CardView cardView = (CardView)finalListitem.findViewById(R.id.card_view);
                    cardView.setCardBackgroundColor(colorInt);
                    button.setTextColor(colorInt);
                    button2.setTextColor(colorInt);
                }
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
