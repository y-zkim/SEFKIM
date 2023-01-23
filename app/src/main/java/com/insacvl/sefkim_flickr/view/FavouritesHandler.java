package com.insacvl.sefkim_flickr.view;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageButton;

import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.db.DatabaseHelper;
import com.insacvl.sefkim_flickr.db.FavouriteContract;
import com.insacvl.sefkim_flickr.model.ImageModel;

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                        FavoritesHandler                                         *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * This FavouritesHandler handles the operations needed when communicating with the database on    *
 * clicking at the favourites button.                                                              *
 * Giving the fact that images visible in the favourites fragment are already in the database, the *
 * handler colors all these images buttons.                                                        *
 *                                                                                                 *
 *=================================================================================================*

 */

public class FavouritesHandler{
    private ImageButton favouriteButton;
    private Context context;
    private SQLiteDatabase db;

    public FavouritesHandler(ImageButton favouriteButton,
                             Context context){
        this.favouriteButton = favouriteButton;
        this.context = context;
    }

    public void handleFavouriteButtonClick(ImageModel image) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        db = mDbHelper.getWritableDatabase();
        // checking if the image doesn't exist already
        String selection = FavouriteContract.FavoriteEntry.COLUMN_IMAGE_ID + " = ?";
        String[] selectionArgs = { image.getImageId() };
        Cursor cursor = db.query(FavouriteContract.FavoriteEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if ((cursor.getCount() == 0)){
            ContentValues contentValues = new ContentValues();
            contentValues.put(FavouriteContract.FavoriteEntry.COLUMN_IMAGE_ID,image.getImageId());
            contentValues.put(FavouriteContract.FavoriteEntry.COLUMN_IMAGE_URL, image.getImageUrl());
            contentValues.put(FavouriteContract.FavoriteEntry.COLUMN_IMAGE_TITLE, image.getImageTitle());
            long newRowId = db.insert(FavouriteContract.FavoriteEntry.TABLE_NAME, null, contentValues);
        }else {
            String whereClause = FavouriteContract.FavoriteEntry.COLUMN_IMAGE_ID + " = ?";
            String[] whereArgs = { image.getImageId() };
            int deletedRows = db.delete(FavouriteContract.FavoriteEntry.TABLE_NAME, whereClause, whereArgs);
        }
        db.close();
    }


    public void handleFavouriteCheckImage(ImageModel image) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                FavouriteContract.FavoriteEntry._ID,
                FavouriteContract.FavoriteEntry.COLUMN_IMAGE_ID};
        String selection = FavouriteContract.FavoriteEntry.COLUMN_IMAGE_ID + " = ?";
        String[] selectionArgs = { image.getImageId() };
        Cursor cursor = db.query(
                FavouriteContract.FavoriteEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null);
        if(cursor.getCount() == 0) {
            favouriteButton.setImageResource(R.drawable.ic_unfavourite);
        }else{
            favouriteButton.setImageResource(R.drawable.ic_favourite);
        }
    }
}
