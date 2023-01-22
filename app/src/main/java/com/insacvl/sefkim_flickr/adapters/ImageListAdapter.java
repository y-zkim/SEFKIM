package com.insacvl.sefkim_flickr.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.db.DatabaseHelper;
import com.insacvl.sefkim_flickr.db.FavoriteContract;
import com.insacvl.sefkim_flickr.model.ImageModel;
import com.insacvl.sefkim_flickr.view.FavouritesHandler;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageListAdapter extends BaseAdapter {
    private List<ImageModel> images;
    private LayoutInflater inflater;
    private Context context;
    private ImageButton favouriteButton;
    private  TextView imageTitle;
    private ImageView cardImage;
    public ImageListAdapter(Context context, List<ImageModel> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.image_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageModel image = checkExistence(images.get(position));
        imageTitle = convertView.findViewById(R.id.title);
        favouriteButton = convertView.findViewById(R.id.favourite_button);
        cardImage = convertView.findViewById(R.id.imageView);
        holder.imageView.setTag(image);
        Picasso.get().load(image.getImageUrl()).into(holder.imageView);
        imageTitle.setText(image.getImageTitle());
        new FavouritesHandler(favouriteButton, context).handleFavouriteCheckImage(image);
        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ImageDetailsInvoker invoker = new ImageDetailsInvoker();
//                invoker.invoke();
                Bundle bundle = new Bundle();
                bundle.putString("imageUrl", image.getImageUrl());
                bundle.putString("imageText", image.getImageTitle());
                bundle.putString("imageId", image.getImageId());
//                Navigation.findNavController(view).navigate(R.id.confirmationAction, bundle);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_navigation_search_to_imageDetailsFragment,bundle);



//                navController.navigate(Uri.parse("myapp://nextFragment"));


//                View navigationView = inflater.inflate(R.navigation.mobile_navigation, parent, false);
//                NavController navController = Navigation.findNavController(navigationView);
//                navController.navigate(R.id.action_imageItemFragment_to_imageDetailsFragment2);
            }
        });
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FavouritesHandler(favouriteButton, context).handleFavouriteButtonClick(image);
                notifyDataSetChanged();
//                FragReloader fr =new FragReloader();
//                fr.FavFragReloader();
//                FavouriteFragment fragment = (FavouriteFragment) getFragmentManager().findFragmentByTag("fragment_tag");
//                fragmentManager.beginTransaction().detach(fragment).attach(fragment).commit();

            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
    }
    private ImageModel checkExistence(ImageModel image){
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                FavoriteContract.FavoriteEntry._ID,
                FavoriteContract.FavoriteEntry.COLUMN_IMAGE_ID};
        String selection = FavoriteContract.FavoriteEntry.COLUMN_IMAGE_ID + " = ?";
        String[] selectionArgs = { image.getImageId() };
        Cursor cursor = db.query(
                FavoriteContract.FavoriteEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null);
        if(cursor.getCount() == 0) return image;
        image.setFavourite(true);
        return image;
    }

}
