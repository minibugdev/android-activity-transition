package com.trydroid.activitytransition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trydroid.activitytransition.model.Item;

public class DetailActivity extends AppCompatActivity {
    public static final String TRANSITION_IMAGE = "transition:image";
    public static final String TRANSITION_TEXT  = "transition:text";

    public static final String EXTRA_IMAGE = "DetailActivity:image";
    public static final String EXTRA_TEXT  = "DetailActivity:text";

    public static void launch(Activity activity, View imageView, View textView, Item item) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_IMAGE, item.getUrl());
        intent.putExtra(EXTRA_TEXT, item.getText());

        Pair<View, String> imageTransitionView = Pair.create(imageView, TRANSITION_IMAGE);
        Pair<View, String> textTransitionView = Pair.create(textView, TRANSITION_TEXT);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imageTransitionView, textTransitionView);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView imageView = (ImageView) findViewById(R.id.detail_image);
        TextView textView = (TextView) findViewById(R.id.detail_text);

        ViewCompat.setTransitionName(imageView, TRANSITION_IMAGE);
        ViewCompat.setTransitionName(textView, TRANSITION_TEXT);

        String url = getIntent().getStringExtra(EXTRA_IMAGE);
        String text = getIntent().getStringExtra(EXTRA_TEXT);

        textView.setText(text);
        Picasso.with(this)
            .load(url)
            .into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}