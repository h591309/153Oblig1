/**
 *
 * Helper class used for displaying all entries in the database activity.
 */

package com.example.oblig1;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import java.util.List;

public class ViewBaseAdapter extends BaseAdapter implements View.OnClickListener {

    private final Context ctx;
    private EntriesSingleton entries;
    private LayoutInflater inflater;

    public ViewBaseAdapter(Context ctx, EntriesSingleton db) {
        this.ctx = ctx;
        this.entries = db;
        inflater = LayoutInflater.from(ctx);
    }


    @Override
    public int getCount() {
        return entries.getEntries().size();
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
        Button btn = (Button) convertView.findViewById(R.id.btnRemove);
        btn.setOnClickListener(this);
        btn.setTag((int)position);

        textView.setText(entries.getEntries().getEntry(position).getName());
        Bitmap bitmap = entries.getEntries().getEntry(position).getImg();
        img.setImageBitmap(bitmap);
        Log.d("BaseAdapter", "getView: " + entries.getEntries().getEntry(position).getName());
        return convertView;
    }

    @Override
    public void onClick(View v) {
        EntriesSingleton db = EntriesSingleton.getInstance();
        int id = (int) v.getTag();
        if(db.doesEntryExist(id)) {
            db.removeEntry(id);
            this.entries = db;
            Log.d("ID CLICKED", "onClick: " + id);
            this.notifyDataSetChanged();
        }
    }
}
