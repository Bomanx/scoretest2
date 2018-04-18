package com.example.sunruoshi.scoretest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

public class top5listAdapter extends BaseAdapter {
    private Context context;
    private List<Players> players;

    public top5listAdapter(Context context, List<Players> players) {
//        super(context, eventList);
        this.context = context;
        this.players = players;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.activity_top5list_adapter, parent, false);
        }
        //////here should be the code how we render top5 players


        return  listItem;
    }
}
