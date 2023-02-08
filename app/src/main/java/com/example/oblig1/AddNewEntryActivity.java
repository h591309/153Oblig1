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
import com.example.oblig1.databinding.ActivityMainBinding;


public class AddNewEntryActivity extends AppCompatActivity implements View.OnClickListener {


    private EntriesSingleton db = EntriesSingleton.getInstance();
    private ActivityAddNewEntryBinding binding;
    private static final int PICK_IMAGE = 1000;

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

    public Entry createEntryFromInput() {
        EditText editText = (EditText) findViewById(R.id.inputNameNewEntry);
        String txt = editText.getText().toString();
        ImageView imageView = (ImageView) findViewById(R.id.galleryButton);

        Bitmap bitmap= ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Entry e = new Entry(txt, bitmap);
        return e;
    }

    private void imageChooser() {


        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            ImageView imageView = (ImageView) findViewById(R.id.galleryButton);
            imageView.setImageURI(imageUri);
        }
    }

    public void addNewEntryToDatabase() {
        Entry entry = createEntryFromInput();
        Log.d("SUBMITTED", "onClick: " + entry.toString());
        db.getEntries().addEntry(entry);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.galleryButton:
                imageChooser();
                break;
            case R.id.submitButton:
                addNewEntryToDatabase();
                //Intent intent = new Intent(getBaseContext(), DatabaseActivity.class);
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                break;

        }

    }
}