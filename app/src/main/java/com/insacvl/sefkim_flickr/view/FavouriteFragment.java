package com.insacvl.sefkim_flickr.view;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.adapters.ImageListAdapter;
import com.insacvl.sefkim_flickr.db.DatabaseHelper;
import com.insacvl.sefkim_flickr.db.FavoriteContract;
import com.insacvl.sefkim_flickr.model.ImageModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourite_fragment, container, false);
        DatabaseHelper mDbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                FavoriteContract.FavoriteEntry._ID,
                FavoriteContract.FavoriteEntry.COLUMN_IMAGE_ID,
                FavoriteContract.FavoriteEntry.COLUMN_IMAGE_URL,
                FavoriteContract.FavoriteEntry.COLUMN_IMAGE_TITLE};

        Cursor cursor = db.query(
                FavoriteContract.FavoriteEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null);     // The sort order
        RecyclerView listView = view.findViewById(R.id.listviewfavourites);
        String[] fromColumns = {FavoriteContract.FavoriteEntry.COLUMN_IMAGE_TITLE};
        int[] toViews = {android.R.id.text1}; // The TextView in simple_list_item_1
        try {
            FavoritesAdapter adapter = new FavoritesAdapter(convertCursorToModelList(cursor));
            listView.setAdapter(adapter);
            db.close();
        }catch (Exception e){
            System.out.println("Exception thrown while converting the cursor to JSONArray");
        }
        return view;
    }

    public List<ImageModel> convertCursorToModelList(@NotNull Cursor cursor) throws JSONException {
        List<ImageModel> images = new ArrayList<>();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                // retrieve column values
                @SuppressLint("Range") String imageUrl = cursor.getString(cursor.getColumnIndex("imageUrl"));
                @SuppressLint("Range") String imageId = cursor.getString(cursor.getColumnIndex("imageId"));
                @SuppressLint("Range") String imageTitle = cursor.getString(cursor.getColumnIndex("imageTitle"));
                ImageModel imageModel = new ImageModel(imageId, imageTitle, imageUrl);
                images.add(imageModel);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return images;
    }
}
