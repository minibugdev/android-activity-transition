package com.trydroid.activitytransition.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trydroid.activitytransition.R;
import com.trydroid.activitytransition.model.Item;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private List<Item> mItems;

    public GridViewAdapter(List<Item> items) {
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        Item item = (Item) getItem(position);

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, viewGroup, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context)
            .load(item.getUrl())
            .into(holder.imageView);

        holder.textView.setText(item.getText());

        return convertView;
    }

    static class ViewHolder {
        public ImageView imageView;
        public TextView  textView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.grid_image);
            textView = (TextView) view.findViewById(R.id.grid_text);
        }
    }
}