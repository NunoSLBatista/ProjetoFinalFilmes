package com.example.projetofinalfilmes.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projetofinalfilmes.data.UserContract.*;
import com.example.projetofinalfilmes.data.GenreContract.*;
import com.example.projetofinalfilmes.models.Genre;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Movie.db";

    private static final String SQL_CREATE_USER =
            "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                    UserEntry.COLUMN_EMAIL + " TEXT, " +
                    UserEntry.COLUMN_PASSWORD + " TEXT, " +
                    UserEntry.COLUMN_PHOTO + " BLOB )";

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;

    private static final String SQL_CREATE_GENRE =
            "CREATE TABLE " + GenreEntry.TABLE_NAME + " (" +
                    GenreEntry.COLUMN_ID + " INTEGERE, " +
                    GenreEntry.COLUMN_NAME + " TEXT )";

    private static final String SQL_DELETE_GENRE =
            "DROP TABLE IF EXISTS " + GenreEntry.TABLE_NAME;


    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_GENRE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USER);
        db.execSQL(SQL_DELETE_GENRE);
        onCreate(db);
    }

    public void addGenres(ArrayList<Genre> genresArrayList){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(GenreEntry.TABLE_NAME, null, null, null, null, null, null);

        if(cursor.getCount() < 1){
            for(int i = 0; i < genresArrayList.size(); i++){
                ContentValues contentValues = new ContentValues();
                contentValues.put(GenreEntry.COLUMN_ID, genresArrayList.get(i).getId());
                contentValues.put(GenreEntry.COLUMN_NAME, genresArrayList.get(i).getName());
                db.insert(GenreEntry.TABLE_NAME, null, contentValues);
            }
        }

    }

    public Genre getGenre(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        String selection = GenreEntry.COLUMN_ID + " = ?";
        String[] args = {id};

        Cursor cursor = db.query(GenreEntry.TABLE_NAME, null, selection, args, null, null, null);

        if(cursor.moveToFirst()){
            Genre genre = new Genre();
            genre.setId(cursor.getString(cursor.getColumnIndex(GenreEntry.COLUMN_ID)));
            genre.setName(cursor.getString(cursor.getColumnIndex(GenreEntry.COLUMN_NAME)));

            return genre;
        }

        return null;

    }


}
