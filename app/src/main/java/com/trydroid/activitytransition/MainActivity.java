package com.trydroid.activitytransition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.trydroid.activitytransition.adapter.GridViewAdapter;
import com.trydroid.activitytransition.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Item> items = buildItemList();
        mAdapter = new GridViewAdapter(items);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Item item = (Item) mAdapter.getItem(position);

                View itemImageView = view.findViewById(R.id.grid_image);
                View itemTextView = view.findViewById(R.id.grid_text);

                DetailActivity.launch(MainActivity.this, itemImageView, itemTextView, item);
            }
        });
    }

    private List<Item> buildItemList() {
        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            items.add(new Item("http://lorempixel.com/800/600/sports/" + i, "Item " + i));
        }

        return items;
    }
}
