package com.yalantis.guillotine.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yalantis.guillotine.sample.utls.History;

import java.util.ArrayList;

public class AdapterHistory extends ArrayAdapter<History> {
    public AdapterHistory(Context context, ArrayList<History> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        History user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_adapter_history, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.a);
        TextView tvHome = (TextView) convertView.findViewById(R.id.b);
        // Populate the data into the template view using the data object
        tvName.setText(user.getAddresse());
        tvHome.setText(user.getDate());
        // Return the completed view to render on screen
        return convertView;
    }
}