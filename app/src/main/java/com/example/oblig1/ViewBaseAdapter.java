package com.example.oblig1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class ViewBaseAdapter extends BaseAdapter {

    private final Context ctx;
    private EntriesArrayList entries = new EntriesArrayList();
    private LayoutInflater inflater;

    public ViewBaseAdapter(Context ctx, EntriesArrayList entries) {
        this.ctx = ctx;
        this.entries = new EntriesArrayList();
        inflater = LayoutInflater.from(ctx);
        for(int i = 0; i < entries.size(); i++) {
            this.entries.addEntry(entries.getEntry(i));
        }
    }


    @Override
    public int getCount() {
        return entries.size();
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
        convertView = inflater.inflate(R.layout.activity_content, null);
        TextView textView = (TextView) convertView.findViewById(R.id.textView3);
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView);

        textView.setText(entries.getEntry(position).getName());
        img.setImageResource(entries.getEntry(position).getImg());
        Log.d("BaseAdapter", "getView: " + entries.getEntry(position).getName());
        return convertView;
    }
}
