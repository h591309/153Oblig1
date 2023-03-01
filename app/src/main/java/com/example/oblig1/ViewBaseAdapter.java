/**
 *
 * Helper class used for displaying all entries in the database activity.
 */

package com.example.oblig1;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
 * Base adapter to display databasec content as a list inside an activity.
 *
 */

public class ViewBaseAdapter extends BaseAdapter implements View.OnClickListener {

    private Application application;
    private final Context ctx;
    private QuizViewModel viewModel;
    private LayoutInflater inflater;

    private ConverterHelper converter = new ConverterHelper();

    /**
     * Creates a new ViewBaseAdapter
     * @param application
     * @param viewModel
     */
    public ViewBaseAdapter(Application application, Context ctx, QuizViewModel viewModel) {
        this.application = application;
        this. ctx = ctx;
        this.viewModel = viewModel;
        inflater = LayoutInflater.from(ctx);
    }


    @Override
    public int getCount() {
        return viewModel.getRepoSize();
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
        btn.setTag((int)viewModel.getAllEntries().getValue().get(position).getId());

        //Updates correct text and image for the entry.
        textView.setText(viewModel.getAllEntries().getValue().get(position).getName());
        Bitmap bitmap = converter.ByteArrayToBitmap(viewModel.getAllEntries().getValue().get(position).getImg());
        img.setImageBitmap(bitmap);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        int id = (int) v.getTag();

        //Removes correct entry if remove-button is pressed.
        viewModel.delete(id);
    }
}
