package com.example.oblig1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.oblig1.databinding.ActivityAddNewEntryBinding;


/**
 *
 * Activity for adding new entries to the database.
 */
public class AddNewEntryActivity extends AppCompatActivity implements View.OnClickListener {


    private EntriesRepository db = new EntriesRepository(this.getApplication());
    private ActivityAddNewEntryBinding binding;
    private static final int PICK_IMAGE = 1000;

    private ConverterHelper converter = new ConverterHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewEntryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageView imageView = (ImageView) findViewById(R.id.galleryButton);
        imageView.setOnClickListener(this);

        Button btn = (Button) findViewById(R.id.submitButton);
        btn.setOnClickListener(this);
    }

    /**
     * Creates an Entry from the user input. (Image and name)
     * @return Entry
     */
    public Entry createEntryFromInput() {
        EditText editText = (EditText) findViewById(R.id.inputNameNewEntry);
        String txt = editText.getText().toString();
        ImageView imageView = (ImageView) findViewById(R.id.galleryButton);

        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Entry e = new Entry(txt, converter.BitmapToByteArray(bitmap));
        return e;
    }

    /**
     * Method to be called when user wants to select an image from their gallery
     */
    private void imageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //TODO find a more relevant method as its depricated (Media storage?)
        startActivityForResult(intent, PICK_IMAGE);
    }

    /**
     *
     * Listening for result returning from an activity started with startActivityForResult().
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode The integer result code returned by the child activity
     *                   through its setResult().
     * @param data An Intent, which can return result data to the caller
     *               (various data can be attached to Intent "extras").
     *
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            ImageView imageView = (ImageView) findViewById(R.id.galleryButton);
            imageView.setImageURI(imageUri);
        }
    }

    /**
     * Adds a new entry to the application data-structure.
     */
    public void addNewEntryToDatabase() {
        Entry entry = createEntryFromInput();
        Log.d("SUBMITTED", "onClick: " + entry.toString());
        db.insert(entry);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.galleryButton:
                imageChooser();
                break;
            case R.id.submitButton:
                addNewEntryToDatabase();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                break;

        }

    }
}