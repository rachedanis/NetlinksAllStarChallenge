package com.yalantis.guillotine.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.yalantis.guillotine.sample.utls.History;
import com.yalantis.guillotine.sample.utls.IRepository;
import com.yalantis.guillotine.sample.utls.Repository;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private IRepository repository;
    private ListView listView;
    private ArrayList<History> histories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = (ListView) findViewById(R.id.list);
        repository = new Repository();
        histories = (ArrayList<History>) repository.getAll();
        AdapterHistory adapter = new AdapterHistory(this, histories);
        listView.setAdapter(adapter);


    }


}
