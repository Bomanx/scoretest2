package com.example.sunruoshi.scoretest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class showTop5 extends AppCompatActivity {
    private ListView top5player;
    private DatabaseReference mDatabase;
    private top5listAdapter listAdapter;
    public List<User> listResult = new ArrayList<>();
    public List<Integer> scores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_top5);
        top5player = (ListView) findViewById(R.id.top5list);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = mDatabase.child("Users").orderByChild("score").limitToFirst(5);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User single = dataSnapshot.getValue(User.class);
                listResult.add(single);
                Log.i("fetch top5", "players are: " + single.email);
                listAdapter.notifyDataSetChanged();
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
        });

        if(listResult!=null){
            listAdapter = new top5listAdapter(this,listResult);
            top5player.setAdapter(listAdapter);
        }

    }
}
