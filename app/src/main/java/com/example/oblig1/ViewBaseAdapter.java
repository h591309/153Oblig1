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

/**
 *
 * Base adapter to display databasec content as a list inside an activity.
 *
 */

public class ViewBaseAdapter extends BaseAdapter implements View.OnClickListener {

    private final Context ctx;
    private EntriesSingleton entries;
    private LayoutInflater inflater;

    /**
     * Creates a new ViewBaseAdapter
     * @param ctx
     * @param db
     */
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
        //Sets a tag on the button to be used in the onClickListener to remove the correct entry.
        btn.setTag((int)position);

        //Updates correct text and image for the entry.
        textView.setText(entries.getEntries().getEntry(position).getName());
        Bitmap bitmap = entries.getEntries().getEntry(position).getImg();
        img.setImageBitmap(bitmap);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        EntriesSingleton db = EntriesSingleton.getInstance();
        int id = (int) v.getTag();

        //Removes correct entry if remove-button is pressed.
        if(db.doesEntryExist(id)) {
            db.removeEntry(id);
            this.entries = db;
            Log.d("ID CLICKED", "onClick: " + id);
            this.notifyDataSetChanged();
        }
    }
}
