package com.example.oblig1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Entry.class}, version = 1, exportSchema = false)
public abstract class QuestionDatabase extends RoomDatabase {
    public abstract EntryDao entryDao();

    private static volatile QuestionDatabase INSTANCE;

    private static Context ctx;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    /**
     * Finds the room database instance. Creates a new one if non exists.
     * @param context
     * @return
     */
    static QuestionDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuestionDatabase.class) {
                if (INSTANCE == null) {
                    ctx = context;
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    QuestionDatabase.class, "entry_database")
                            .addCallback(sRoomDatabaseCallback).build();

                }
                //resetDb();
            }
        }
        return INSTANCE;
    }

    /**
     * Creates bitmap from given drawable.
     * @param drawableId
     * @return Bitmap
     */
    private static Bitmap createBitmapFromDrawable(int drawableId) {
        Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(), drawableId);
        return bitmap;
    }

    /**
     * Temporary: Used to reset the initial database entries.
     */
    private static void resetDb() {
        databaseWriteExecutor.execute(() -> {
            // Populate the database in the background.
            // If you want to start with more words, just add them.
            EntryDao dao = INSTANCE.entryDao();
            dao.deleteAll();

            Bitmap catImage = createBitmapFromDrawable(R.drawable.cat1);
            ConverterHelper converter = new ConverterHelper();
            Entry e = new Entry("Kitten with blue eyes", converter.BitmapToByteArray(catImage));
            dao.insert(e);
            catImage = createBitmapFromDrawable(R.drawable.cat2);
            e = new Entry("Black cat with yellow eyes", converter.BitmapToByteArray(catImage));
            dao.insert(e);
            catImage = createBitmapFromDrawable(R.drawable.cat3);
            e = new Entry("Not a cat", converter.BitmapToByteArray(catImage));
            dao.insert(e);
            catImage = createBitmapFromDrawable(R.drawable.cat4);
            e = new Entry("Striped cat", converter.BitmapToByteArray(catImage));
            dao.insert(e);
            catImage = createBitmapFromDrawable(R.drawable.cat5);
            e = new Entry("White belly cat", converter.BitmapToByteArray(catImage));
            dao.insert(e);
            //db.setExampleDataExist();

        });
    }

    /**
     * Creates default initial values when creating the database for the first time.
     *
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                EntryDao dao = INSTANCE.entryDao();
                dao.deleteAll();

                Bitmap catImage = createBitmapFromDrawable(R.drawable.cat1);
                ConverterHelper converter = new ConverterHelper();
                Entry e = new Entry("Kitten with blue eyes", converter.BitmapToByteArray(catImage));
                dao.insert(e);
                catImage = createBitmapFromDrawable(R.drawable.cat2);
                e = new Entry("Black cat with yellow eyes", converter.BitmapToByteArray(catImage));
                dao.insert(e);
                catImage = createBitmapFromDrawable(R.drawable.cat3);
                e = new Entry("Not a cat", converter.BitmapToByteArray(catImage));
                dao.insert(e);
                catImage = createBitmapFromDrawable(R.drawable.cat4);
                e = new Entry("Striped cat", converter.BitmapToByteArray(catImage));
                dao.insert(e);
                catImage = createBitmapFromDrawable(R.drawable.cat5);
                e = new Entry("White belly cat", converter.BitmapToByteArray(catImage));
                dao.insert(e);
                //db.setExampleDataExist();

            });
        }
    };


}