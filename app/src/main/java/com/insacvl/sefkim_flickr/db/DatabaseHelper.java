package com.insacvl.sefkim_flickr.db;
/**
* @Author : ZKIM Youssef
*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.insacvl.sefkim_flickr.model.ImageModel;

/*

 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                        DatabaseHelper                                           *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * This code is for a class that interacts with a SQLite database to perform various operations on *
 * a "favorites" table. The class includes the following methods:                                  *
 *                                                                                                 *
 * - fetchData(): This method takes in a whereSelection string and an array of whereSelectionArgs, *
 * and performs a query on the "favorites" table to fetch data based on the provided selection     *
 * criteria. The method returns a cursor containing the resulting rows.                            *
 *                                                                                                 *
 * - checkPhotoExistenceById(): This method takes in an imageId and checks if a row with that ID   *
 * exists in the "favorites" table. It returns a boolean indicating whether the image was found    *
 * or not.                                                                                         *
 *                                                                                                 *
 * - deleteIfExistsById(): This method takes in an imageId and checks if a row with that ID exists *
 * in the "favorites" table. If the image exists, it will delete the corresponding row from the    *
 * table.                                                                                          *
 *                                                                                                 *
 * insertImageInDB(): This method takes in an ImageModel object and inserts the image's ID, URL,   *
 * and title into the "favorites" table.                                                           *
 *                                                                                                 *
 *=================================================================================================*

 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;
    private static final String[] PROJECTION = {
            FavouriteContract.FavoriteEntry._ID,
            FavouriteContract.FavoriteEntry.COLUMN_IMAGE_ID,
            FavouriteContract.FavoriteEntry.COLUMN_IMAGE_URL,
            FavouriteContract.FavoriteEntry.COLUMN_IMAGE_TITLE};

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_FAVORITES_TABLE = "CREATE TABLE " +
                FavouriteContract.FavoriteEntry.TABLE_NAME + " (" +
                FavouriteContract.FavoriteEntry._ID + " INTEGER PRIMARY KEY," +
                FavouriteContract.FavoriteEntry.COLUMN_IMAGE_ID + " TEXT," +
                FavouriteContract.FavoriteEntry.COLUMN_IMAGE_URL + " TEXT," +
                FavouriteContract.FavoriteEntry.COLUMN_IMAGE_TITLE + " TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITES_TABLE);
    }

    // This method is called when the database needs to be upgraded.
    // It drops the existing table and calls onCreate to recreate it.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavouriteContract.FavoriteEntry.TABLE_NAME);
        onCreate(db);
    }

    public Cursor fetchData(String whereSelection, String[] whereSelectionArgs) {
        // Create a readable database object
        SQLiteDatabase db = this.getReadableDatabase();

        // Fetch data from the database table based on the whereSelection and whereSelectionArgs passed
        Cursor cursor = db.query(
                FavouriteContract.FavoriteEntry.TABLE_NAME,
                PROJECTION,
                whereSelection,
                whereSelectionArgs,
                null,
                null,
                null);

        // Return the cursor
        return cursor;
    }

    public Boolean checkPhotoExistenceById(String imageId) {
        // Create a readable database object
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the selection criteria for the query
        String selection = FavouriteContract.FavoriteEntry.COLUMN_IMAGE_ID + " = ?";
        String[] selectionArgs = {imageId};

        // Perform the query
        Cursor cursor = db.query(
                FavouriteContract.FavoriteEntry.TABLE_NAME,
                PROJECTION,
                selection,
                selectionArgs,
                null,
                null,
                null);

        // Return true if a row was found, false otherwise
        return cursor.getCount() != 0;
    }

    public void deleteIfExistsById(String imageId) {
        // Check if the image exists in the database
        if (checkPhotoExistenceById(imageId)) {
            // Create a readable database object
            SQLiteDatabase db = this.getReadableDatabase();

            // Define the where clause for the delete statement
            String whereClause = FavouriteContract.FavoriteEntry.COLUMN_IMAGE_ID + " = ?";
            String[] whereArgs = {imageId};

            // Delete the image from the database
            db.delete(FavouriteContract.FavoriteEntry.TABLE_NAME, whereClause, whereArgs);
        } else {
            // If the image doesn't exist in the database, return without doing anything
            return;
        }
    }

    public void insertImageInDB(ImageModel image) {
        // Create a readable database object
        SQLiteDatabase db = this.getReadableDatabase();

        // Create a ContentValues object to hold the data for the new row
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavouriteContract.FavoriteEntry.COLUMN_IMAGE_ID, image.getImageId());
        contentValues.put(FavouriteContract.FavoriteEntry.COLUMN_IMAGE_URL, image.getImageUrl());
        contentValues.put(FavouriteContract.FavoriteEntry.COLUMN_IMAGE_TITLE, image.getImageTitle());

        // Insert the data into the database
        long newRowId = db.insert(FavouriteContract.FavoriteEntry.TABLE_NAME, null, contentValues);
    }

}

