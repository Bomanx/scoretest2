package com.example.sunruoshi.scoretest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class showTop5 extends AppCompatActivity {
    private ListView top5player;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_top5);
        top5player = (ListView) findViewById(R.id.top5list);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listAdapter = new ArtistInfoAdapter(this, artistsList);
        listView_of_artists.setAdapter(listAdapter);
    }
}
