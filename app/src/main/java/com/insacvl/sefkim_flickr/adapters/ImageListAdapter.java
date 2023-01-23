package com.insacvl.sefkim_flickr.adapters;
/**
* @Author : ZKIM Youssef
*/
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.insacvl.sefkim_flickr.db.FavouriteContract;
import com.insacvl.sefkim_flickr.model.ImageModel;
import com.insacvl.sefkim_flickr.view.FavouritesHandler;
import com.squareup.picasso.Picasso;

import java.util.List;

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *
 *                                     ImageListAdapter                                            *
 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * ImageListAdapter is an adapter class that is used to display a list of images in a ListView .   *
 * The class takes in a context and a list of ImageModel objects as parameters in its constructor. *
 * The class extends the BaseAdapter class and overrides its methods to handle the list of images. *
 * The getView() method is used to create the view for each image in the list, it loads the image  *
 * into an ImageView using the Picasso library and sets the title of the image, it sets the        *
 * favorite button and checks if the image is already a favorite, it sets an onClickListener for   *
 * the image to navigate to image details fragment and sets an onClickListener for the favorite    *
 * button to handle adding/removing from favorites. The class also includes a checkExistence()     *
 * method to check if the image already exists in the favorites table and sets the favorite status *
 * of the image accordingly.                                                                       *
 *                                                                                                 *
 *=================================================================================================*

 */

public class ImageListAdapter extends BaseAdapter {
    private List<ImageModel> images;
    private LayoutInflater inflater;
    private Context context;
    private ImageButton favouriteButton;
    private  TextView imageTitle;
    private ImageView cardImage;

    // constructor that takes in context and list of images
    public ImageListAdapter(Context context, List<ImageModel> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    // returns the number of images in the list
    @Override
    public int getCount() {
        return images.size();
    }

    // returns the image at the given position in the list
    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    // returns the position of the image in the list
    @Override
    public long getItemId(int position) {
        return position;
    }

    // creates the view for each image in the list
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

        // checks if the image already exists in the favorites table
        ImageModel image = checkExistence(images.get(position));
        imageTitle = convertView.findViewById(R.id.title);
        favouriteButton = convertView.findViewById(R.id.favourite_button);
        cardImage = convertView.findViewById(R.id.imageView);
        holder.imageView.setTag(image);

        // loads image into imageview using Picasso library
        Picasso.get().load(image.getImageUrl()).into(holder.imageView);

        // sets the title of the image
        imageTitle.setText(image.getImageTitle());

        // sets the favorite button and checks if the image is already a favorite
        new FavouritesHandler(favouriteButton, context).handleFavouriteCheckImage(image);

        // sets onClickListener for the image to navigate to image details fragment
        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("imageUrl", image.getImageUrl());
                bundle.putString("imageText", image.getImageTitle());
                bundle.putString("imageId", image.getImageId());
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_navigation_search_to_imageDetailsFragment,bundle);
            }
        });

        // sets onClickListener for the favorite button to handle adding/removing from favorites
        // this invokes Favourites handler to handle coloring the heart icon (like button)
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FavouritesHandler(favouriteButton, context).handleFavouriteButtonClick(image);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    // view holder class to hold the image view
    private class ViewHolder {
        ImageView imageView;
    }

    // checks if the image already exists in the favorites table to use it later to handle the
    // favourites
    private ImageModel checkExistence(ImageModel image){
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
        if(cursor.getCount() == 0) return image;
        image.setFavourite(true);
        return image;
    }

}
