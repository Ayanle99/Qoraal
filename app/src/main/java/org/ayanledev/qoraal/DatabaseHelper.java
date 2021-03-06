package org.ayanledev.qoraal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "note";

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String TIME_POSTED = "date_written";

    Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        try {

            String query = "CREATE TABLE " + TABLE_NAME +
                    " (" +
                    ID + " INTEGER PRIMARY KEY  AUTOINCREMENT ," +
                    TITLE + " TEXT, " +
                    CONTENT + " TEXT, " +
                    TIME_POSTED + " TEXT );";

            db.execSQL(query);


        }catch (SQLException exc){
            Toast.makeText(context,
                    "Khaladbaa dhacay.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);


    }

    public void addNote(String title, String content, String date){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();


        cv.put(TITLE,title );
        cv.put(CONTENT,content);
        cv.put(TIME_POSTED, date);


        long result = db.insert(TABLE_NAME, null, cv);

        try {
            if (result == -1) {
                Toast.makeText(context,
                        "Waxbaa khaldamay, ku celi.", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(context,
                        "Waraaqdii waan ku darnay booga!", Toast.LENGTH_SHORT).show();


            }

        }catch (Exception exc){
            Toast.makeText(context,
                    "An error occured. Try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllDate(){

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;


        if (db != null){

            cursor =  db.rawQuery(query, null);

        }

        return cursor;
    }

    void updateData(String row_id,
                    String title,
                    String content,
                    String date){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(TITLE, title);
        cv.put(CONTENT, content);
        cv.put(TIME_POSTED, date);

        try {
            long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});

            if (result == -1) {
                Toast.makeText(context,
                        "Waxbaa khaldamay, ku celi.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context,
                        "Tifaftirkii waa hirgalay..", Toast.LENGTH_SHORT).show();
            }

        }catch (SQLException ex){
            Log.d("TAG", "updateData: " + ex.getMessage().toString());
        }

    }

    public void delteAll(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM  "+ TABLE_NAME);
    }
    public void deleteData(String row_id){

        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME, "id=?",new String[]{row_id});

        if (result == -1){
            Toast.makeText(context,
                    "Waxbaa khaldamay, ku celi masaxaada.", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context,
                    "Waad masaxday..", Toast.LENGTH_SHORT).show();

        }

    }


}