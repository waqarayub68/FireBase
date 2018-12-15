package com.example.waqarayub.firebasetask;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BeingList extends ArrayAdapter<Being> {
    private Activity context;
    private List<Being> beingList;
    public BeingList( Activity context, List<Being> beingList) {
        super(context, R.layout.list_layout,beingList);
        this.context = context;
        this.beingList = beingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator = context.getLayoutInflater();
        View listviewItem = inflator.inflate(R.layout.list_layout,null,true);

        TextView name = (TextView) listviewItem.findViewById(R.id.textViewName);
        TextView genre = (TextView) listviewItem.findViewById(R.id.textView2Genre);

        Being b = beingList.get(position);
        name.setText(b.getBeingname());
        genre.setText(b.getBeingGenre());

        return listviewItem;
    }
}
